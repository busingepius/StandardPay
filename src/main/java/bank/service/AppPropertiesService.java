package bank.service;

import bank.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppPropertiesService {
    @Autowired
    private ApplicationProperties applicationProperties;

    public void printProperties() {
        System.out.println("Name: " + applicationProperties.getName());
        System.out.println("Version: " + applicationProperties.getVersion());
        System.out.println("URL: " + applicationProperties.getUrl());
        System.out.println("Server Name: " + applicationProperties.getServerName());
        System.out.println("First Name: " + applicationProperties.getFirstname());
        System.out.println("Last Name: " + applicationProperties.getLastname());
        System.out.println("Username: " + applicationProperties.getUsername());
        System.out.println("Password: " + applicationProperties.getPassword());
        System.out.println("Countries: " + applicationProperties.getCountries());
    }
}
