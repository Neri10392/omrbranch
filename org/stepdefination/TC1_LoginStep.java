package org.stepdefination;

import org.base.BaseClass;
import org.endpoints.Endpoints;
import org.junit.Assert;
import org.pojo.Login_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC1_LoginStep extends BaseClass {

	 static  String logtoken;
//	 Response response ;
	 
	 /**
	  * @description TC1_Login stepdefination class 
	  * @ add header
	  * @ basic authentication for login 
	  * 
	  */
	
	@Given("User add header")
	public void userAddHeader() {
		
		addHeader("accept", "application/json");
		
	}
	@Given("User add basic authentication for login")
	public void userAddBasicAuthenticationForLogin() {
		
		basicAuth("neriyarasan@gmail.com", "Neri@10392");

		
	}
	@When("User send {string} request for login endpoint")
	public void userSendRequestForLoginEndpoint(String POST) {
		
		 response = requestMethodType(POST, Endpoints.POSTMANBASICAUTHLOGIN);
	
	}
	
	@Then("User verify the login response body firstName Present as {string} and get the logtoken")
	public void userVerifyTheLoginResponseBodyFirstNamePresentAsAndGetTheLogtoken(String expmessage) {
		
		Login_Output_Pojo loginOutput = response.as(Login_Output_Pojo.class);
        logtoken = loginOutput.getData().getLogtoken();
        
		String actmessage = loginOutput.getData().getFirst_name();
		System.out.println(actmessage);
		Assert.assertEquals("Verify FirstName",actmessage,expmessage );


	
	}

}
