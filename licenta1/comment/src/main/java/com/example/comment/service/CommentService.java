package com.example.comment.service;

import com.example.clients.comment.CommentNotificationRequest;
import com.example.comment.client.User;
import com.example.comment.client.UserClient;
import com.example.comment.entity.CommentEntity;
import com.example.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserClient client;


    public CommentEntity saveComment(CommentEntity comment){
        User user = client.findUserByUserId(comment.getUserId());
        comment.setUsername(user.getUsername());
        return commentRepository.save(comment);

    }

    public ArrayList<CommentEntity> getAllComments(String postId){

        ArrayList<CommentEntity> commentList=commentRepository.findAllByPostId(postId);

//        for(int i=0;i<commentList.size();i++) {
//            Comments commentItem=commentList.get(i);
//            commentItem.setUserName(userService.displayUserMetaData(commentItem.getUserId()).getUserName());
//        }

        return commentList;

    }
}
