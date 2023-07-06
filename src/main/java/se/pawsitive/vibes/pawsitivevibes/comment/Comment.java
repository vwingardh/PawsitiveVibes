package se.pawsitive.vibes.pawsitivevibes.comment;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @Column(name = "comment_message", nullable = false)
    private String message;

    @Column(name = "comment_created")
    private LocalDateTime created;

    @Column(name = "comment_favorite")
    @ColumnDefault("0")
    private int favorite;

    @PrePersist
    protected void created() {
        this.created = LocalDateTime.now();
    }

    public Comment() {

    }

    public Comment(Long id, String message, LocalDateTime created, int favorite) {
        this.id = id;
        this.message = message;
        this.created = created;
        this.favorite = favorite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
