package com.example.licenta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Collection;
import java.util.Objects;


@Entity
@Table(name="app_user", schema = "public")
public class UserEntity {

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private int id;
    @NotNull
    private String userId;
    private String username;
    private String name;
    private String profileImage;

    public UserEntity() {
    }
    public UserEntity(String userId, String username, String name, String profileImage) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.profileImage = profileImage;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProfileImage() {
        return profileImage;
    }
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, username, name, profileImage);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserEntity user = (UserEntity) obj;
        return Objects.equals(id, user.id) && Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(name, user.name) && Objects.equals(profileImage, user.profileImage) ;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +'\'' +
                "userId=" + userId +'\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", profileImage=" + profileImage +
                '}';
    }
}