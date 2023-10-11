package com.example.licenta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "posts")
@Getter
@Setter
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
    @Column(columnDefinition = "integer default 0")
    private int likes;
    public PostEntity(String fileName, String objectUrl,String version, String userId, int likes) {
        this.fileName = fileName;
        this.objectUrl = objectUrl;
        this.version = version;
        this.userId = userId;
        this.likes = likes;
    }
}
