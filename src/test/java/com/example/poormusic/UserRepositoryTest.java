package com.example.poormusic;

import com.example.poormusic.entity.Role;
import com.example.poormusic.entity.User;
import com.example.poormusic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void test() {

    }

    @Test
    public void testCreateRole() {
        Role roleAdministrator = new Role("Administrator");
        Role roleEditor = new Role("Editor");
        Role roleVisitor = new Role("Visitor");

        entityManager.persist(roleAdministrator);
        entityManager.persist(roleEditor);
        entityManager.persist(roleVisitor);
    }

    @Test
    public void testCreateNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User user = new User("John", "john@gmail.com", "john1 ", "user7");
        user.addRole(roleAdmin);
        userRepository.save(user);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        Role roleEditor = entityManager.find(Role.class, 2);
        Role roleVisitor = entityManager.find(Role.class, 3);
        User user = new User("Kate", "kate@gmail.com", "kate2", "user8");
        user.addRole(roleEditor);
        user.addRole(roleVisitor);
        userRepository.save(user);
    }

    @Test
    public void testAssignRoleToExistingUser() {
        User user = userRepository.findById(7L).get();
        Role roleEditor = entityManager.find(Role.class, 4);
        user.addRole(roleEditor);
    }

    @Test
    public void testRemoveRoleFromExistingUser() {
        User user = userRepository.findById(7L).get();
        Role role = new Role(5L);
        user.removeRole(role);
    }

    @Test
    public void testCreateNewUserWithNewRole() {
        User user = new User("Jane", "jane@gmail.com", "jane3", "user9");
        Role roleGuest = new Role("Guest");
        user.addRole(roleGuest);

        userRepository.save(user);
    }

    @Test
    public void testGetUser() {
        User user = userRepository.findById(2L).get();
        System.out.println(user.getEmail());
        System.out.println(user.getRoles());
    }

    @Test
    public void testRemoveUser() {
        userRepository.deleteById(3L);
    }
}
