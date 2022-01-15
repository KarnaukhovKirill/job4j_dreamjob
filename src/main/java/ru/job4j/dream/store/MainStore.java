package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

public class MainStore {
    public static void main(String[] args) {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        Candidate candidate = new Candidate(0, "new candidate");
        store.save(post);
        store.save(candidate);
        printPosts(store);
        printCandidates(store);
        User user = new User(0, "First User", "email@yandex.ru", "123");
        store.save(user);
        System.out.println("------------");
        Post post2 = new Post(post.getId(), "Updated Java Job");
        store.save(post2);
        store.delCandidate(candidate.getId());
        printCandidates(store);
        printPosts(store);
        printUsers(store);
    }

    private static void printUsers(Store store) {
        for (User user : store.findAllUsers()) {
            System.out.println(user.getId() + " " + user.getName());
        }
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
