package com.david.classes.services.file;

import handlers.FileHandler;

import java.io.File;
import java.io.IOException;

public class OpenFileService implements Runnable{
    private final String fileName;
    private final String directoryName;
    private final String mainDirectory;
    private final String path;
    public OpenFileService(String path, String mainDirectory, String directoryName, String fileName) {
        this.path = path;
        this.mainDirectory = mainDirectory;
        this.directoryName = directoryName;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try{
            StringBuilder completePath = new StringBuilder(path);
             completePath.append(File.separator).append(mainDirectory)
                         .append(File.separator).append(directoryName)
                         .append(File.separator).append(fileName);
            FileHandler.openFile(completePath.toString());
        }catch (IOException e){
            System.err.println(e);
        }
    }
}
