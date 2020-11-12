package testScript;

import base.ServiceUtils;

import static constant.HTTPStatusCode.SUCCESSFULL_OK;

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
import pojos.requestPojoClasses.updateResourseDataPojo.UpdateResourceNamePojo;
import utils.PropertiesConfiguration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PatchResourse extends TestBase {

	private static Logger log = Logger.getLogger(PatchResourse.class.getName());

	static PropertiesConfiguration propertiesConfiguration;
	static String baseURL;
	static String patchUser;

	@BeforeClass()
	public static void beforeClass() {
		propertiesConfiguration = new PropertiesConfiguration(ConstantPath.APPLICATION_FILE);
		log.trace("Application properties File load in memeory");
		baseURL = propertiesConfiguration.getPropertyValue("baseURL");
		log.trace("Getting Base URL : " + baseURL);
		patchUser = propertiesConfiguration.getPropertyValue("patchUser");
		log.trace("Getting service URL : " + patchUser);
	}

	@Test
	@Description("PATCH api call to update users")
	@Severity(SeverityLevel.TRIVIAL)
	@Epic("Epic for update response")
	public void getUserList() {
		String apiName = baseURL + patchUser;
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

		CloseableHttpResponse response = httpHelper.serviceCallWithAPIName(apiName, "patch", entity);
		log.info("Response Status Code : " + response.getStatusLine().getStatusCode());

		Assert.assertEquals(response.getStatusLine().getStatusCode(), SUCCESSFULL_OK);
		log.info("Valid Response Status Code");
		String responseBody = ServiceUtils.getResponseBody(response);
		UpdateResourceNamePojo UpdateResourceNamePojo = null;
		try {
			UpdateResourceNamePojo = mapper.readValue(responseBody, UpdateResourceNamePojo.class);
		} catch (IOException e) {
			log.error(e.getMessage());
			log.error(e.getCause());
		}
		log.info("User Updated At  : " + UpdateResourceNamePojo.getUpdatedAt());
	}
}
