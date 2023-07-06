package se.pawsitive.vibes.pawsitivevibes.pet;

import org.springframework.data.repository.CrudRepository;

public interface JpaPetRepository extends CrudRepository<Pet, Long> {
}
