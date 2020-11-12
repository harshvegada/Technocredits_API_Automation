package constant;

public class HTTPStatusCode {

	/**
	 *************************
	 * INFORMATION RESPONSES *
	 *************************
	 */

	public static final int CONTINUE = 100;
	public static final int SWITCHING_PROTOCOL = 101;
	public static final int PROCESSING = 102;
	public static final int EARLY_HINTS = 103;

	/**
	 ************************
	 * SUCCESSFUL RESPONSES *
	 ************************
	 */

	public static final int SUCCESSFULL_OK = 200;
	public static final int SUCCESSFULL_CREATED = 201;
	public static final int SUCCESSFULL_ACCEPTED = 202;
	public static final int NON_AUTHORITATIVE_INFORMATION = 203;
	public static final int NO_CONTENT_FOUND = 204;
	public static final int RESET_CONTENT = 205;
	public static final int PARTIAL_CONTENT = 206;
	public static final int MULTI_STATUC = 207;
	public static final int ALREADY_REPORTED = 208;

	/**
	 ************************
	 * REDIRECTION MESSAGES *
	 ************************
	 */

	public static final int MULTIPLE_CHOICE = 300;
	public static final int MOVED_PERMANENTLY = 301;
	public static final int FOUND = 302;
	public static final int SEE_OTHER = 303;
	public static final int NOT_MODOFIED = 304;
	public static final int USE_PROXY = 305;
	public static final int UNUSED = 306;
	public static final int TEMPORARY_REDIRECT = 307;
	public static final int PERMANENT_REDIRECT = 308;

	/**
	 **************************
	 * CLIENT ERROR RESPONSES *
	 **************************
	 */

	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int PAYMENT_REQUIRED = 402;
	public static final int FORBIDDEN = 403;
	public static final int NOT_FOUND = 404;
	public static final int METHOD_NOT_ALLOWED = 405;
	public static final int NOT_ACCEPTABLE = 406;
	public static final int REQUEST_TIMEOUT = 408;
	public static final int LENGTH_REQUIRE = 411;

	/**
	 **************************
	 * SERVER ERROR RESPONSES *
	 **************************
	 */

	public static final int INTERNAL_SERVER_ERROR = 500;
	public static final int GATEWAY_TIMEOUT = 504;
	public static final int BAD_GATEWAY = 502;
	public static final int SERVICE_UNAVAILABLE = 503;

}
