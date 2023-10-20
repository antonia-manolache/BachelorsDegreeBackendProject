package com.example.licenta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Entity
@Table(name = "posts")
@Getter
@Setter
@Data
@AllArgsConstructor
public class PostEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fileName;
    private String objectUrl;
    private String version;
    @NotNull
    private String userId;

    private String username;
    private String description;
    private int challengeId;


    public PostEntity(String fileName, String objectUrl,String version, String userId,String username,String description,int challengeId) {
        this.fileName = fileName;
        this.objectUrl = objectUrl;
        this.version = version;
        this.userId = userId;
        this.username=username;
        this.description=description;
        this.challengeId=challengeId;
    }
}
