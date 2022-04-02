package com.david.classes;

import com.david.classes.services.application.ApplicationService;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationService applicationService = new ApplicationService();
        applicationService.start();
        System.exit(0);
    }

}