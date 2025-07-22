package com.bookstore.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileHandler {
    public static boolean isFileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static boolean createFile(String fileName) {
        File file = new File(fileName);
        boolean state = false;
        try {
            state = file.createNewFile();
        } catch (Exception e) {
            System.out.println("Error creating file..." + fileName);
        }
        return state;
    }

    public static boolean writeToFile(String fileName, boolean append, String data) {
        boolean fileCreated = true;
        if (!isFileExist(fileName)) {
            fileCreated = createFile(fileName);
        }
        if (fileCreated) {
            try {
                FileWriter fileWriter = new FileWriter(fileName, append);
                fileWriter.write(data);
                fileWriter.close();
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    public static String[] readFromFile(String fileName) {
        if (!isFileExist(fileName)) {
            return new String[0];
        }
        String data = "";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferReaderFile = new BufferedReader(fileReader);
            String line;
            while ((line = bufferReaderFile.readLine()) != null) {
                data = data + line + "\n";
            }
            fileReader.close();
            bufferReaderFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (data.isEmpty()) {
            return new String[0];
        }
        return data.split("\n");
    }
}