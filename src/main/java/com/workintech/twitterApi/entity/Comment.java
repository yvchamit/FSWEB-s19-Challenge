package com.workintech.twitterApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comment", schema = "fsweb")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "content", nullable = false)
    private String content;


    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;


    @ManyToOne
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
