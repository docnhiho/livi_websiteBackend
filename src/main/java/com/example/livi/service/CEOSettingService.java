package com.example.livi.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.livi.model.CEOSetting;
import com.example.livi.model.Session;
import com.example.livi.repository.CEOSettingRepository;
import com.example.livi.repository.SessionRepository;
import jakarta.transaction.Transactional;

@Service
public class CEOSettingService {
	@Autowired
	private CEOSettingRepository ceoSettingRepository;
	@Autowired
	private SessionRepository sessionRepository;
	@Value("${file.upload-dir}")
	private String uploadDir;

	public List<CEOSetting> getCeoSettings() {
		return ceoSettingRepository.findAll();
	}

	public CEOSetting getCeoSettingById(int id) {
		return ceoSettingRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public CEOSetting addCeoSetting(CEOSetting ceoSetting, int sessionId) {
		if (ceoSetting != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			ceoSetting.setSession(session);
			return ceoSettingRepository.save(ceoSetting);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	

    @Transactional
    public CEOSetting updateCEOSetting(int id, CEOSetting ceoSettingDetails) {
        Optional<CEOSetting> existingCEOSetting = ceoSettingRepository.findById(id);
        if (existingCEOSetting.isPresent()) {
            CEOSetting ceoSetting = existingCEOSetting.get();
            ceoSetting.setImage(ceoSettingDetails.getImage());
            ceoSetting.setTitleTag(ceoSettingDetails.getTitleTag());
            ceoSetting.setDescription(ceoSettingDetails.getDescription());
            ceoSetting.setSlug(ceoSettingDetails.getSlug());
            
            return ceoSettingRepository.save(ceoSetting);
        } else {
            throw new IllegalArgumentException("CEOSetting not found with id: " + id);
        }
    }

	public void deleteCEOSetting(int id) {
		if (!ceoSettingRepository.existsById(id)) {
			throw new RuntimeException("CEOSetting not found with id: " + id);
		}
		ceoSettingRepository.deleteById(id);
	}
}
