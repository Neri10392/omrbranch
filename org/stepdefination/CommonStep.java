package org.stepdefination;

import org.base.BaseClass;
import org.junit.Assert;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class CommonStep extends BaseClass {
	
	/**
	 * @description Used to verify status code with assertion
	 * @category Common class extends by baseClass
	 * @param int1
	 */
	
	
	@Then("User verify the status code is {int}")
	public void userVerifyTheStatusCodeIs(int int1) {
		
		int statusCode = getStatusCode(response);
        System.out.println(statusCode);
       Assert.assertEquals("Verify statusCode",int1, statusCode);
		
	}

}
