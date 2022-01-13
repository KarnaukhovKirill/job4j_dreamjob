package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemStore implements Store {
    private static final MemStore INST = new MemStore();
    private final static AtomicInteger POST_ID = new AtomicInteger(4);
    private final static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    private final static AtomicInteger USER_ID = new AtomicInteger(0);
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private MemStore() {
        posts.put(1, new Post(1,
                "Junior Java Job",
                new GregorianCalendar(2021, Calendar.DECEMBER, 26, 13, 1),
                "Вакансия для джуна"));
        posts.put(2, new Post(2,
                "Middle Java Job",
                new GregorianCalendar(2021, Calendar.DECEMBER, 23, 14, 55),
                "Вакансия для мидла"));
        posts.put(3, new Post(3,
                "Senior Java Job",
                new GregorianCalendar(2020, Calendar.DECEMBER, 31, 23, 59),
                "Вакансия для синьора- помидора"));
        candidates.put(1, new Candidate(1, "Анатолий"));
        candidates.put(2, new Candidate(2, "Виктория"));
        candidates.put(3, new Candidate(3, "Зинаида"));
    }

    public static MemStore instOf() {
        return INST;
    }

    @Override
    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    @Override
    public Collection<User> findAllUsers() {
        return users.values();
    }

    @Override
    public Post findPostById(int id) {
        return posts.get(id);
    }

    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }

    @Override
    public User findUserById(int id) {
        return users.get(id);
    }

    @Override
    public User findUserByEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(USER_ID.incrementAndGet());
        }
        users.put(user.getId(), user);
    }

    public boolean delCandidate(int id) {
        return candidates.remove(id, findCandidateById(id));
    }

    @Override
    public boolean delPost(int id) {
        return posts.remove(id, findPostById(id));
    }

    @Override
    public boolean delUser(int id) {
        return users.remove(id, findUserById(id));
    }

    @Override
    public void delAllPosts() {
        posts.clear();
    }

    @Override
    public void delAllCandidates() {
        candidates.clear();
    }

    @Override
    public void delAllUsers() {
        users.clear();
    }
}
