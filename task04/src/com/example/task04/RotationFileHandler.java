package com.example.task04;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String Path;
    private final ChronoUnit Rotation;

    public RotationFileHandler(String path, ChronoUnit rotation) {
        Path = path;
        Rotation = rotation;
    }

    @Override
    public void handle(String message) {
        LocalDateTime dateNow = LocalDateTime.now().truncatedTo(Rotation);
        File file = new File(Path + dateNow.toString().replace(':', '#') + ".txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(message.getBytes());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
