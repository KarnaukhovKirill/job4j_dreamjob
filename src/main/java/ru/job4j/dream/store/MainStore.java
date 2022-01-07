package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

public class MainStore {
    public static void main(String[] args) {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        Candidate candidate = new Candidate(0, "new candidate");
        store.save(post);
        store.save(candidate);
        printPosts(store);
        printCandidates(store);
        System.out.println("------------");
        Post post2 = new Post(post.getId(), "Updated Java Job");
        store.save(post2);
        store.delCandidate(candidate.getId());
        printCandidates(store);
        printPosts(store);
    }

    private static void printPosts(Store store) {
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }
    }

    private static void printCandidates(Store store) {
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName());
        }
    }
}
