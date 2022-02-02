package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();
    Collection<Candidate> findAllCandidates();
    Collection<User> findAllUsers();
    Collection<City> findAllCities();
    Collection<Post> findPostsByLastDay();
    Collection<Candidate> findCandidatesByLastDay();
    void save(Post post);
    void save(Candidate candidate);
    void save(User user);
    void save(City city);
    Post findPostById(int id);
    Candidate findCandidateById(int id);
    User findUserById(int id);
    City findCityById(int id);
    City findCityByTitle(String title);
    User findUserByEmail(String email);
    boolean delCandidate(int id);
    boolean delPost(int id);
    boolean delUser(int id);
    boolean delCity(int id);
    void delAllPosts();
    void delAllCandidates();
    void delAllUsers();
    void delAllCities();
}
