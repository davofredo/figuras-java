package com.david.classes.services.application;

import com.david.classes.abstracts.Figure2D;
import com.david.classes.constants.*;
import com.david.classes.excepciones.InterruptedProcessException;
import handlers.InputHandler;
import com.david.classes.services.file.OpenFileService;
import com.david.classes.services.file.WriteFileService;
import handlers.FileHandler;

import java.text.NumberFormat;
import java.util.Locale;

public class ProcessService {

    public static void writeFileFigure(Figure2D figure2D, FigureTypeEnum figureType,String path, String mainDirectory, String extension, String unitMeasure) throws InterruptedProcessException {
        if( figure2D != null){
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            format.setMaximumFractionDigits(2);
            String message = String.format(Message.FINAL_MESSAGE, figureType.getName(), format.format(figure2D.calculatePerimeter()), unitMeasure, format.format(figure2D.calculateArea()), unitMeasure, figure2D.getAttributes(), unitMeasure);
            String prompt = String.format(Message.FINAL_MESSAGE_PROMPT, message);
            String fileName = InputHandler.getFileName(prompt, extension);
            if(fileName != null){
                Thread thread = new Thread(new WriteFileService(path, mainDirectory, fileName, message));
                thread.start();
                return;
            }
            throw new InterruptedProcessException(Message.INVALID_VALUE, ProcessesEnum.TYPE_OPTION_MENU);
        }
    }

    public static String getFigureOptions(){
        FigureTypeEnum[] figures = FigureTypeEnum.values();
        StringBuilder sb = new StringBuilder(Message.TYPE_FIGURE_OPTION);
        for(FigureTypeEnum f : figures) {
            sb.append(String.format(Message.FORMAT_OPTION, f.getOption(), f.getName()));
        }
        return sb.toString();
    }

    public static String getMenuOptions(){
        MenuEnum[] menuOptions = MenuEnum.values();
        StringBuilder sb = new StringBuilder(Message.TYPE_MENU_OPTION);
        for(MenuEnum option : menuOptions) {
            sb.append(String.format(Message.FORMAT_OPTION, option.getOption(), option.getMessage()));
        }
        return sb.toString();
    }

    public static String getDirectoryOptions(String[] subdirectories) throws InterruptedProcessException {
        if(subdirectories == null) throw new InterruptedProcessException(Message.NOT_FOUND_FILE, ProcessesEnum.TYPE_OPTION_MENU);
        StringBuilder sb = new StringBuilder(Message.TYPE_DIRECTORY_OPTION);
        for(int i = 0; i < subdirectories.length ; i++){
            sb.append(String.format(Message.FORMAT_OPTION, i+1, subdirectories[i]));
        }
        return sb.toString();
    }

    public static String getFilesOptions(String[] files){
        StringBuilder sb = new StringBuilder(Message.TYPE_FILE_OPTIONS);
        for(int i = 0; i < files.length ; i++){
            sb.append(String.format(Message.FORMAT_OPTION, i+1, files[i]));
        }
        return sb.toString();
    }

    public static String selectDirectory(String path, String mainDirectory) throws InterruptedProcessException {
        String[] subdirectories = FileHandler.findSubdirectories(path, mainDirectory);
        return InputHandler.getOption(subdirectories, getDirectoryOptions(subdirectories));
    }

    public static void openFiles(String path, String mainDirectory, String subdirectory) throws InterruptedProcessException {
        if(subdirectory != null){
            String [] files =  FileHandler.findFiles(path, mainDirectory, subdirectory);
            if(files == null) throw new InterruptedProcessException(Message.NOT_FOUND_FILE, ProcessesEnum.SELECT_DIRECTORY);
            String[] Files = InputHandler.getSelectedFiles(files, getFilesOptions(files));

            if( Files != null){
                for (String file: Files) {
                    Thread thread = new Thread(new OpenFileService(path, mainDirectory,subdirectory, file));
                    thread.start();
                }
                return;
            }
        }
        throw new InterruptedProcessException(Message.INVALID_VALUE, ProcessesEnum.SELECT_DIRECTORY);
    }

}
