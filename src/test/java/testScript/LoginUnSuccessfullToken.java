package testScript;

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
import pojos.requestPojoClasses.resourcesPojo.Data;
import pojos.responsePojos.error.Error;
import utils.PropertiesConfiguration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import static constant.HTTPStatusCode.BAD_REQUEST;


public class LoginUnSuccessfullToken extends TestBase {

    private static Logger log = Logger.getLogger(LoginUnSuccessfullToken.class.getName());

    static PropertiesConfiguration propertiesConfiguration;
    static String baseURL;
    static String loginToken;

    @BeforeClass()
    public static void beforeClass() {
        propertiesConfiguration = new PropertiesConfiguration(ConstantPath.APPLICATION_FILE);
        log.trace("Application properties File load in memeory");
        baseURL = propertiesConfiguration.getPropertyValue("baseURL");
        log.trace("Getting Base URL : " + baseURL);
        loginToken = propertiesConfiguration.getPropertyValue("loginToken");
        log.trace("Getting service URL : " + loginToken);

    }

    @Test
    @Description("POST api call to create users")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Epic for post response")
    public void getUserList() {
        String apiName = baseURL + loginToken;
        log.debug("Api Name : " + apiName);
        log.debug("Api Name : " + apiName);
        Data data = new Data();
        data.setEmail("peter@klaven");
        String requestBody = null;
        StringEntity entity = null;
        try {
            requestBody = mapper.writeValueAsString(data);
            entity = new StringEntity(requestBody);
        } catch (JsonProcessingException | UnsupportedEncodingException e1) {
            log.error(e1.getMessage());
            log.error(e1.getStackTrace());
        }
        CloseableHttpResponse response = httpHelper.serviceCallWithAPIName(apiName, "post", entity);
        log.info("Response Status Code : " + response.getStatusLine().getStatusCode());
        Assert.assertEquals(response.getStatusLine().getStatusCode(), BAD_REQUEST);
        log.info("Valid Response Status Code");
        String responseBody = ServiceUtils.getResponseBody(response);
        Error error = null;
        try {
            error = mapper.readValue(responseBody, Error.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
        }
        log.info("Error Message  : " + error.getError());

    }

}
