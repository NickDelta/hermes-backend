package org.hua.hermes.controller;

import org.hua.hermes.entity.Application;
import org.hua.hermes.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/citizen/application")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @GetMapping
    public ResponseEntity<?> getApplications(@Valid @RequestParam("offset") Integer offset,@Valid @RequestParam("limit") Integer limit){
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
        URI location = citizenService.addApplication(application);
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
