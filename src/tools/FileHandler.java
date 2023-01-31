/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.io.*;
import java.util.*;

/**
 *
 * @author Administrator
 */
public class FileHandler {

    private final static String SYSPATH = new File("").getAbsolutePath();

    private static String initPath(String path) {
        return SYSPATH + path;
    }

    public static ArrayList<String> readFromFile(String path) {
        String _path = initPath(path);
        File file = new File(_path);
        ArrayList<String> dta = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                dta.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
        return dta;
    }

    public static boolean writeToFile(String filePath, ArrayList<String> dta) {
        String _path = initPath(filePath);
        try {
            File file = new File(_path);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (String line : dta) {
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
        return true;
    }
}
