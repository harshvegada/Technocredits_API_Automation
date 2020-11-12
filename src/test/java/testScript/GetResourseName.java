package testScript;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ServiceUtils;
import constant.ConstantPath;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pojos.requestPojoClasses.resourcesPojo.Resources;
import utils.PropertiesConfiguration;

import static constant.HTTPStatusCode.SUCCESSFULL_OK;

public class GetResourseName extends TestBase {

    private static Logger log = Logger.getLogger(GetResourseName.class.getName());

    static PropertiesConfiguration propertiesConfiguration;
    static String baseURL;
    static String listUsers;

    @BeforeClass()
    public static void beforeClass() {
        propertiesConfiguration = new PropertiesConfiguration(ConstantPath.APPLICATION_FILE);
        log.trace("Application properties File load in memeory");
        baseURL = propertiesConfiguration.getPropertyValue("baseURL");
        log.trace("Getting Base URL : " + baseURL);
        listUsers = propertiesConfiguration.getPropertyValue("listUsers");
        log.trace("Getting service URL : " + listUsers);
    }

    @Test
    @Description("GET api call to retive users List on per pages")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Epic for get to check user list on page")
    public void getUserList() {
        String apiName = baseURL + listUsers;
        log.debug("Api Name : " + apiName);
        CloseableHttpResponse response = httpHelper.serviceCallWithAPIName(apiName, "GET", null);

        log.info("Response Status Code : " + response.getStatusLine().getStatusCode());

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SUCCESSFULL_OK);
        log.info("Valid Response Status Code");
        String responseBody = ServiceUtils.getResponseBody(response);

        Resources resources = null;
        try {
            resources = mapper.readValue(responseBody, Resources.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        log.info("Total pages : " + resources.getPage());
        log.info("Total per page user : " + resources.getPerPage());
        log.info("Total per page data : " + resources.getData().size());
        log.info("Avatar Name : " + resources.getData().get(0).getAvatar());
        log.info("Email : " + resources.getData().get(0).getEmail());
        log.info("Avatar First Name : " + resources.getData().get(0).getFirstName());
        log.info("Avatar Last Name : " + resources.getData().get(0).getLastName());
        log.info("Avatar ID : " + resources.getData().get(0).getId());
        log.info("Total User : " + resources.getTotal());

    }
}
