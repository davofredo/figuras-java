package com.david.classes;

import com.david.classes.services.application.ApplicationService;
import java.io.IOException;

public class Main {
    public static void main(String[] args)
            // TODO: Ideally, the application should be able to identify what caused the IOException
            //  and provide an accurate feedback to the user. As this application is taking advantage of JOptionPane,
            //  a user friendly message, could be shown in an error alert before closing the application.
            //  Just letting the application crash due to an IOException is not a good approach
            throws IOException
    {
        ApplicationService applicationService = new ApplicationService();
        applicationService.start();
        // TODO: No need to use System.exit(0).
        //  At this point, the application is going exit by itself
        System.exit(0);
    }

}