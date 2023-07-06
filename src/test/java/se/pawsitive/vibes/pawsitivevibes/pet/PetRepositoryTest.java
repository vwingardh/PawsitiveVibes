package se.pawsitive.vibes.pawsitivevibes.pet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PetRepositoryTest {
    @Autowired
    PetRepository repo;

    @Test
    void getAllPets() {
        List<Pet> pets = repo.getAllPets();
        Pet pet = pets.get(0);
        assertNotNull(pets);
        assertThat(pet.getId()).isEqualTo(3828L);
        assertThat(pet.getImgPath()).isEqualTo("src/main/resources/static/pet-uploads/test.jpeg");
        assertThat(pet.getTag()).isEqualTo("cat");
        assertThat(pet.getCreated().toString()).isEqualTo("2023-07-06T12:08:37.047688");
        assertThat(pet.getFavorite()).isEqualTo(0);
    }
}
