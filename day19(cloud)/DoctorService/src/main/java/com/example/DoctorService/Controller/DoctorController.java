package com.example.DoctorService.Controller;

import com.example.DoctorService.Client.UserClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DoctorController {
//    @Value("$server.port")
//    private String port;

    private final UserClient userClient;
    public DoctorController(UserClient userClient){
        this.userClient = userClient;
    }

//     final private RestTemplate restTemplate;
//    public DoctorController(RestTemplate restTemplate){
//        this.restTemplate = restTemplate;
//    }


    @GetMapping("/doctors")
    @CircuitBreaker(name = "userService",fallbackMethod = "userFallback")
    public String getDoctor() {
//        String users = restTemplate.getForObject("http://UserService/users", String.class);
        String users = userClient.getUsers();
        return "doctor fetched ====> "+users;

        //  return "hi im in doc";
    }
    public String userFallback(Throwable ex){
        return "User service is currently down. User is on lunch break, showing chached doctor data";
    }

    @GetMapping("/doctors/config")
    public String getConfig(@Value("${doctor.service.message}")String message,
                            @Value("${db.password}") String dbPass){
        return message + " | DB "+dbPass;
    }



}



