package org.hua.hermes.service;

import org.hua.hermes.entity.Application;
import org.hua.hermes.repository.CitizenApplicationRepository;
import org.hua.hermes.util.persistence.OffsetBasedPageRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vivian Gourgioti
 */
@Service
public class CitizenService {
    private CitizenApplicationRepository citizenApplicationRepository;

    public CitizenService(CitizenApplicationRepository citizenApplicationRepository) {
        this.citizenApplicationRepository = citizenApplicationRepository;
    }

    public List<Application> getListOfApplications(Integer offset,Integer limit){
        var page = new OffsetBasedPageRequest(offset,limit);
        return citizenApplicationRepository.findAll(page).getContent();
    }

    public Application addApplication(Application application){
        return citizenApplicationRepository.saveAndFlush(application);
    }

    public Application getApplication(String id){
        return citizenApplicationRepository.findById(id).get();
    }

    public Application updateApplication(String id,Application updatedApplication){
        var currentApplication = citizenApplicationRepository.findById(id).get(); //@PostAuthorize covers null case
        BeanUtils.copyProperties(updatedApplication,currentApplication);
        return citizenApplicationRepository.saveAndFlush(currentApplication);
    }
}

