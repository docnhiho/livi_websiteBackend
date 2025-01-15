package com.example.livi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.livi.model.DocumentBanner;
import com.example.livi.model.Session;
import com.example.livi.repository.DocumentBannerRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class DocumentBannerService {
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Autowired
	private DocumentBannerRepository documentBannerRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<DocumentBanner> getDocumentBanners(){
		return documentBannerRepository.findAll();
	}
	
	public DocumentBanner getDocumentBannerById(int id) {
		return documentBannerRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
    public DocumentBanner uploadFile(MultipartFile file, int sessionId, String language) throws IOException {
        // Kiểm tra sessionId có tồn tại trong cơ sở dữ liệu không
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session không tồn tại với id: " + sessionId));

        // Tạo tên file duy nhất
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        
        // Đường dẫn lưu file
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent()); // Tạo thư mục nếu chưa tồn tại
        Files.write(filePath, file.getBytes());

        // Lưu thông tin file vào cơ sở dữ liệu
        DocumentBanner banner = new DocumentBanner();
        banner.setSession(session);
        banner.setLanguage(language);
        banner.setPath(filePath.toString());
        banner.setAttachement(file.getOriginalFilename());

        return documentBannerRepository.save(banner);
    }
    
    public byte[] downloadFile(int id) throws IOException {
        // Lấy đối tượng DocumentBanner từ DB
        DocumentBanner banner = documentBannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File không tồn tại với id: " + id));

        // Đọc file từ đường dẫn
        Path filePath = Paths.get(banner.getPath());
        return Files.readAllBytes(filePath);
    }

}
