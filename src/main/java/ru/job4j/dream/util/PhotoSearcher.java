package ru.job4j.dream.util;

import java.io.File;

public class PhotoSearcher {

    public File search(String name) {
        File rsl = null;
        for (File file : new File("c:\\images\\").listFiles()) {
            var fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            if (name.equals(fileName)) {
                rsl = file;
                break;
            }
        }
        return rsl;
    }
}
