package com.example.livi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.DocumentBanner;
import com.example.livi.service.DocumentBannerService;



@RestController
@RequestMapping("/documentbanner")
public class DocumentBannerController {

	@Autowired
	private DocumentBannerService documentBannerService;
	
	@GetMapping("")
	public List<DocumentBanner> getDocumentBanners() {
		return documentBannerService.getDocumentBanners();
	}
	
	@GetMapping("/{id}")
	public DocumentBanner getDocumentBannerById(@PathVariable int id) {
		return documentBannerService.getDocumentBannerById(id);
	}
	
    @PostMapping("/{sessionId}")
    public ResponseEntity<DocumentBanner> uploadFile(
            @PathVariable("sessionId") int sessionId,  // Lấy sessionId từ URL
            @RequestParam("file") MultipartFile file,
            @RequestParam("language") String language
    ) {
        try {
            DocumentBanner savedBanner = documentBannerService.uploadFile(file, sessionId, language);
            return ResponseEntity.ok(savedBanner);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable int id) {
        try {
            byte[] fileData = documentBannerService.downloadFile(id);

            // Lấy file mở rộng từ đường dẫn file và xác định loại MIME
            String fileExtension = getFileExtension(fileData);
            MediaType mediaType = getMediaTypeForFile(fileExtension);

            return ResponseEntity.ok()
                    .contentType(mediaType) // Đảm bảo content type đúng
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file_" + id + "\"")
                    .body(fileData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    private String getFileExtension(byte[] fileData) {
        return "webp"; // Bạn có thể thay đổi điều này để tự động xác định phần mở rộng
    }

    private MediaType getMediaTypeForFile(String extension) {
        switch (extension.toLowerCase()) {
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG;
            case "png":
                return MediaType.IMAGE_PNG;
            case "webp":
                return MediaType.parseMediaType("image/webp");
            case "pdf":
                return MediaType.APPLICATION_PDF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM; // Dùng cho các loại file khác
        }
    }
}
