package se.pawsitive.vibes.pawsitivevibes.pet;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import se.pawsitive.vibes.pawsitivevibes.comment.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id", nullable = false)
    private Long id;

    @Column(name = "pet_img", nullable = false)
    private String img;

    @Column(name = "pet_tag", nullable = false)
    private String tag;

    @Column(name = "pet_created")
    private LocalDateTime created;

    @Column(name = "pet_favorite")
    @ColumnDefault("0")
    private int favorite;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    protected void created() {
        this.created = LocalDateTime.now();
    }

    public Pet() {

    }

    public Pet(Long id, String img, String tag, LocalDateTime created, int favorite, List<Comment> comments) {
        this.id = id;
        this.img = img;
        this.tag = tag;
        this.created = created;
        this.favorite = favorite;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
