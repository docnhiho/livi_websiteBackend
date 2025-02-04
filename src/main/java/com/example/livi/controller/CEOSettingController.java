package com.example.livi.controller;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<?> addCeoSetting(@RequestParam("image") MultipartFile image, 
			@RequestParam("titleTag") String titleTag,
			@RequestParam("description") String description,
			@RequestParam("slug") String slug,
			@RequestParam("lang") String lang,
			@PathVariable int sessionId ) throws IOException {
				
		try {
			byte[] fileBytes = image.getBytes();
			CEOSetting ceoSetting = new CEOSetting();
			ceoSetting.setTitleTag(titleTag);
			ceoSetting.setDescription(description);
			ceoSetting.setSlug(slug);
			ceoSetting.setLang(lang);
			CEOSetting savedEntity = ceoSettingService.addCeoSetting(ceoSetting, sessionId,
					fileBytes);
			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCEOSetting(
	        @PathVariable int id, 
	        @RequestParam(value = "image", required = false) MultipartFile image,
	        @RequestParam(value = "titleTag", required = false) String titleTag,
	        @RequestParam(value = "description", required = false) String description,
	        @RequestParam(value = "slug", required = false) String slug,
	        @RequestParam(value = "lang", required = false) String lang) throws IOException {
	    
	    try {
			byte[] fileBytes = null;
			if (image != null) {
				fileBytes = image.getBytes();
			}
			CEOSetting ceoSetting = new CEOSetting();
			ceoSetting.setTitleTag(titleTag);
			ceoSetting.setDescription(description);
			ceoSetting.setSlug(slug);
			ceoSetting.setLang(lang);
			CEOSetting updatedEntity = ceoSettingService.updateBanner(id, ceoSetting, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteCEOSetting(@PathVariable int id) {
		ceoSettingService.deleteCEOSetting(id);
	}

}
