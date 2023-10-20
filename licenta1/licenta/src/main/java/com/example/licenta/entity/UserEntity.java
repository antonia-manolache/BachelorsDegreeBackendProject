package com.example.licenta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Objects;


@Entity
@Table(name="app_user", schema = "public")
@Builder
@Data
@AllArgsConstructor

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

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    private int solved=0;

    public UserEntity() {
    }
    public UserEntity(String userId, String username, String name) {
        this.userId = userId;
        this.username = username;
        this.name = name;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, username, name, solved);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserEntity user = (UserEntity) obj;
        return Objects.equals(id, user.id) && Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(name, user.name) && Objects.equals(solved, user.solved) ;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +'\'' +
                "userId=" + userId +'\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", solved=" + solved +
                '}';
    }
}