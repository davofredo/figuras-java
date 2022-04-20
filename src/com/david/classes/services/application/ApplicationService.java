package com.david.classes.services.application;
import com.david.classes.abstracts.Figure2D;
import com.david.classes.constants.*;
import com.david.classes.excepciones.InterruptedProcessException;

import handlers.PropertyHandler;
import javax.swing.JOptionPane;
import java.io.IOException;
import static handlers.InputHandler.*;
import static com.david.classes.services.figures.FigureService.enterFigureValues;
import static com.david.classes.services.application.ProcessService.*;

public class ApplicationService {

    public void start() throws IOException {
        // TODO: It would be better to move this constants out from this method and turn them into static constants
        final String PROP_EXTENSION = "system.extension";
        final String PROP_MAIN_DIRECTORY = "system.directory";
        final String PROP_UNIT_MEASURE = "system.unit.measure";
        final String PROP_PATH = "system.path";
        // TODO: Instead of just letting the IOException pass through,
        //  you could catch it here and throw a custom exception,
        //  containing a user friendly message like "The application is not able to create a properties file".
        PropertyHandler.load("/application-default.properties", "application.properties");
        String mainDirectory = PropertyHandler.getStringProperty(PROP_MAIN_DIRECTORY);
        String extension = PropertyHandler.getStringProperty(PROP_EXTENSION);
        String unitMeasure = PropertyHandler.getStringProperty(PROP_UNIT_MEASURE);
        String path = PropertyHandler.getStringProperty(PROP_PATH);
        FigureTypeEnum figureType = null;
        Figure2D figure2D = null;
        String selectedDirectory =null;
        ProcessesEnum currentProcess = ProcessesEnum.TYPE_OPTION_MENU;
        do {
            try {
                switch (currentProcess) {
                    case TYPE_OPTION_MENU:
                        currentProcess = menu();
                        break;
                    case SELECT_DIRECTORY:
                         selectedDirectory = selectDirectory(path, mainDirectory);
                    case SELECT_FILES:
                        // TODO: Handling sub-stages in the same switch complicates the code more than necessary
                        openFiles(path, mainDirectory, selectedDirectory);
                        currentProcess = ProcessesEnum.TYPE_OPTION_MENU;
                        break ;
                    case TYPE_FIGURE:
                        figureType = getFigureType(getFigureOptions());
                    case TYPE_FIGURE_VALUES:
                        // TODO: Handling sub-stages in the same switch complicates the code more than necessary
                        figure2D = enterFigureValues(figureType, unitMeasure);
                    case TYPE_FILENAME:
                        // TODO: Handling sub-stages in the same switch complicates the code more than necessary
                        writeFileFigure(figure2D, figureType, path, mainDirectory,extension, unitMeasure);
                        currentProcess = ProcessesEnum.TYPE_OPTION_MENU;
                }
            } catch (InterruptedProcessException ex) {
                // TODO: Using exception details to drive application transitions is nice,
                //  but carrying the process code into the exception, could lead to application flow issues.
                //  (It actually happened in another project that used this same approach)
                currentProcess = ex.getProcess();
                if (ex.getMessage() != null) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } while (true);
    }

    private ProcessesEnum menu() throws InterruptedProcessException {
        MenuEnum menuOption = getMenuOption(ProcessService.getMenuOptions());
        switch (menuOption) {
            case OPEN:
                return ProcessesEnum.SELECT_DIRECTORY;
            case REGISTER:
                return ProcessesEnum.TYPE_FIGURE;
            case EXIT:
                getShowConfirmExitDialog(ProcessesEnum.TYPE_OPTION_MENU);
        }
        return ProcessesEnum.TYPE_OPTION_MENU;
    }
}
