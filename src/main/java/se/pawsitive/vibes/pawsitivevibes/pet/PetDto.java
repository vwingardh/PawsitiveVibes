package se.pawsitive.vibes.pawsitivevibes.pet;

import java.time.LocalDateTime;

public record PetDto(Long id, String img, String tag, LocalDateTime created, int favorite) {
}
