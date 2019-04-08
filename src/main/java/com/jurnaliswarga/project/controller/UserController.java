package com.jurnaliswarga.project.controller;

import java.util.Map;
import java.util.Optional;

import com.cloudinary.utils.ObjectUtils;
import com.jurnaliswarga.project.config.CloudinaryConfig;
import com.jurnaliswarga.project.login.JWTLoginSuccessResponse;
import com.jurnaliswarga.project.login.LoginRequest;
import com.jurnaliswarga.project.model.Role;
import com.jurnaliswarga.project.model.User;
import com.jurnaliswarga.project.repository.UserRepository;

import com.jurnaliswarga.project.security.JwtTokenProvider;
import com.jurnaliswarga.project.service.MapValidationErrorService;
import com.jurnaliswarga.project.service.UserService;
import com.jurnaliswarga.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


import static com.jurnaliswarga.project.security.SecurityConstant.TOKEN_PREFIX;
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository repository;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired AuthenticationManager authManager;


    @GetMapping
    public Iterable <User> findAll(){
        return repository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null){
            return errorMap;
        }
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(auth);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){

        userValidator.validate(user, result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        user.setRole(Role.normal);
        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
    @PostMapping("/registerMedia")
    public ResponseEntity<?> registerMedia(@Valid @RequestBody User user, BindingResult result){

        userValidator.validate(user, result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        user.setRole(Role.Media);
        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{user_id}")
    public User updateUser(@PathVariable(value = "user_id") Long user_id,
                              @Valid @RequestBody User user) {

        User u = repository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User"+ "user_id"+ user_id));

        u.setAbout(user.getAbout());
        u.setPhone(user.getPhone());

        User updatedUser = repository.save(u);
        return updatedUser;
    }
}

    


