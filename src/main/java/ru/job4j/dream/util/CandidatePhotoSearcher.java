package ru.job4j.dream.util;

import java.io.File;

public class CandidatePhotoSearcher implements PhotoSearcher {

    @Override
    public File search(String name) {
        String folder = Config.getProperty("CandidatePhotos");
        File rsl = null;
        for (File file : new File(folder).listFiles()) {
            var fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            if (name.equals(fileName)) {
                rsl = file;
                break;
            }
        }
        return rsl;
    }
}
