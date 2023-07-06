package se.pawsitive.vibes.pawsitivevibes.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.pawsitive.vibes.pawsitivevibes.comment.Comment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin("*")
public class PetController {

    private final PetService service;

    @Autowired
    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return service.getAllPets();
    }

    @GetMapping("/{petId}/comments")
    public ResponseEntity<Object> getAllCommentsByPetId(@PathVariable Long petId) {
        try {
            List<Comment> comments = service.getAllCommentsByPetId(petId);
            return ResponseEntity.ok(comments);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createPet(@RequestParam("image") MultipartFile file, @RequestParam("tag") String tag) {
        try {
            Pet pet = service.createPet(file, tag);
            URI location = URI.create("/api/pets/" + pet.getId());
            return ResponseEntity.created(location).body(pet);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{petId}/comments")
    public ResponseEntity<Object> createComment(@PathVariable Long petId, @RequestBody Map<String, String> body) {
        try {
            String message = body.get("message");
            Comment comment = service.createComment(message, petId);
            URI location = URI.create("/api/pets/" + petId + "/comments/" + comment.getId());
            return ResponseEntity.created(location).body(comment);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
