package ru.job4j.dream.store;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.sql.Timestamp;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DbStoreTest {
    private static Store store;

    @BeforeClass
    public static void init() {
        store = DbStore.instOf();
    }

    @Test
    public void testFindAllPosts() {
        store.delAllPosts();
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
        var first = new Candidate(0, "First Candidate", 1, new Timestamp(System.currentTimeMillis()));
        var second = new Candidate(0, "Second Candidatte", 2, new Timestamp(System.currentTimeMillis()));
        store.save(first);
        store.save(second);
        Collection<Candidate> excepted = List.of(first, second);
        var rsl = new ArrayList<>();
        rsl.add(store.findCandidateById(first.getId()));
        rsl.add(store.findCandidateById(second.getId()));
        assertThat(rsl, is(excepted));
    }

    @Test
    public void testFindAllUsers() {
        store.delAllUsers();
        var first = new User("First", "first@gmail.com", "123");
        var second = new User("Second", "second@gmail.com", "435");
        store.save(first);
        store.save(second);
        Collection<User> excepted = List.of(first, second);
        var rsl = store.findAllUsers();
        assertThat(rsl, is(excepted));
    }

    @Test
    public void testFindAllCities() {
        store.delAllCities();
        var moscow = new City("Moscow");
        var paris = new City("Paris");
        store.save(moscow);
        store.save(paris);
        Collection<City> excepted = List.of(moscow, paris);
        var rsl = new ArrayList<>();
        rsl.add(store.findCityById(moscow.getId()));
        rsl.add(store.findCityById(paris.getId()));
        assertThat(rsl, is(excepted));
    }

    @Test
    public void testFindPostsLastDay() {
        store.delAllPosts();
        Calendar now = Calendar.getInstance();
        var monthAgo = Calendar.getInstance();
        monthAgo.set(2022, Calendar.JANUARY, 10);
        var post = new Post(0, "Vacancy", now, "Description");
        var oldPost = new Post(0, "Old Vacancy", monthAgo, "Description");
        store.save(post);
        store.save(oldPost);
        var expected = List.of(post);
        var rsl = store.findPostsByLastDay();
        assertThat(rsl, is(expected));
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
        var first = new Candidate(0, "First Candidate", 1, new Timestamp(System.currentTimeMillis()));
        store.save(first);
        assertThat(first, is(store.findCandidateById(first.getId())));
        var updatedFirst = new Candidate(first.getId(), "Updated First Candidate", 1, new Timestamp(System.currentTimeMillis()));
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
        var first = new Candidate(0, "new candidate", 1, new Timestamp(System.currentTimeMillis()));
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
        store.delAllUsers();
        var user = new User("New User", "email@yandex.ru", "321");
        store.save(user);
        var rsl = store.findUserByEmail(user.getEmail());
        assertThat(rsl, is(user));
    }

    @Test
    public void testFindCityByTitle() {
        var city = new City("Krasnoyarsk");
        store.save(city);
        assertThat(store.findCityByTitle(city.getTitle()).getTitle(), is(city.getTitle()));
    }

    @Test
    public void testDelCandidate() {
        var first = new Candidate(0, "new candidate", 1, new Timestamp(System.currentTimeMillis()));
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
        assertThat(store.findPostById(first.getId()), is(nullValue()));
    }

    @Test
    public void testDelUser() {
        var first = new User("User", "mail@yandex.ru", "333");
        store.save(first);
        assertThat(first, is(store.findUserById(first.getId())));
        store.delUser(first.getId());
        assertThat(store.findUserById(first.getId()), is(nullValue()));
    }
}