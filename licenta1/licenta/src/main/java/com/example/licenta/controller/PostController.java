package com.example.licenta.controller;

import com.example.licenta.entity.PostEntity;
import com.example.licenta.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("api/posts")
public class PostController {
    private PostService postService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("userId") String userId,@RequestParam("file") MultipartFile file,@RequestParam("description") String description, @RequestParam("challengeId") int challengeId) throws IOException {
        postService.upload(file,userId,description,challengeId);
        return ok(postService);
    }

    @GetMapping("get-posts")
    public ResponseEntity<?> getAllPosts()
    {
        List<PostEntity> posts = postService.getAllPosts();
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(posts);
        }
    }
    @GetMapping("/get-posts-by/{userId}")
    public ResponseEntity<?> getAllPosts(@PathVariable("userId") String userId)
    {
        List<PostEntity> posts = postService.getAllPostsByUserId(userId);
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(posts);
        }
    }
    @GetMapping("/get-posts-by-challenge/{challengeId}")
    public ResponseEntity<?> getAllPostsByChallenge(@PathVariable("challengeId") int challengeId)
    {
        List<PostEntity> posts = postService.getAllPostsByChallengeId(challengeId);
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(posts);
        }
    }
}
