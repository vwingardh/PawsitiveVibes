package se.pawsitive.vibes.pawsitivevibes.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin("*")
public class PetController {

    PetService service;

    @Autowired
    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return service.getAllPets();
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
}
