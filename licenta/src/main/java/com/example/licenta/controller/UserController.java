package com.example.licenta.controller;

import com.example.licenta.entity.UserEntity;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.request.LoginRequest;
import com.example.licenta.response.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.licenta.request.SignupRequest;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/authentication")
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<?>  getUser(@PathVariable("userId") String userId){
        return ResponseEntity.ok(userRepository.findByUserId(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserEntity user){

//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        UserEntity user = new UserEntity(
//                signUpRequest.getUserId(),
//                signUpRequest.getUsername(),
//                signUpRequest.getName(),
//                signUpRequest.getProfileImage()
//        );
//
//        userRepository.save(user);

        return ResponseEntity.ok(userRepository.save(user));

    }

//    @GetMapping("/login")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
//        if (!userRepository.existsByUsername(loginRequest.getEmail())){
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Incorrect Email!"));
//        }
//        else if (userRepository.existsByEmail(loginRequest.getEmail())) {
//            UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
//            if(loginRequest.getPassword().equals(user.getPassword())==false){
//                return ResponseEntity.badRequest().body(new MessageResponse("Error: Incorrect Password!"));
//            }
//        }
//        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
//        return ResponseEntity.ok(user);
//    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
    }

}
