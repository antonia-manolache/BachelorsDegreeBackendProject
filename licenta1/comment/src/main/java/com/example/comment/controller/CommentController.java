package com.example.comment.controller;

import com.example.comment.entity.CommentEntity;
import com.example.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    CommentService commentsService;

    @PostMapping("")
    private ResponseEntity<?> submitComment(@RequestBody CommentEntity comment) {
        return ResponseEntity.ok(commentsService.saveComment(comment));
    }

    @GetMapping("/{postId}")
    private ResponseEntity<?>getCommentsForPost(@PathVariable("postId") String postId){
        return ResponseEntity.ok(commentsService.getAllComments(postId));
    }


}
