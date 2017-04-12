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

/**
 * Title:
 * Description:
 * Date: 4/11/17
 *
 * @author RGH
 */
@RestController
public class RegistrationController {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    RegistrationService registrationService;

    //Access at http://localhost:8080
    @GetMapping("/")
    @ResponseBody
    ResponseEntity<Registration> getRegistrations() {
        log.debug("getRegistrations: called");
        Registration reg = new Registration("ace","ace@gmail.com", new Date());
        return new ResponseEntity<Registration>(reg, HttpStatus.OK);
    }

    //Access at http://localhost:8080/ace
    @GetMapping("/{username}")
    @ResponseBody
    ResponseEntity<Registration> getRegistration(@PathVariable String username) {
        log.debug("getRegistrations: called with " + username);
        Registration reg = new Registration(username,"ace@gmail.com", new Date());
        return new ResponseEntity<Registration>(reg, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    @ResponseBody
    ResponseEntity<Registration> deleteRegistration(@PathVariable String username) {
        log.debug("deleteRegistration: called with " + username);
        Registration reg = new Registration(username,"ace@gmail.com", new Date());
        return new ResponseEntity<Registration>(reg, HttpStatus.OK);
    }

    //curl -H "Content-Type: application/json" -X POST -d '{"userName":"xyz","email":"xyz@gmail.com"}' http://localhost:8080/add
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Registration> addRegistration(@RequestBody Registration registration) {
        log.debug("getRegistrations: called with " + registration);

        Registration reg = new Registration("ace","ace@gmail.com", new Date());
        return new ResponseEntity<Registration>(reg, HttpStatus.CREATED);

    }
}
