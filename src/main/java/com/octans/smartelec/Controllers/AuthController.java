package com.octans.smartelec.Controllers;

import java.sql.SQLException;

import com.octans.smartelec.ApiResponse;
import com.octans.smartelec.DataServices.ElectionDataService;
import com.octans.smartelec.DataServices.FinishedElectionDataService;
import com.octans.smartelec.DataServices.UserDataService;
import com.octans.smartelec.Models.Election;
import com.octans.smartelec.Models.User;
import com.octans.smartelec.Repositories.ElectionRepository;
import com.octans.smartelec.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
@RequestMapping(path = "/auth/")
public class AuthController {

    @Autowired
    UserDataService userDataService;

    @Autowired
    ElectionRepository eRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping(path = "users")
    public ApiResponse allUsers() {
        return userDataService.allUsers();
    }

    @GetMapping(path = "elecCands/{elecId}")
    public ApiResponse allUsersOfElec(@PathVariable Integer elecId) {
        Election e = eRepository.findById(elecId).get();
        return userDataService.allUsersOfElec(e);
    }

    @GetMapping(path = "email/{email}")
    public ApiResponse sendEmail(@PathVariable String email) {
        return userDataService.sendEmail(email);
    }

    @GetMapping(path = "test")
    public String test() {
        return "Helloo";
    }

    @GetMapping(path = "signin/{email}/{pass}")
    public ApiResponse signIn(@PathVariable String email, @PathVariable String pass) {
        return userDataService.signIn(email, pass);
    }

    // @ResponseBody
    // @PostMapping(path = "signup/{name}/{email}/{bio}/{pass}/{mob}/{img}",
    // consumes = { MediaType.APPLICATION_JSON_VALUE,
    // MediaType.APPLICATION_XML_VALUE }, produces = {
    // MediaType.APPLICATION_JSON_VALUE,
    // MediaType.APPLICATION_XML_VALUE })
    // @PathVariable String name,
    // @PathVariable
    // String email,
    // @PathVariable
    // String bio,
    // @PathVariable
    // String pass,
    // @PathVariable
    // String mob,

    // @PathVariable String img
    @PostMapping(path = "signup", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    public ApiResponse signUp(@RequestBody User user) {
        return userDataService.signUp(user);
    }

    @GetMapping(path = "forgot/{email}")
    public ApiResponse forgotPassword(@PathVariable String email) {
        return userDataService.forgotPassword(email);
    }

    @PostMapping(path = "update/{email}/{pass}")
    public ApiResponse updatePassword(@PathVariable String email, @PathVariable String pass) {
        return userDataService.updatePassword(email, pass);
    }

}
