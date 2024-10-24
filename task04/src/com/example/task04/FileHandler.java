package com.example.task04;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class FileHandler implements MessageHandler {
    private final String Path;

    public FileHandler(String path) {
        Path = path;
    }

    @Override
    public void handle(String message) {
        File file = new File(Path);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(message.getBytes());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}