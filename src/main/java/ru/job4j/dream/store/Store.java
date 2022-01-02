package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private static final Store INST = new Store();
    private final static AtomicInteger POST_ID = new AtomicInteger(4);
    private final static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private Store() {
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

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public Post findPostById(int id) {
        return posts.get(id);
    }

    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public void save(Candidate candidate) {
        candidate.setId(CANDIDATE_ID.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
    }
}
