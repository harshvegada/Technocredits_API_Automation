package helper;

import java.io.IOException;

import io.qameta.allure.Step;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.testng.Assert;

import base.ServiceUtils;

public class HTTPHelper extends ServiceUtils {

	private static Logger log = Logger.getLogger(HTTPHelper.class.getName());

	private static HTTPHelper httpHelper;
	static HTTPHelper http;

	private HTTPHelper() {
		client = HttpClients.createDefault();
		log.debug("default connection created");
		log.trace("Singleton achieve for HTTPHelper class");
	}

	public static HTTPHelper getInstace() {
		if (http == null)
			http = new HTTPHelper();
		return http;
	}

	@Step("Service to call {0} and service type {1} with request body {2}")
	public CloseableHttpResponse serviceCallWithAPIName(String serviceName, String serviceType, StringEntity entity) {
		CloseableHttpResponse response = null;
		log.debug("Service Type : " + serviceType);
		log.debug("Service Name : " + serviceName);
		try {
			switch (serviceType.toUpperCase()) {
			case "GET":
				try {
					response = client.execute(getHttpGetCall(serviceName));
					log.debug("response capatured for " + serviceType);
				} catch (IOException e) {
					log.error(e.getMessage());
				}
				break;
			case "POST":
				try {
					HttpPost postCall = getHttpPostCall(serviceName);
					postCall.setEntity(entity);
					response = client.execute(postCall);
					log.debug("response capatured for " + serviceType);
				} catch (IOException e) {
					log.error(e.getMessage());
				}
				break;
			case "DELETE":
				try {
					response = client.execute(getHttpDeleteCall(serviceName));
					log.debug("response capatured for " + serviceType);
				} catch (IOException e) {
					log.error(e.getMessage());
				}
				break;
			case "PUT":
				try {
					HttpPut putCall = getHttpPutCall(serviceName);
					putCall.setEntity(entity);
					response = client.execute(putCall);
					log.debug("response capatured for " + serviceType);
				} catch (IOException e) {
					log.error(e.getMessage());
				}
				break;
			case "PATCH":
				try {
					HttpPatch putCall = getHttpPatchCall(serviceName);
					putCall.setEntity(entity);
					response = client.execute(putCall);
					log.debug("response capatured for " + serviceType);
				} catch (IOException e) {
					log.error(e.getMessage());
				}
				break;

			default:
				Assert.fail("Invalid Http Call : " + serviceType);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			try {
				client.close();

			} catch (IOException e1) {
				log.error(e.getMessage());
				log.error(e.getStackTrace());
			}
		}

		return response;
	}

}
