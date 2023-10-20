package com.example.licenta.controller;

import com.example.licenta.entity.AvatarEntity;
import com.example.licenta.entity.PostEntity;
import com.example.licenta.service.AvatarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/avatars")
@AllArgsConstructor
public class AvatarController {

    AvatarService avatarService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("userId") String userId, @RequestParam("file") MultipartFile file) throws IOException {
        avatarService.upload(file,userId);
        return ResponseEntity.ok(avatarService);
    }

    @GetMapping("/get-avatar-by/{userId}")
    public ResponseEntity<?> getAvatarByUserId(@PathVariable("userId") String userId)
    {
        AvatarEntity avatar = avatarService.getAvatarByUserId(userId);
        if (avatar==null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(avatar);
        }
    }

}
