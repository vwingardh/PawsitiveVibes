package se.pawsitive.vibes.pawsitivevibes.comment;

import java.time.LocalDateTime;

public record CommentDto(Long id, String message, LocalDateTime created, int favorite) {
}
