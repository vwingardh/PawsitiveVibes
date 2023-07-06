package se.pawsitive.vibes.pawsitivevibes.pet;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PetService {

    private final PetRepository petRepo;


    @Autowired
    public PetService(PetRepository petRepo) {
        this.petRepo = petRepo;
    }

    public List<Pet> getAllPets() {
        return petRepo.getAllPets();
    }
}
