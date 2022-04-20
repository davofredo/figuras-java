package com.david.classes.services.file;

import com.david.classes.constants.Message;
import handlers.FileHandler;
import handlers.InputHandler;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WriteFileService implements Runnable {

    private final String message;
    private final String fileName;
    private final String mainDirectory;
    private final String path;

    public WriteFileService(String path, String mainDirectory, String fileName, String message) {
        this.path = path;
        this.mainDirectory = mainDirectory;
        this.fileName = fileName;
        this.message = message;
    }

    @Override
    public void run() {
            try{
                JFrame f = new JFrame();
                f.requestFocusInWindow();
                DateTimeFormatter isoDate = DateTimeFormatter.ISO_LOCAL_DATE;
                LocalDate now = LocalDate.now();
                String directoryName = now.format(isoDate);

                FileHandler.createDirectory(path, mainDirectory);

                StringBuilder pathFile = new StringBuilder(path);

                pathFile.append(File.separator).append(mainDirectory)
                        .append(File.separator).append(directoryName)
                        .append(File.separator).append(fileName);

                FileHandler.createDirectory(mainDirectory,directoryName);
                File file = new File(pathFile.toString());
                if(file.exists()) {
                    int confirmation = InputHandler.getShowConfirmDialog(Message.CONFIRMATION_DELETE);
                    if (confirmation == 0) {
                        if (!file.delete())
                            JOptionPane.showMessageDialog(null, Message.ERROR);
                        FileHandler.writeFile(pathFile.toString(), message);
                        if(file.exists())
                            JOptionPane.showMessageDialog(f, String.format(Message.SUCCESS_MESSAGE, fileName));
                        else
                            JOptionPane.showMessageDialog(f, String.format(Message.ERROR, fileName));
                    }
                }
                // TODO: What if file doesn't exist?
            }catch (IOException e){
                System.err.println(e.getClass());
            }
    }
}
