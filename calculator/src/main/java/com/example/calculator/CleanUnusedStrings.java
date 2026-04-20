package com.example.calculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanUnusedStrings {

    @SuppressWarnings("NewApi")
    public static void main(String args[]) throws Exception {

        String projectPath = "";
        String stringsPath = "";

        @SuppressWarnings("NewApi") String content = new String(Files.readAllBytes(Paths.get(stringsPath)));

        Pattern pattern = Pattern.compile("<string name=\"(.*?)\">(.*?)</string>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);

        List<String> usedStrings = new ArrayList<>();

        while (matcher.find()) {
            String key = matcher.group(1);

            if (isUsed(projectPath, key)) {
                usedStrings.add(matcher.group(0)); // full string tag
            } else {
                System.out.println("REMOVED: " + key);
            }
        }

        // Write only used strings back
        StringBuilder newContent = new StringBuilder();
        newContent.append("<resources>\n");

        for (String s : usedStrings) {
            newContent.append("    ").append(s).append("\n");
        }

        newContent.append("</resources>");

        Files.write(Paths.get(stringsPath), newContent.toString().getBytes());

        System.out.println("\n✅ Cleanup complete!");
        System.out.println("Remaining strings: " + usedStrings.size());
    }


    @SuppressWarnings("NewApi")
    private static boolean isUsed(String projectPath, String key) throws IOException {

        final boolean[] found = {false};

        Files.walk(Paths.get(projectPath))
                .filter(path -> !path.toString().contains("build"))
                .filter(path -> !path.toString().contains(".gradle"))
                .filter(path -> path.toString().endsWith(".xml")
                        || path.toString().endsWith(".java")
                        || path.toString().endsWith(".groovy")
                        || path.toString().endsWith(".kt"))
                .forEach(path -> {
                    try {
                        String text = new String(Files.readAllBytes(path));

                        if (text.contains("R.string." + key) ||
                                text.contains("@string/" + key)) {
                            found[0] = true;
                        }

                    } catch (Exception ignored) {}
                });

        return found[0];
    }
}