package com.example.CloneGamestop.jUnit;

import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
// Indica l'utilizzo del runner di Spring per eseguire i test.
@SpringBootTest
// Indica che si desidera caricare l'applicazione Spring Boot durante l'esecuzione dei test.
public class UserTest {

    @Autowired
    // Inietta il bean UserRepository all'interno della classe di test.
    private UserRepository userRepository;

    @Test
    // Metodo di test per trovare un utente per ID
    public void testFindUserById() {
        Long idUser = 1L;
        // Cerca un utente per ID
        Optional<User> optionalUser = userRepository.findById(idUser);
        // Ottiene l'utente se presente, altrimenti restituisce null
        User user = optionalUser.orElse(null);
        // Verifica se l'ID dell'utente corrisponde all'ID atteso
        assertEquals(idUser, user != null ? user.getIdUser() : null);
    }

    // Ipotetico metodo di test per trovare altri utenti per ID
    /*@Test
    public void testFindUsersById() {
        Collection<Long> collectionUser = Arrays.asList(1L, 2L, 3L);
        for (Long id : collectionUser) {
            testFindUsersById(id);
        }
    }
    private void testFindUsersById(Long idUser) {
        Optional<User> optionalUser = userRepository.findById(idUser);
        User user = optionalUser.orElse(null);
        assertEquals(idUser, user != null ? user.getIdUser() : null);
    }*/

}

//Test jUnit da rivedere e approfondire l'argomento (ripassare il tutto).