package se.pawsitive.vibes.pawsitivevibes.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.pawsitive.vibes.pawsitivevibes.pet.Pet;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    public void deleteComment(Comment comment) {
        repo.delete(comment);
    }

    public Comment getCommentById(Long commentId) throws NoSuchElementException {
        Optional<Comment> retrieveComment = repo.findById(commentId);
        if (retrieveComment.isEmpty()) {
            throw new NoSuchElementException("No comment exists with provided ID");
        }
        return retrieveComment.get();
    }
}
