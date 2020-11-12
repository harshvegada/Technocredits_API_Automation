package testScript;

import base.ServiceUtils;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import pojos.requestPojoClasses.resourcesPojo.Resources;
import utils.PropertiesConfiguration;

import java.io.IOException;
import static constant.HTTPStatusCode.SUCCESSFULL_OK;

public class DelayInResponse extends TestBase {

	private static Logger log = Logger.getLogger(DelayInResponse.class.getName());

	static PropertiesConfiguration propertiesConfiguration;
	static String baseURL;
	static String delayInResponse;

	@BeforeClass()
	public static void beforeClass() {
		propertiesConfiguration = new PropertiesConfiguration(ConstantPath.APPLICATION_FILE);
		log.trace("Application properties File load in memeory");
		baseURL = propertiesConfiguration.getPropertyValue("baseURL");
		log.trace("Getting Base URL : " + baseURL);
		delayInResponse = propertiesConfiguration.getPropertyValue("delayInResponse");
		log.trace("Getting service URL : " + delayInResponse);
	}

	@Test
	@Description("GET api call with delay in response")
	@Severity(SeverityLevel.CRITICAL)
	@Epic("Epic for get response")
	public void delayResponse() throws JsonParseException, JsonMappingException, IOException {
		String apiName = baseURL + delayInResponse + 3;
		log.debug("Api Name : " + apiName);
		CloseableHttpResponse response = httpHelper.serviceCallWithAPIName(apiName, "GET", null);
			int statusCode = response.getStatusLine().getStatusCode();
			Assert.assertEquals(SUCCESSFULL_OK, statusCode);
			log.info("Valid response : " + statusCode);
			String responseBody = ServiceUtils.getResponseBody(response);
			Resources resources = mapper.readValue(responseBody, Resources.class);
			log.info("Total pages : " + resources.getPage());
			log.info("Total per page user : " + resources.getPerPage());
			log.info("Total per page data : " + resources.getData().size());
			log.info("Total User : " + resources.getTotal());
	}

}
