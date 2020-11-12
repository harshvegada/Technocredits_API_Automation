package testScript;

import constant.ConstantPath;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PropertiesConfiguration;

import static constant.HTTPStatusCode.NO_CONTENT_FOUND;

public class DeleteResource extends TestBase {

	private static Logger log = Logger.getLogger(DeleteResource.class.getName());

	static PropertiesConfiguration propertiesConfiguration;
	static String baseURL;
	static String deleteUser;

	@BeforeClass()
	public static void beforeClass() {
		propertiesConfiguration = new PropertiesConfiguration(ConstantPath.APPLICATION_FILE);
		log.trace("Application properties File load in memeory");
		baseURL = propertiesConfiguration.getPropertyValue("baseURL");
		log.trace("Getting Base URL : " + baseURL);
		deleteUser = propertiesConfiguration.getPropertyValue("deleteUser");
		log.trace("Getting service URL : " + deleteUser);
	}

	@Test
	@Description("DELETE api call to delete users")
	@Severity(SeverityLevel.MINOR)
	@Epic("Epic for delete response")
	public void getUserList() {
		String apiName = baseURL + deleteUser;
		log.debug("Api Name : " + apiName);
		CloseableHttpResponse response = httpHelper.serviceCallWithAPIName(apiName, "Delete", null);
		log.info("Response Status Code : " + response.getStatusLine().getStatusCode());
		Assert.assertEquals(response.getStatusLine().getStatusCode(), NO_CONTENT_FOUND);
		log.info("Valid Response Status Code");
		log.info("User Deleted Successfully..");
	}

}
