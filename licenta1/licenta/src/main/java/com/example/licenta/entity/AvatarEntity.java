package com.example.licenta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avatars")
@Getter
@Setter
public class AvatarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;
    private String objectUrl;
    private String version;
    @NotNull
    private String userId;

    public AvatarEntity(String fileName, String objectUrl, String version, String userId) {
        this.fileName = fileName;
        this.objectUrl = objectUrl;
        this.version = version;
        this.userId = userId;
    }
}
