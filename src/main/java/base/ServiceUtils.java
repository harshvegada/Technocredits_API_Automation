package base;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public abstract class ServiceUtils {

	protected static ServiceUtils serviceUtils;
	private static Logger log = Logger.getLogger(ServiceUtils.class.getName());
	static ThreadLocal<ServiceUtils> serviceThread = new ThreadLocal<>();

	public static CloseableHttpClient client;

	protected HttpGet getHttpGetCall(String apiName) {
		log.trace("Return HTTP Get object");
		return new HttpGet(apiName);
	}

	protected HttpPost getHttpPostCall(String apiName) {
		log.trace("Return HTTP Post object");
		return new HttpPost(apiName);
	}

	protected HttpPatch getHttpPatchCall(String apiName) {
		log.trace("Return HTTP Patch object");
		return new HttpPatch(apiName);
	}

	protected HttpPut getHttpPutCall(String apiName) {
		log.trace("Return HTTP Put object");
		return new HttpPut(apiName);
	}

	protected HttpDelete getHttpDeleteCall(String apiName) {
		log.trace("Return HTTP Delete object");
		return new HttpDelete(apiName);
	}

	public static String getResponseBody(CloseableHttpResponse response) {
		String body = null;
		try {
			body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			log.trace("Response converted to String body");
		} catch (ParseException | IOException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}

		return body;
	}

}
