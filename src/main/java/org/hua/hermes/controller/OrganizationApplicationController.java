package org.hua.hermes.controller;

import org.hua.hermes.data.entity.Application;
import org.hua.hermes.service.OrganizationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @author <a href="mailto:nikosdelta@protonmail.com">Nick Dimitrakopoulos
 */
@RestController
@RequestMapping("/organization/application")
@Validated
public class OrganizationApplicationController
{

    private final OrganizationApplicationService organizationApplicationService;

    public OrganizationApplicationController(OrganizationApplicationService organizationApplicationService)
    {
        this.organizationApplicationService = organizationApplicationService;
    }

    @GetMapping
    public ResponseEntity<?> findApplications(@RequestParam("offset")
                                              @NotNull(message = "{offset.notnull}")
                                              @Min(value = 0,message = "{offset.min}") Integer offset,

                                              @RequestParam("limit")
                                              @NotNull(message = "{limit.notnull}")
                                              @Min(value = 1,message = "{limit.min}")  Integer limit)
    {
        var applications = organizationApplicationService.findApplications(offset,limit);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findApplicationById(@PathVariable String id)
    {
        var application = organizationApplicationService.findApplicationById(id);
        return ResponseEntity.ok(application);
    }

    @GetMapping("/count")
    public ResponseEntity<?> applicationNumberByOrganization() {
        Long count = organizationApplicationService.getOrganizationApplicationNumber();
        return ResponseEntity.ok(count);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateApplication(@PathVariable String id,
                                               @RequestBody @Valid Application application)
    {
        organizationApplicationService.updateApplication(id,application);
        return ResponseEntity.ok().build();
    }


}
