package com.example.challenge.controller;

import com.example.challenge.entity.ChallengeEntity;
import com.example.challenge.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/challenges")
public class ChallengeController {
    @Autowired
    ChallengeService challengeService;
    @PostMapping("/add")
    private ResponseEntity<?> submitChallenge(@RequestBody ChallengeEntity challenge) {
        return ResponseEntity.ok(challengeService.saveChallenge(challenge));
    }

    @GetMapping("")
    private ResponseEntity<?>getChallenge(){
        LocalDateTime date = LocalDateTime.now();
        Timestamp currentDate = Timestamp.valueOf(date);
        return ResponseEntity.ok(challengeService.getCurrentChallenge(currentDate));
    }

    @GetMapping("/{challengeId}")
    private ResponseEntity<?>getChallenge(@PathVariable("challengeId") int id){
        return ResponseEntity.ok(challengeService.getChallengeById(id));
    }
}
