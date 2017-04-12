package com.webpilot.controller;

import com.webpilot.domain.Registration;
import com.webpilot.services.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Title: RegistrationController
 * Description: Registration controller
 * Date: 4/11/17
 *
 * @author RGH
 */
@RestController
public class RegistrationController {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/")
    @ResponseBody
    ResponseEntity<List<Registration>> getRegistrations() {
        log.debug("getRegistrations: called");
        List<Registration> registrations = registrationService.getRegistrations();
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    @ResponseBody
    ResponseEntity<Registration> getRegistration(@PathVariable String username) {
        log.debug("getRegistrations: called with " + username);
        Registration reg = registrationService.getRegistration(username);
        if (reg == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reg, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    @ResponseBody
    ResponseEntity<Registration> deleteRegistration(@PathVariable String username) {
        log.debug("deleteRegistration: called with " + username);
        registrationService.deleteRegistration(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Registration> addRegistration(@RequestBody Registration registration) {
        log.debug("getRegistrations: called with " + registration);
        registration.setRegisterDate(new Date());
        registrationService.addRegistration(registration);
        return new ResponseEntity<>(registration, HttpStatus.CREATED);

    }
}
