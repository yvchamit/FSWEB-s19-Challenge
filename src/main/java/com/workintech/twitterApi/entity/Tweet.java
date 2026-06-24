package com.workintech.twitterApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tweet", schema = "fsweb")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "content", nullable = false, length = 280)
    private String content;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "author_id", nullable = false)
    private User author;


    @ManyToOne
    @JoinColumn(name = "retweet_id")
    private Tweet retweet;


    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;


    @Column(name = "like_count")
    private int likeCount;


    @Column(name = "retweet_count")
    private int retweetCount;


    @Column(name = "reply_count")
    private int replyCount;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
