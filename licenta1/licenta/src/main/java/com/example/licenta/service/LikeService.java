package com.example.licenta.service;

import com.example.amqp.RabbitMQMessageProducer;
import com.example.clients.like.LikeNotificationRequest;
import com.example.clients.notification.NotificationRequest;
import com.example.licenta.entity.LikeEntity;
import com.example.licenta.mqrequest.LikeRequest;
import com.example.licenta.mqrequest.UserRegistrationRequest;
import com.example.licenta.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public boolean hasUserLikedPost(String userId, int postId){
        LikeEntity like = likeRepository.findByUserIdAndPostId(userId, postId);
        return like != null;
    }
    public void userLikesPost(LikeRequest request){
        LikeEntity like = new LikeEntity(request.byUserId(),request.toPostId());
        likeRepository.save(like);
        LikeNotificationRequest notificationRequest = new LikeNotificationRequest(
                like.getUserId(),
                like.getPostId(),
                String.format("Post liked by: ",
                        like.getUserId())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }

    public void userDislikesPost(String userId, int postId){
        LikeEntity like = likeRepository.findByUserIdAndPostId(userId, postId);
        likeRepository.delete(like);
    }

    public int getNumberOfLikes(int postId){
        return likeRepository.countByPostId(postId);
    }
}
