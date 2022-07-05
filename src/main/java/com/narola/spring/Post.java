package com.narola.spring;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_desc")
    private String postDesc;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "post")
    private List<PostComment> commentList = new ArrayList<>();

    public void addComment(PostComment comment) {
        commentList.add(comment);
        comment.setPost(this);
    }

    public void removeComment(PostComment comment) {
        commentList.remove(comment);
        comment.setPost(null);
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public List<PostComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<PostComment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postDesc='" + postDesc + '\'' +
//                ", commentList=" + commentList +
                '}';
    }
}
