package handlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.awt.Desktop;
import javax.swing.JOptionPane;

import com.david.classes.constants.Message;

public class FileHandler {
    public synchronized static void createDirectory(String... args) {
        StringBuilder completePath = new StringBuilder();
        for(String directory: args){
            completePath.append(directory).append(File.separator);
        }
        File directory = new File(completePath.toString());
        // TODO: Do not assume that the directory was created. Use the returned value for feedback
        //  and handle the scenario when the directory could not be created.
        directory.mkdir();
    }

    public synchronized static void writeFile(String fileName, String line) throws IOException{
        try (PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8)){
            writer.println(line);
        }
    }

    public static String[] findSubdirectories(String... args){
        StringBuilder completePath = new StringBuilder();
        for(String directory: args){
            completePath.append(directory).append(File.separator);
        }
        File dir = new File(completePath.toString());
        if(dir.listFiles() == null) return null;
        return Arrays.stream(new File(completePath.toString()).listFiles(File::isDirectory)).map(File::getName).toArray(String[]::new);
    }

    public static String[] findFiles(String... args){
        StringBuilder completePath = new StringBuilder();
        for(String directory: args){
            completePath.append(directory).append(File.separator);
        }
        File dir = new File(completePath.toString());
        if(dir.listFiles() == null) return null;
        return Arrays.stream(new File(completePath.toString()).listFiles(File::isFile)).map(File::getName).toArray(String[]::new);
    }

    public synchronized static void openFile(String filename) throws IOException{
        File file = new File(filename);
        if(!Desktop.isDesktopSupported()){
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
    }

}
