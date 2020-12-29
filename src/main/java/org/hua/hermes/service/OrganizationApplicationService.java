package org.hua.hermes.service;

import org.hua.hermes.data.OffsetBasedPageRequest;
import org.hua.hermes.data.entity.Application;
import org.hua.hermes.repository.OrganizationApplicationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:nikosdelta@protonmail.com">Nick Dimitrakopoulos
 */
@Service
public class OrganizationApplicationService
{

    private final OrganizationApplicationRepository organizationApplicationRepository;

    public OrganizationApplicationService(OrganizationApplicationRepository organizationApplicationRepository)
    {
        this.organizationApplicationRepository = organizationApplicationRepository;
    }

    public List<Application> findApplications(int offset, int limit)
    {
        var page = new OffsetBasedPageRequest(offset,limit);
        return organizationApplicationRepository.findAll(page).getContent();
    }

    public Application findApplicationById(String id)
    {
        return organizationApplicationRepository.findById(id).get();
    }

    public Long getOrganizationApplicationNumber() {
        return  organizationApplicationRepository.count();
    }

    public void updateApplication(String id, Application application)
    {
        var savedApplication = organizationApplicationRepository.findById(id).get();
        BeanUtils.copyProperties(application,savedApplication);
        organizationApplicationRepository.saveAndFlush(savedApplication);
    }


}
