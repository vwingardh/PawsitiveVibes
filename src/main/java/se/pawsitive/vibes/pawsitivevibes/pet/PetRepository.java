package se.pawsitive.vibes.pawsitivevibes.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetRepository {

    private final JpaPetRepository repo;

    @Autowired
    public PetRepository(JpaPetRepository repo) {
        this.repo = repo;
    }

    public List<Pet> getAllPets() {
        return (List<Pet>) repo.findAll();
    }
}
