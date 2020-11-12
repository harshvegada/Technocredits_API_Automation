package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import constant.ConstantPath;

public class PropertiesConfiguration {

    Logger log = Logger.getLogger(this.getClass().getName());
    Properties prop;

    public PropertiesConfiguration(String fileName) {
        String fileLocation = ConstantPath.APPLICATION_FILE;

        File file = new File(fileLocation);
        FileInputStream input = null;

        try {
            input = new FileInputStream(file);
            prop = new Properties();
            prop.load(input);
        } catch (FileNotFoundException e) {
            log.fatal("File Not fount at " + fileLocation);
        } catch (IOException e) {
            log.fatal("Check Correct File access.." + fileName);
        }

    }

    public String getPropertyValue(String key) {
        return prop.getProperty(key);
    }
}
