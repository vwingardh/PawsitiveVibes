package se.pawsitive.vibes.pawsitivevibes.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.pawsitive.vibes.pawsitivevibes.pet.Pet;

@Repository
public class CommentRepository {

    private final JpaCommentRepository repo;

    @Autowired
    public CommentRepository(JpaCommentRepository repo) {
        this.repo = repo;
    }

    public Comment saveComment(Comment comment) {
        return repo.save(comment);
    }
}
