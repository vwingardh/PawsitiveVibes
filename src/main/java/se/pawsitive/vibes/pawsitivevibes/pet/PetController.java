package se.pawsitive.vibes.pawsitivevibes.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public PetDto createPet(@Validated @RequestBody AddPetDto addPetDto) {
        return service.createPet(addPetDto);
    }
}
