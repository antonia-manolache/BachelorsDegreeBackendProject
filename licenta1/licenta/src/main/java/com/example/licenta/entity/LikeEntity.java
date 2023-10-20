package com.example.licenta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "likes")
@Getter
@Setter
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userId;
    private int postId;

    public LikeEntity(String userId, int postId){
        this.userId=userId;
        this.postId=postId;
    }
}
