package se.pawsitive.vibes.pawsitivevibes.pet;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import se.pawsitive.vibes.pawsitivevibes.comment.CommentRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
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

    public Pet createPet(MultipartFile file, String tag) throws FileNotFoundException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String directory = "src/main/resources/static/pet-uploads/";
        String filePath = Paths.get(directory, fileName).toString();

        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Pet pet = new Pet();
        pet.setImgPath(filePath);
        pet.setTag(tag);
        petRepo.savePet(pet);
        return pet;
    }

}
