package se.pawsitive.vibes.pawsitivevibes.pet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PetServiceTest {
    @Autowired
    PetService service;

    @MockBean
    PetRepository repo;

    @Test
    void getAllPets() {
        service.getAllPets();
        verify(repo, times(1)).getAllPets();
    }
}
