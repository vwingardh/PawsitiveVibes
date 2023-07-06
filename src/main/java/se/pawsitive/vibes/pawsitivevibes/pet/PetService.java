package se.pawsitive.vibes.pawsitivevibes.pet;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import se.pawsitive.vibes.pawsitivevibes.comment.Comment;
import se.pawsitive.vibes.pawsitivevibes.comment.CommentRepository;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

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

    public List<Comment> getAllCommentsByPetId(Long petId) throws NoSuchElementException {
        Pet pet = petRepo.getPetById(petId);
        return pet.getComments();
    }

    public Pet createPet(MultipartFile file, String tag) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String directory = "src/main/resources/static/pet-uploads/";
        String filePath = Paths.get(directory, fileName).toString();
        String fileUrl = "/pet-uploads/" + fileName;

        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }

        Pet pet = new Pet();
        pet.setImgPath(fileUrl);
        pet.setTag(tag);
        petRepo.savePet(pet);
        return pet;
    }

    public Comment createComment(String message, Long petId) {
        Pet pet = petRepo.getPetById(petId);
        Comment comment = new Comment();
        comment.setPet(pet);
        comment.setMessage(message);
        commentRepo.saveComment(comment);
        return comment;
    }
}
