package testScript;

import static constant.HTTPStatusCode.SUCCESSFULL_CREATED;

import base.ServiceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import constant.ConstantPath;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojos.requestPojoClasses.createUserPojo.Users;
import utils.PropertiesConfiguration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PostResourceName extends TestBase {

	private static Logger log = Logger.getLogger(PostResourceName.class.getName());

	static PropertiesConfiguration propertiesConfiguration;
	static String baseURL;
	static String createUser;

	@BeforeClass()
	public static void beforeClass() {
		propertiesConfiguration = new PropertiesConfiguration(ConstantPath.APPLICATION_FILE);
		log.trace("Application properties File load in memeory");
		baseURL = propertiesConfiguration.getPropertyValue("baseURL");
		log.trace("Getting Base URL : " + baseURL);
		createUser = propertiesConfiguration.getPropertyValue("createUser");
		log.trace("Getting service URL : " + createUser);
	}

	@Test
	@Description("POST api call to create users")
	@Severity(SeverityLevel.CRITICAL)
	@Epic("Epic for post response")
	public void getUserList() {
		String apiName = baseURL + createUser;
		log.debug("Api Name : " + apiName);
		Users user = new Users();
		user.setName("morpheus");
		user.setJob("zion resident");
		String requestBody = null;
		StringEntity entity = null;
		try {
			requestBody = mapper.writeValueAsString(user);
			entity = new StringEntity(requestBody);
		} catch (JsonProcessingException | UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
		CloseableHttpResponse response = httpHelper.serviceCallWithAPIName(apiName, "post", entity);
		log.info("Response Status Code : " + response.getStatusLine().getStatusCode());
		Assert.assertEquals(response.getStatusLine().getStatusCode(), SUCCESSFULL_CREATED);
		log.info("Valid Response Status Code");
		String responseBody = ServiceUtils.getResponseBody(response);
		Users users = null;
		try {
			users = mapper.readValue(responseBody, Users.class);
		} catch (IOException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		log.info("User ID : " + users.getId());
		log.info("User Create At : " + users.getCreatedAt());
	}
}
