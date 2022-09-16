package org.stepdefination;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.base.BaseClass;
import org.endpoints.Endpoints;
import org.pojo.UpdateProfilrPic_Output_Pojo;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC3_ChangeProfilePic_Step extends BaseClass {

	static String logtoken;
	Response response;
	
/**
 * 
 * @description Used for add headers and bearer authorization
 * @ for accessing upload profile picture endpoints
 *  
 */
	@Given("User add headers and bearer authorization for accessing upload profile picture endpoints")
	public void userAddHeadersAndBearerAuthorizationForAccessingUploadProfilePictureEndpoints() {
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.logtoken);
		Header h3 = new Header("Content-Type", "multipart/form-data");

		h.add(h1);
		h.add(h2);
		h.add(h3);
		Headers headers = new Headers(h);

		addHeaders(headers);

	}

	@When("User add multipart for upload profile picture")
	public void userAddMultipartForUploadProfilePicture() {

		formData("profilePic",
				new File("C:\\Users\\Welcome\\eclipse-workspace\\OMRBranchAPIAutomation\\Images\\Sketch001.tif"));
	}

	@When("User sent {string} request for upload profile picture")
	public void userSentRequestForUploadProfilePicture(String POST) {

		response = requestMethodType(POST, Endpoints.UPDATEPROFILEPIC);
	}

	@Then("User verify the create upload profile picture response message matches {string}")
	public void userVerifyTheCreateUploadProfilePictureResponseMessageMatches(String expmsg) {
		
		UpdateProfilrPic_Output_Pojo updateProfilrPic_Output_Pojo = response.as(UpdateProfilrPic_Output_Pojo.class);
		String actmessage = updateProfilrPic_Output_Pojo.getMessage();
		System.out.println(actmessage);
		Assert.assertEquals("Profile updated Successfully", actmessage, expmsg);

	}
}
