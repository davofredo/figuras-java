package handlers;

import java.io.*;
import java.util.Properties;

public class PropertyHandler {

    private PropertyHandler() {}

    private static final String PROP_PROPS_FILE = "handler.propsFile";

    /**
     * @param defaultPropsFile from classpath
     * @param configPropsFile anywhere in filesystem
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void load(String defaultPropsFile, String configPropsFile) throws FileNotFoundException, IOException {
        // Load default properties
        Properties defaultProps = new Properties(System.getProperties());
        //FileInputStream in = new FileInputStream(defaultPropsFile);
        InputStream in = PropertyHandler.class.getResourceAsStream(defaultPropsFile);
        defaultProps.load(in);
        in.close();

        // Create application properties
        Properties appProps = new Properties(defaultProps);

        // Load last invocation properties
        try {
            in = new FileInputStream(configPropsFile);
        } catch (FileNotFoundException e) {
            if(!new File(configPropsFile).createNewFile()) {
                // TODO: This implementation would be better if we throw a custom exception
                //  instead of printing a message and closing the app.
                System.err.printf("Failed to create file %s", configPropsFile);
                // TODO: This implementation was used to demonstrate the method System::exit,
                //  but this approach is not advisable for real applications.
                //  The best approach would be to throw a custom exception
                //  and let another method in the call stack to handle it.
                System.exit(1);
            }
            in = new FileInputStream(configPropsFile);
        }
        appProps.load(in);
        in.close();

        System.setProperties(appProps);
        System.setProperty(PROP_PROPS_FILE, configPropsFile);
    }

    public static String getStringProperty(String key) {
        return System.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        System.setProperty(key, value);
    }

    public static void clearProperty(String key) {
        System.getProperties().remove(key);
    }

    public static void persist() throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(System.getProperty(PROP_PROPS_FILE));
        System.getProperties().store(out, "---No Comment---");
        out.close();
    }
}
