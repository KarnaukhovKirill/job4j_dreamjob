package ru.job4j.dream.store;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DbStoreTest {
    private static Store store;

    @BeforeClass
    public static void init() {
        store = DbStore.instOf();
    }

    @After
    public void wipeTables() {
        store.delAllPosts();
        store.delAllCandidates();
        store.delAllUsers();
    }

    @Test
    public void testFindAllPosts() {
        var first = new Post(0, "First Post");
        var second = new Post(0, "Second Post");
        store.save(first);
        store.save(second);
        Collection<Post> excepted = List.of(first, second);
        var rsl = store.findAllPosts();
        assertThat(rsl, is(excepted));
    }

    @Test
    public void testFindAllCandidates() {
        var first = new Candidate(0, "First Candidate");
        var second = new Candidate(0, "Second Candidatte");
        store.save(first);
        store.save(second);
        Collection<Candidate> excepted = List.of(first, second);
        var rsl = store.findAllCandidates();
        assertThat(rsl, is(excepted));
    }

    @Test
    public void testFindAllUsers() {
        var first = new User("First", "first@gmail.com", "123");
        var second = new User("Second", "second@gmail.com", "435");
        store.save(first);
        store.save(second);
        Collection<User> excepted = List.of(first, second);
        var rsl = store.findAllUsers();
        assertThat(rsl, is(excepted));
    }

    @Test
    public void testSavePost() {
        var first = new Post(0, "First Post");
        store.save(first);
        assertThat(first, is(store.findPostById(first.getId())));
        var updatedFirst = new Post(first.getId(), "Updated First Post");
        store.save(updatedFirst);
        var rsl = store.findPostById(updatedFirst.getId());
        assertThat(rsl, is(updatedFirst));
    }

    @Test
    public void testSaveCandidate() {
        var first = new Candidate(0, "First Candidate");
        store.save(first);
        assertThat(first, is(store.findCandidateById(first.getId())));
        var updatedFirst = new Candidate(first.getId(), "Updated First Candidate");
        store.save(updatedFirst);
        var rsl = store.findCandidateById(updatedFirst.getId());
        assertThat(rsl, is(updatedFirst));
    }

    @Test
    public void testSaveUser() {
        var first = new User("First User", "email@yandex.ru", "qwerty123");
        store.save(first);
        assertThat(first, is(store.findUserById(first.getId())));
        var updatedFirst = new User(first.getId(),
                "Updated First User",
                "email@yandex.ru",
                "qwerty123");
        store.save(updatedFirst);
        var rsl = store.findUserById(updatedFirst.getId());
        assertThat(rsl, is(updatedFirst));
    }

    @Test
    public void testFindPostById() {
        var first = new Post(0, "new post");
        store.save(first);
        var rsl = store.findPostById(first.getId());
        assertThat(rsl, is(first));
    }

    @Test
    public void testFindCandidateById() {
        var first = new Candidate(0, "new candidate");
        store.save(first);
        var rsl = store.findCandidateById(first.getId());
        assertThat(rsl, is(first));
    }

    @Test
    public void testFindUserById() {
        var first = new User("User", "email@gmail.com", "123");
        store.save(first);
        var rsl = store.findUserById(first.getId());
        assertThat(rsl, is(first));
    }

    @Test
    public void testFindUserByEmail() {
        var user = new User("New User", "email@yandex.ru", "321");
        store.save(user);
        var rsl = store.findUserByEmail(user.getEmail());
        assertThat(rsl, is(user));
    }

    @Test
    public void testDelCandidate() {
        var first = new Candidate(0, "new candidate");
        store.save(first);
        assertThat(first, is(store.findCandidateById(first.getId())));
        assertThat(store.delCandidate(first.getId()), is(true));
    }

    @Test
    public void testDelPost() {
        var first = new Post(0, "new post");
        store.save(first);
        assertThat(first, is(store.findPostById(first.getId())));
        store.delPost(first.getId());
        var posts = store.findAllPosts();
        assertThat(posts, is(Collections.emptyList()));
    }

    @Test
    public void testDelUser() {
        var first = new User("User", "mail@yandex.ru", "333");
        store.save(first);
        assertThat(first, is(store.findUserById(first.getId())));
        store.delUser(first.getId());
        var posts = store.findAllUsers();
        assertThat(posts, is(Collections.emptyList()));
    }
}