package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	/**
	 * @author Welcome
	 * @description used to maintain all reusable methods
	 * @date 8/07/2022
	 * 
	 * 
	 */
	RequestSpecification reqSpec;
	public static Response response;

	/**
	 * @description used to get properties in properties file
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Object getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(System.getProperty("user.dir") + "\\Config\\config.properties"));
		Object object = properties.get(key);
		return object;

	}

	/**
	 * @description used to formData its contains picture path
	 * @param key
	 * @param file
	 */
	public void formData(String key, File file) {
		reqSpec = reqSpec.multiPart("profile_picture", file);

	}

	/**
	 * @description used to add header
	 * @param key
	 * @param value
	 */

	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);
	}

	/**
	 * @description used to ad multiple headers
	 * @param headers
	 */
	public void addHeaders(Headers headers) {

		reqSpec = RestAssured.given().headers(headers);

	}
     /**
      * @description used to add Query parameter
      * @param key
      * @param value
      */
	public void addQueryParam(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);
	}
/**
 * @description used to basic authentication username and password 
 * @param userName
 * @param password
 */
	public void basicAuth(String userName, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(userName, password);

	}
	/**
	 * @description used to add path parameter
	 * @param key
	 * @param value
	 */

	public void addPathParam(String key, String value) {
		reqSpec = reqSpec.pathParam(key, value);
	}
	/**
	 * @description used to add body by  object
	 * @param body
	 */

	public void addBodyObj(Object body) {
		reqSpec = reqSpec.body(body);
	}
	/**
	 * @description used to add body by   string
	 * @param body
	 */

	public void addBody(String body) {
		reqSpec = reqSpec.body(body);
	}
	/**
	 * @description used to request Method type
	 * @param type
	 * @param endPoint
	 * @return
	 */

	public Response requestMethodType(String type, String endPoint) {
		switch (type) {
		case "GET":
			response = reqSpec.log().all().get(endPoint);

			break;
		case "PUT":
			response = reqSpec.log().all().put(endPoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endPoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endPoint);
			break;
		default:
			break;
		}
		return response;
	}
/**
 * @description used to get status code
 * @param response
 * @return
 */
	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;

	}
	/**
	 * @description used to get response as string
	 * @param response
	 * @return
	 */

	public String getAsString(Response response) {
		String asString = response.asString();
		return asString;

	}
	/**
	 * @description used to get response as Pretty string
	 * @param response
	 * @return
	 */

	public String getAsPrettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;

	}
}
