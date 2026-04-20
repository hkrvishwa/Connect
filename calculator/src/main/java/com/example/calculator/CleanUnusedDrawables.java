package com.example.calculator;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class CleanUnusedDrawables {

    @SuppressWarnings("NewApi")
    public static void main(String[] args) throws Exception {

        String projectPath = "";

         Path resPath = Paths.get(projectPath, "app", "src", "main", "res");

        Files.walk(resPath)
                .filter(path -> path.toString().contains("drawable"))
                .filter(Files::isRegularFile)
                .filter(CleanUnusedDrawables::isDrawableFile)
                .forEach(path -> {
                    try {
                        String fileName = path.getFileName().toString();

                        // remove extension
                        String key = fileName.substring(0, fileName.lastIndexOf('.'));

                        if (!isUsed(projectPath, key)) {
                            //Files.delete(path);
                            System.out.println("❌ DELETED: " + path);
                        } else {
                            System.out.println("✅ USED: " + key);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        System.out.println("\n🎯 Drawable cleanup complete!");
    }

    private static boolean isDrawableFile(Path path) {
        String name = path.toString().toLowerCase();
        return name.endsWith(".png") ||
                name.endsWith(".jpg") ||
                name.endsWith(".jpeg") ||
                name.endsWith(".webp") ||
                name.endsWith(".xml"); // vector drawables
    }

    @SuppressWarnings("NewApi")
    private static boolean isUsed(String projectPath, String key) throws IOException {

        final boolean[] found = {false};

        try (Stream<Path> paths = Files.walk(Paths.get(projectPath))) {

            paths
                    .filter(path -> !path.toString().contains("build"))
                    .filter(path -> !path.toString().contains(".gradle"))
                    .filter(path -> path.toString().endsWith(".xml")
                            || path.toString().endsWith(".java")
                            || path.toString().endsWith(".kt")
                            || path.toString().endsWith(".groovy"))
                    .forEach(path -> {
                        try {
                            String text = new String(Files.readAllBytes(path));

                            if (text.contains("R.drawable." + key) ||
                                    text.contains("@drawable/" + key)) {
                                found[0] = true;
                            }

                        } catch (Exception ignored) {}
                    });
        }

        return found[0];
    }
}
