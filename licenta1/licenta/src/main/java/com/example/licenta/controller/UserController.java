package com.example.licenta.controller;

import com.example.licenta.entity.UserEntity;
import com.example.licenta.mqrequest.UserRegistrationRequest;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<?>  findUserByUserId(@PathVariable("userId") String userId){
        return ResponseEntity.ok(userService.findUserByUserId(userId));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserEntity user){
        return ResponseEntity.ok(userRepository.save(user));

    }

    @PutMapping("/users/{userId}/solved")
    public ResponseEntity<?> updateSolvedCount(@PathVariable String userId) {
        return ResponseEntity.ok(userService.setSolved(userId));
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
    }

}
