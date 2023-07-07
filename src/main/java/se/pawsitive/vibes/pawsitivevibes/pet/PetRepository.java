package se.pawsitive.vibes.pawsitivevibes.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.pawsitive.vibes.pawsitivevibes.comment.JpaCommentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class PetRepository {

    private final JpaPetRepository petRepo;
    private final JpaCommentRepository commentRepo;

    @Autowired
    public PetRepository(JpaPetRepository petRepo, JpaCommentRepository commentRepo) {
        this.petRepo = petRepo;
        this.commentRepo = commentRepo;
    }

    public List<Pet> getAllPets() {
        return (List<Pet>) petRepo.findAll();
    }

    public Pet getPetById(Long petId) throws NoSuchElementException {
        Optional<Pet> retrievePet = petRepo.findById(petId);
        if (retrievePet.isEmpty()) {
            throw new NoSuchElementException("No pet exists with provided ID");
        }
        return retrievePet.get();
    }

    public Pet savePet(Pet newPet) {
        return petRepo.save(newPet);
    }

    public void deletePet(Pet pet) {
        petRepo.delete(pet);
    }
}
