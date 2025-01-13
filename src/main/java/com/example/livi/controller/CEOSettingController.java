package com.example.livi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.livi.model.CEOSetting;
import com.example.livi.service.CEOSettingService;

@RestController
@RequestMapping("/ceosetting")
public class CEOSettingController {

	@Autowired
	private CEOSettingService ceoSettingService;

	@Value("${file.upload-dir}") // Đọc cấu hình thư mục upload từ application.properties
	private String uploadDir;

	@GetMapping("")
	public List<CEOSetting> getAllSessions() {
		return ceoSettingService.getCeoSettings();
	}

	@GetMapping("/{id}")
	public CEOSetting getSessionById(@PathVariable int id) {
		return ceoSettingService.getCeoSettingById(id);
	}

//	@PostMapping("{sessionId}")
//	public CEOSetting addSession(@RequestBody CEOSetting ceoSetting, @PathVariable int sessionId) {
//		//TODO: process POST request
//		return ceoSettingService.addCeoSetting(ceoSetting, sessionId);
//	}

	@PostMapping("/{sessionId}")
	public ResponseEntity<CEOSetting> addCeoSetting(@RequestParam("image") MultipartFile image, // File ảnh
			@RequestParam("titleTag") String titleTag, // Thẻ tiêu đề
			@RequestParam("description") String description, // Mô tả
			@PathVariable int sessionId // ID của session
	) {
		try {
			// Bước 1: Lưu ảnh vào thư mục trên server
			Path uploadPath = Paths.get(uploadDir);

			// Tạo thư mục nếu chưa tồn tại
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			// Lấy tên file gốc
			String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
			Path imagePath = uploadPath.resolve(imageName);

			// Lưu file vào thư mục
			Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

			// Bước 2: Lưu thông tin vào database
			CEOSetting ceoSetting = new CEOSetting();
			ceoSetting.setImage(uploadDir + imageName); // Lưu đường dẫn file vào database
			ceoSetting.setTitleTag(titleTag);
			ceoSetting.setDescription(description);

			// Gọi service để lưu
			CEOSetting savedSetting = ceoSettingService.addCeoSetting(ceoSetting, sessionId);

			return ResponseEntity.ok(savedSetting);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<CEOSetting> updateCEOSetting(
	        @PathVariable int id, 
	        @RequestParam(value = "image", required = false) MultipartFile image,
	        @RequestParam(value = "titleTag", required = false) String titleTag,
	        @RequestParam(value = "description", required = false) String description,
	        @RequestParam(value = "slug", required = false) String slug,
	        @RequestParam(value = "sessionId", required = false) Integer sessionId) {
	    
	    try {
	        // Lấy CEOSetting hiện tại từ database
	        Optional<CEOSetting> existingCEOSetting = Optional.ofNullable(ceoSettingService.getCeoSettingById(id));
	        if (!existingCEOSetting.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        CEOSetting ceoSettingDetails = existingCEOSetting.get();
	        
	        // Cập nhật các trường nếu có thay đổi
	        if (titleTag != null) ceoSettingDetails.setTitleTag(titleTag);
	        if (description != null) ceoSettingDetails.setDescription(description);
	        if (slug != null) ceoSettingDetails.setSlug(slug);
	        
	        // Xử lý ảnh nếu có
	        if (image != null && !image.isEmpty()) {
	            // Lưu ảnh mới vào thư mục
	            Path uploadPath = Paths.get(uploadDir);
	            if (!Files.exists(uploadPath)) {
	                Files.createDirectories(uploadPath); // Tạo thư mục nếu chưa có
	            }

	            // Tạo tên ảnh mới để tránh trùng lặp
	            String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
	            Path imagePath = uploadPath.resolve(imageName);

	            // Lưu ảnh vào thư mục
	            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

	            // Cập nhật đường dẫn ảnh vào CEOSetting
	            ceoSettingDetails.setImage(uploadDir + imageName); // Lưu đường dẫn ảnh vào database
	        }

	        // Cập nhật CEOSetting trong database
	        CEOSetting updatedCEOSetting = ceoSettingService.updateCEOSetting(id, ceoSettingDetails);
	        return ResponseEntity.ok(updatedCEOSetting);

	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	}

	@DeleteMapping("/{id}")
	public void deleteCEOSetting(@PathVariable int id) {
		ceoSettingService.deleteCEOSetting(id);
	}

}
