package com.example.task01;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);
        System.out.println(extractSoundName(new File("task01\\src\\main\\resources\\3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException {
        String[] command = buildCommand(file);

        ProcessBuilder builder = new ProcessBuilder();
        builder.command(command);
        builder.redirectOutput(ProcessBuilder.Redirect.PIPE);
        Process process = builder.start();

        InputStream processOutput = process.getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processOutput)))
        {
            return reader.readLine();
        }
    }

    private static String[] buildCommand(File file) {
        String commandFormat = "ffprobe -v error -of default=nw=1:nk=1 -show_entries format_tags=title %s";

        String filename = file.getAbsolutePath();
        String formattedCommand = String.format(commandFormat, filename);

        return formattedCommand.split("\\s+");
    }
}
