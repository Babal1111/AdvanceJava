package com.example.DoctorService.Controller;

import com.example.DoctorService.Client.UserClient;
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


    @GetMapping("/doctor")
    public String getDoctor() {
//        String users = restTemplate.getForObject("http://UserService/users", String.class);
        String users = userClient.getUsers();
        return "doctor fetched ====> "+users;

        //  return "hi im in doc";

    }

}



