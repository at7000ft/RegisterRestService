package com.webpilot.services;

import com.webpilot.domain.Registration;
import com.webpilot.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Date: 4/11/17
 *
 * @author RGH
 */
@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    public Registration getRegistration(String userName) {

        return null;
    }

    public List<Registration> getRegistrations() {

        return new ArrayList<>();
    }

    public void deleteRegistration(String userName) {

    }

    public void addRegistration(Registration registration) {
        registrationRepository.save(registration);
    }
}
