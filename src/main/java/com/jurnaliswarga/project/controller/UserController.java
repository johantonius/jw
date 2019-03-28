package com.jurnaliswarga.project.controller;

import java.util.Map;
import java.util.Optional;

import com.cloudinary.utils.ObjectUtils;
import com.jurnaliswarga.project.config.CloudinaryConfig;
import com.jurnaliswarga.project.model.User;
import com.jurnaliswarga.project.repository.UserRepository;

import com.jurnaliswarga.project.service.MapValidationErrorService;
import com.jurnaliswarga.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javassist.tools.web.BadHttpRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){

//        userValidator.validate(user, result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}

    


