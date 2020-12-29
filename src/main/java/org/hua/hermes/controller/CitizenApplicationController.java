package org.hua.hermes.controller;

import org.hua.hermes.data.entity.Application;
import org.hua.hermes.service.CitizenApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.net.URI;

/**
 * @author Vivian Gourgioti
 */
@RestController
@RequestMapping("/citizen/application")
@Validated
public class CitizenApplicationController
{

    private final CitizenApplicationService citizenApplicationService;

    public CitizenApplicationController(CitizenApplicationService citizenApplicationService) {
        this.citizenApplicationService = citizenApplicationService;
    }

    @GetMapping
    public ResponseEntity<?> getApplications(@RequestParam("offset")
                                             @NotNull(message = "{offset.notnull}")
                                             @Min(value = 0,message = "{offset.min}") Integer offset,

                                             @RequestParam("limit")
                                             @NotNull(message = "{limit.notnull}")
                                             @Min(value = 1,message = "{limit.min}")  Integer limit)
    {
        var applications = citizenApplicationService.getListOfApplications(offset,limit);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable String id){
        var application = citizenApplicationService.getApplication(id);
        return ResponseEntity.ok(application);
    }

    @GetMapping("/count")
    public ResponseEntity<?> applicationNumberByCitizen() {
        Long count = citizenApplicationService.getCitizenApplicationNumber();
        return ResponseEntity.ok(count);
    }

    @PostMapping
    public ResponseEntity<?> addApplication(@Valid @RequestBody Application application){

        var savedApplication = citizenApplicationService.addApplication(application);

        //Get the location of the new resource
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedApplication.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateApplication(@PathVariable String id,
                                               @Valid @RequestBody Application updatedApplication)
    {
        citizenApplicationService.updateApplication(id,updatedApplication);
        return ResponseEntity.ok().build();
    }


}
