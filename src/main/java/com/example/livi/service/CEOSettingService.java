package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.livi.model.CEOSetting;
import com.example.livi.model.Section;
import com.example.livi.repository.CEOSettingRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class CEOSettingService {
	@Autowired
	private CEOSettingRepository ceoSettingRepository;
	@Autowired
	private SectionRepository sessionRepository;
	@Value("${file.upload-dir}")
	private String uploadDir;

	public List<CEOSetting> getCeoSettings() {
		return ceoSettingRepository.findAll();
	}

	public CEOSetting getCeoSettingById(int id) {
		return ceoSettingRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public CEOSetting addCeoSetting(CEOSetting ceoSetting, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);
		Section session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		ceoSetting.setSession(session);
		ceoSetting.setImage(base64Image);
		return ceoSettingRepository.save(ceoSetting);

	}

	public CEOSetting updateBanner(int id, CEOSetting ceoSetting, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<CEOSetting> optional = ceoSettingRepository.findById(id);
		if (optional.isPresent()) {
			CEOSetting existingEntity = optional.get();
			if (ceoSetting.getTitleTag() != null) {
				existingEntity.setTitleTag(ceoSetting.getTitleTag());
			}
			if (ceoSetting.getDescription() != null) {
				existingEntity.setDescription(ceoSetting.getDescription());
			}
			if (ceoSetting.getSlug() != null) {
				existingEntity.setSlug(ceoSetting.getSlug());
			}
			if (ceoSetting.getLang() != null) {
				existingEntity.setLang(ceoSetting.getLang());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getImage())) {
				existingEntity.setImage(base64Image);
			}
			return ceoSettingRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("HeroBanner not found with ID " + id);
		}
	}

	

	public void deleteCEOSetting(int id) {
		if (!ceoSettingRepository.existsById(id)) {
			throw new RuntimeException("CEOSetting not found with id: " + id);
		}
		ceoSettingRepository.deleteById(id);
	}
}
