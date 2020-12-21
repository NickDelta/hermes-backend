package org.hua.hermes.service;

import org.hua.hermes.entity.Application;
import org.hua.hermes.exception.ResourceNotFoundException;
import org.hua.hermes.repository.CitizenApplicationRepository;
import org.hua.hermes.util.persistence.OffsetBasedPageRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@Service
public class CitizenService {
    private CitizenApplicationRepository citizenApplicationRepository;

    public CitizenService(CitizenApplicationRepository citizenApplicationRepository) {
        this.citizenApplicationRepository = citizenApplicationRepository;
    }

    public List<Application> getListOfApplications(Integer offset,Integer limit){
        var page = new OffsetBasedPageRequest(offset,limit);
        var applications = citizenApplicationRepository.findAll(page).getContent();
        return applications;
    }

    public URI addApplication(Application application){
        var savedComment = citizenApplicationRepository.saveAndFlush(application);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedComment.getId()).toUri();
        return location;
    }

    public Application getApplication(String id){
        var application = citizenApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        return application;
    }

    public Application updateApplication(String id,Application updatedApplication){
        var currentApplication = citizenApplicationRepository.findById(id).get(); //@PostAuthorize covers null case
        BeanUtils.copyProperties(updatedApplication,currentApplication);
        return citizenApplicationRepository.saveAndFlush(currentApplication);
    }
}

