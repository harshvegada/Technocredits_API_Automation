package constant;

import java.io.File;

public class ConstantPath {

	public static final String APPLICATION_FILE = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "application.properties";
	public static final String LOG4J_FILE = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "log4j.properties";

}
