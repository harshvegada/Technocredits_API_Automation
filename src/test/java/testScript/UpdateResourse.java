package testScript;

import static constant.HTTPStatusCode.SUCCESSFULL_OK;

import base.ServiceUtils;
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

public class UpdateResourse extends TestBase {

	private static Logger log = Logger.getLogger(UpdateResourse.class.getName());

	static PropertiesConfiguration propertiesConfiguration;
	static String baseURL;
	static String updateUser;

	@BeforeClass()
	public static void beforeClass() {
		propertiesConfiguration = new PropertiesConfiguration(ConstantPath.APPLICATION_FILE);
		log.trace("Application properties File load in memeory");
		baseURL = propertiesConfiguration.getPropertyValue("baseURL");
		log.trace("Getting Base URL : " + baseURL);
		updateUser = propertiesConfiguration.getPropertyValue("updateUser");
		log.trace("Getting service URL : " + updateUser);
	}

	@Test
	@Description("PUT api call to update users")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic for put response")
	public void getUserList() throws Exception {
		String apiName = baseURL + updateUser;
		log.debug("Api Name : " + apiName);
		Users user = new Users();
		user.setName("morpheus");
		user.setJob("zion resident");
		String userBody = null;
		userBody = mapper.writeValueAsString(user);
		StringEntity entity = new StringEntity(userBody);
		CloseableHttpResponse response = httpHelper.serviceCallWithAPIName(apiName, "PUT", entity);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), SUCCESSFULL_OK);
		log.info("Valid Response Status Code " + SUCCESSFULL_OK);
		String responseBody = ServiceUtils.getResponseBody(response);
		UpdateResourceNamePojo UpdateResourceNamePojo = mapper.readValue(responseBody, UpdateResourceNamePojo.class);
		log.info("User Updated At  : " + UpdateResourceNamePojo.getUpdatedAt());

	}
}
