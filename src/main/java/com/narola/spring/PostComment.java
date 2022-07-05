package com.narola.spring;

import javax.persistence.*;

@Entity
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pc_id")
    private Long pcId;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id",
            foreignKey = @ForeignKey(
                    name = "post_id_fk",
                    foreignKeyDefinition = "foreign key (post_id) references post (post_id)"))
    private Post post;

    public Long getPcId() {
        return pcId;
    }

    public void setPcId(Long pcId) {
        this.pcId = pcId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
