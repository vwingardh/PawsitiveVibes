package se.pawsitive.vibes.pawsitivevibes.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

    private final JpaCommentRepository repo;

    @Autowired
    public CommentRepository(JpaCommentRepository repo) {
        this.repo = repo;
    }
}
