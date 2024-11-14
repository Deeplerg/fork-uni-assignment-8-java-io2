package com.example.task02;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        List<Path> paths = listFiles(Paths.get("task02/src/main/resources/"));
        System.out.println(paths);

    }

    public static List<Path> listFiles(Path rootDir) throws IOException {
        try (Stream<Path> pathStream = Files.walk(rootDir)) {
            return pathStream
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
    }


    // simple
//    public static List<Path> listFiles(Path rootDir) {
//        ArrayList<Path> paths = new ArrayList<>();
//
//        try (Stream<Path> pathStream = Files.list(rootDir)) {
//            pathStream.forEach(path -> {
//                if(Files.isDirectory(path)) {
//                    try {
//                        paths.addAll(listFiles(path));
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                if(Files.isRegularFile(path))
//                    paths.add(path);
//            });
//        }
//
//        return paths;
//    }
}
