package se.pawsitive.vibes.pawsitivevibes.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
