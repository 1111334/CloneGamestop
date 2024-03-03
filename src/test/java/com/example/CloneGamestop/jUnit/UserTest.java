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
//La classe è annotata con @RunWith(SpringRunner.class) che indica l'utilizzo del runner di Spring per eseguire i test.
@SpringBootTest
// viene utilizzato per indicare che si desidera caricare l'applicazione Spring Boot durante l'esecuzione dei test.
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserById() {
        Long idUser = 1L;
        Optional<User> optionalUser = userRepository.findById(idUser);
        User user = optionalUser.orElse(null);
        assertEquals(idUser, user != null ? user.getIdUser() : null);
    }

    //Test per trovare altri idUser
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
