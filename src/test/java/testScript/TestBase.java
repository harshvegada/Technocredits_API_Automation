package testScript;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

import com.fasterxml.jackson.databind.ObjectMapper;

import constant.ConstantPath;
import helper.HTTPHelper;

public class TestBase {

	private static Logger log = Logger.getLogger(TestBase.class.getName());

	public static HTTPHelper httpHelper = HTTPHelper.getInstace();
	protected ObjectMapper mapper = new ObjectMapper();

	@BeforeSuite
	public void beforeSuite() {
		PropertyConfigurator.configure(ConstantPath.LOG4J_FILE);
		log.trace("Log 4j file load in memory");
		deleteAllHistoricalAllureReport();
		log.info("Delete Allure report historical data.");
		addPropertiesFileToAllureReport();
		log.info("Allure report values added in project");
	}

	/**
	 * This method will delete all the historical data of allure report so it will
	 * not give wrong report after execution
	 */
	private static void deleteAllHistoricalAllureReport() {
		File file = new File(System.getProperty("user.dir") + File.separator + "allure-results" + File.separator);
		File[] files = file.listFiles();
		for (File currentFile : files) {
			if (currentFile.isDirectory()) {
				deleteFile(currentFile);
			} else {
				log.debug("File deleted : " + currentFile.getName());
				currentFile.delete();
			}
		}
	}

	private static void deleteFile(File file) {
		for (File currentFile : file.listFiles()) {
			if (currentFile.isDirectory()) {
				deleteFile(currentFile);
			} else {
				log.debug("File deleted : " + currentFile.getName());
				currentFile.delete();
			}
		}
	}

	/*
	 * This method will add value like on which parameter we are running Test cases
	 */
	private void addPropertiesFileToAllureReport() {
		Properties prop = new Properties();
		prop.setProperty("Environment", "QA");
		prop.setProperty("Testing_Framework", "TestNG");
		prop.setProperty("Build_Tool", "Maven");
		prop.setProperty("Integration_Tool", "Jenkins");
		log.debug("Allure report value's added to properties file..");

		try {
			prop.store(new FileOutputStream(new File("allure-results" + File.separator + "environment.properties")),
					"Adding Properties File to allure-results file");
		} catch (IOException e) {
			log.fatal("Unable to write on Environment properties file");
			e.printStackTrace();
		}
		log.debug("Allure report value's set to properties file..");
	}
}
