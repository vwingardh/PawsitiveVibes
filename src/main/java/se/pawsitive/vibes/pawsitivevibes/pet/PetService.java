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

    public PetDto createPet(AddPetDto addPetDto) {
        Pet pet = new Pet();
        pet.setImg(addPetDto.img());
        pet.setTag(addPetDto.tag());
        petRepo.savePet(pet);
        return convertPetToDto(pet);
    }

    private static PetDto convertPetToDto(Pet pet) {
        PetDto petDto = new PetDto(
                pet.getId(),
                pet.getImg(),
                pet.getTag(),
                pet.getCreated(),
                pet.getFavorite()
        );
        return petDto;
    }
}
