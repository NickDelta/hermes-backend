package org.hua.hermes.controller;

import org.hua.hermes.entity.Application;
import org.hua.hermes.service.CitizenService;
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
public class CitizenController {

    private final CitizenService citizenService;

    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping
    public ResponseEntity<?> getApplications(@RequestParam("offset") @NotNull @Min(0) Integer offset,
                                             @RequestParam("limit") @NotNull @Min(1) Integer limit){
        var applications = citizenService.getListOfApplications(offset,limit);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable String id){
        var application = citizenService.getApplication(id);
        return ResponseEntity.ok(application);
    }

    @PostMapping
    public ResponseEntity<?> addApplication(@Valid @RequestBody Application application){

        var savedApplication = citizenService.addApplication(application);

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
        citizenService.updateApplication(id,updatedApplication);
        return ResponseEntity.ok().build();
    }

}
