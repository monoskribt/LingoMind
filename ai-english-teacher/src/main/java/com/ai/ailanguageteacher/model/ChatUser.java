package com.ai.ailanguageteacher.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "chat_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 160)
    private String email;

    @OneToMany(mappedBy = "chatUser", cascade = CascadeType.ALL,
               fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Chat> chats;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatUser chatUser = (ChatUser) o;
        return Objects.equals(id, chatUser.id) &&
                Objects.equals(username, chatUser.username) &&
                Objects.equals(email, chatUser.email) &&
                Objects.equals(chats, chatUser.chats) &&
                Objects.equals(createdAt, chatUser.createdAt) &&
                Objects.equals(updatedAt, chatUser.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, chats, createdAt, updatedAt);
    }
}
