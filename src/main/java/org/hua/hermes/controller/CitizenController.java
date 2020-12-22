package org.hua.hermes.controller;

import org.hua.hermes.entity.Application;
import org.hua.hermes.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;

@RestController
@RequestMapping("/citizen/application")
public class CitizenController {

    private CitizenService citizenService;

    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping
    public ResponseEntity<?> getApplications(@RequestParam("offset") @Min(0) Integer offset, @Valid @RequestParam("limit") @Min(1) Integer limit){
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
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(citizenService.addApplication(application).getId()).toUri();

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
