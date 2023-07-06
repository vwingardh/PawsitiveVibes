package se.pawsitive.vibes.pawsitivevibes.pet;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pawsitive.vibes.pawsitivevibes.comment.CommentRepository;

import java.util.List;

@Service
@Transactional
public class PetService {

    private final PetRepository petRepo;
    private final CommentRepository commentRepo;


    @Autowired
    public PetService(PetRepository petRepo, CommentRepository commentRepo) {
        this.petRepo = petRepo;
        this.commentRepo = commentRepo;
    }

    public List<Pet> getAllPets() {
        return petRepo.getAllPets();
    }
}
