package handlers;

import com.david.classes.constants.Message;
import com.david.classes.constants.MenuEnum;
import com.david.classes.constants.ProcessesEnum;
import com.david.classes.constants.FigureTypeEnum;
import com.david.classes.excepciones.InterruptedProcessException;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.HeadlessException;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class InputHandler {
    public static FigureTypeEnum getFigureType(String message) throws InterruptedProcessException {
        ProcessesEnum process = ProcessesEnum.TYPE_FIGURE;
        try {
            String stringValue = JOptionPane.showInputDialog(message);
            if (stringValue == null) throw new NullPointerException();
            int selectedOption = Integer.parseInt(stringValue);
            return Stream.of(FigureTypeEnum.values()).filter(f -> f.getOption() == selectedOption).findFirst().orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException | NumberFormatException ex) {
            throw new InterruptedProcessException(Message.INVALID_FIGURE, process);
        } catch (NullPointerException ex) {
            getShowConfirmExitDialog(process);
        }
        return null;
    }

    public static MenuEnum getMenuOption(String prompt) throws InterruptedProcessException {
        ProcessesEnum process = ProcessesEnum.TYPE_OPTION_MENU;
        try {
            String stringValue = JOptionPane.showInputDialog(prompt);
            if (stringValue == null) throw new NullPointerException();
            int selectedOption = Integer.parseInt(stringValue);
            return Stream.of(MenuEnum.values()).filter(f -> f.getOption() == selectedOption).findFirst().orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException | NumberFormatException ex) {
            throw new InterruptedProcessException(Message.INVALID_OPTION, process);
        } catch (NullPointerException ex) {
            getShowConfirmExitDialog(process);
        }
        return null;
    }

    public static double getPositiveDouble(String prompt) throws InterruptedProcessException {
        //TODO handle big numbers
        ProcessesEnum process = ProcessesEnum.TYPE_FIGURE_VALUES;
        double value = 0;
        try{
            String stringValue = JOptionPane.showInputDialog(null, prompt);
            if (stringValue == null) throw new NullPointerException();
            value = Double.parseDouble(stringValue);
            if ( value < 0) throw new NumberFormatException();
        }catch (HeadlessException | NumberFormatException e){
            throw new InterruptedProcessException(Message.INVALID_VALUE, process);
        }catch (NullPointerException e){
            getShowConfirmExitDialog(process);
        }
        return value;
    }

    public static void getShowConfirmExitDialog(ProcessesEnum process)  throws InterruptedProcessException {
        JFrame f =new JFrame();
        f.requestFocusInWindow();
        int selectedOption = JOptionPane.showConfirmDialog(f, Message.CONFIRMATION_MESSAGE);
        // TODO: Instead of calling System::exit, you could throw a custom exception
        //  to instruct the ApplicationService that is time to finish the main loop.
        if(selectedOption == 0) System.exit(0);
        if(process == null) process = ProcessesEnum.TYPE_OPTION_MENU;
        throw new InterruptedProcessException(process);
    }

    public static int getShowConfirmDialog(String prompt){
        JFrame f =new JFrame();
        f.requestFocusInWindow();
        int confirmation = JOptionPane.showConfirmDialog(f, prompt);
        return confirmation;
    }

    public static String getOption(String[] options, String prompt) throws InterruptedProcessException {
        ProcessesEnum process = ProcessesEnum.SELECT_DIRECTORY;
        try {
            String stringValue = JOptionPane.showInputDialog(prompt);
            if (stringValue == null) throw new NullPointerException();
            int selectedOptions = Integer.parseInt(stringValue);
            return options[selectedOptions-1];

        } catch (NoSuchElementException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            throw new InterruptedProcessException(Message.INVALID_OPTION, process);
        } catch (NullPointerException ex) {
            getShowConfirmExitDialog(process);
        }
        return null;
    }

    public static String [] getSelectedFiles(String[] optionFiles, String prompt) throws InterruptedProcessException {
        ProcessesEnum process = ProcessesEnum.SELECT_FILES;
        try {
            String stringValue = JOptionPane.showInputDialog(prompt);
            if (stringValue == null) throw new NullPointerException();
            String regex = "[0-9]+(,[0-9]+)*";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(stringValue);
            if (!matcher.matches()) throw new InterruptedProcessException(Message.INVALID_VALUE, process);
            int [] selectedOptions = Stream.of(stringValue.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            String[] selectedFiles = new String[selectedOptions.length];
            for (int i = 0; i < selectedOptions.length; i++) selectedFiles[i] = optionFiles[selectedOptions[i]-1];

            return selectedFiles;

        } catch (NoSuchElementException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            throw new InterruptedProcessException(Message.INVALID_OPTION, process);
        } catch (NullPointerException ex) {
            getShowConfirmExitDialog(process);
        }

        return null;
    }

    public static String getFileName(String prompt, String extension) throws InterruptedProcessException {
        JFrame f = new JFrame();
        f.requestFocusInWindow();
        ProcessesEnum process = ProcessesEnum.TYPE_FILENAME;
        String regex = "\\w+\\.\\w+";
        Pattern pattern = Pattern.compile(regex);
        String stringValue = null;

        try{
            stringValue = JOptionPane.showInputDialog(f, prompt);
            if (stringValue == null) throw new NullPointerException();
            stringValue += extension;
            Matcher matcher = pattern.matcher(stringValue);
            if (!matcher.matches()) throw new InterruptedProcessException(Message.INVALID_NAME, process);
        } catch (NullPointerException e){
            getShowConfirmExitDialog(process);
        }

        return stringValue;
    }

}
