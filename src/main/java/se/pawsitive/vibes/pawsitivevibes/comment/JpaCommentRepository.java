package se.pawsitive.vibes.pawsitivevibes.comment;

import org.springframework.data.repository.CrudRepository;

public interface JpaCommentRepository extends CrudRepository<Comment, Long> {
}
