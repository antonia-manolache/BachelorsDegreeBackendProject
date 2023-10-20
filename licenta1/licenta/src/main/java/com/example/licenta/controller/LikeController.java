package com.example.licenta.controller;

import com.example.clients.like.LikeNotificationRequest;
import com.example.licenta.mqrequest.LikeRequest;
import com.example.licenta.service.LikeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/likes")
@AllArgsConstructor
@Slf4j
public class LikeController {

    private final LikeService likeService;

    @GetMapping("")
    public Boolean hasUserLikedPost(@RequestParam("userId") String userId, @RequestParam("postId")  int postId){
        return likeService.hasUserLikedPost(userId,postId);
    }

    @GetMapping("/number")
    public int getNumberOfLikes(@RequestParam("postId")  int postId){
        return likeService.getNumberOfLikes(postId);
    }

    @PostMapping("/add")
    public void addLike(@RequestParam("userId") String userId, @RequestParam("postId")  int postId){
        LikeRequest notificationRequest = new LikeRequest(
                userId,
                postId
        );
        log.info("new customer registration {}", notificationRequest);
        likeService.userLikesPost(notificationRequest);
    }

    @DeleteMapping("/delete")
    public void deleteLike(@RequestParam("userId") String userId, @RequestParam("postId") int postId){
        likeService.userDislikesPost(userId,postId);
    }
}