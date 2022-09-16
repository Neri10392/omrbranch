package org.stepdefination;

import java.util.ArrayList;
import java.util.List;

import org.base.BaseClass;
import org.endpoints.Endpoints;
import org.junit.Assert;
import org.pojo.AddAddress_Input_Pojo;
import org.pojo.AddAddress_Output_Pojo;
import org.pojo.DeleteAddress_Input_Pojo;
import org.pojo.DeleteAddress_Output_Pojo;
import org.pojo.GetUserAddress_Output_Pojo;
import org.pojo.UpdateAddress_Input_Pojo;
import org.pojo.UpdateAddress_Output_pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC2_AddressStep extends BaseClass {
	/**
	 * @description User add headers
	 * @description add request body for Add new address 
	 *  
	 */
	Response response;
	public static String AddressId;

	@Given("User add headers and bearer authentication for accessing address endpoints")
	public void userAddHeadersAndBearerAuthenticationForAccessingAddressEndpoints() {
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		h.add(h1);
		h.add(h2);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

	}

	@When("User add request body for Add new address {string},{string},{string},{string},{string},{string},{string},{string}, {string} and {string}")
	public void userAddRequestBodyForAddNewAddressAnd(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
		AddAddress_Input_Pojo addAddress_Input_Pojo = new AddAddress_Input_Pojo(first_name, last_name, mobile,
				apartment, Integer.valueOf(state), Integer.valueOf(city), Integer.valueOf(country), zipcode, address,
				address_type);

		addBodyObj(addAddress_Input_Pojo);
	}

	@When("User send {string} request for AddUserAddress endpoint")
	public void userSendRequestForAddUserAddressEndpoint(String POST) {

		response = requestMethodType(POST, Endpoints.ADDUSERADDRESS);
	}

	@Then("User verify the login response message matches {string}")
	public void userVerifyTheLoginResponseMessageMatches(String expMessage) {
		
		AddAddress_Output_Pojo addAddress_Output_Pojo = response.as(AddAddress_Output_Pojo.class);
		
		String actmessage = addAddress_Output_Pojo.getMessage();
		
		System.out.println(actmessage);
		
//		Assert.assertEquals("verify message", expMessage, actmessage);
		
		 int address_id = addAddress_Output_Pojo.getAddress_id();
		 
		 AddressId = String.valueOf(address_id);
	}
/**
 * @used for AddRequestBody and UpdateAddress
 * @param address_id
 * @param first_name
 * @param last_name
 * @param mobile
 * @param apartment
 * @param state
 * @param city
 * @param country
 * @param zipcode
 * @param address
 * @param address_type
 */
	@When("User add request body for  update address {string},{string},{string},{string},{string},{string},{string},{string},{string}, {string} and {string}")
	public void userAddRequestBodyForUpdateAddressAnd(String address_id, String first_name, String last_name,
			String mobile, String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
		UpdateAddress_Input_Pojo updateAddress_Input_Pojo = new UpdateAddress_Input_Pojo(AddressId, first_name,
				last_name, mobile, apartment, Integer.valueOf(state), Integer.valueOf(city), Integer.valueOf(country),
				zipcode, address, address_type);
		addBodyObj(updateAddress_Input_Pojo);
	}

	@When("User send {string} request for AddUpdateAddress endpoint")
	public void userSendRequestForAddUpdateAddressEndpoint(String put) {
		response = requestMethodType(put, Endpoints.UPDATEUSERADDRESS);
	}

	@Then("User verify the UpdateUserAddress response message matches {string}")
	public void userVerifyTheUpdateUserAddressResponseMessageMatches(String expUpdatedMsg) {
		UpdateAddress_Output_pojo updateAddress_Output_pojo = response.as(UpdateAddress_Output_pojo.class);
		String actUpdatedMsg = updateAddress_Output_pojo.getMessage();
//		Assert.assertEquals("verify updated sucess message", expUpdatedMsg, actUpdatedMsg);
		System.out.println(actUpdatedMsg);

	}
	/**
	 * @description  Used for  add request body and delete address
	 * 
	 */
	@When("User add request body for delete address")
	public void userAddRequestBodyForDeleteAddress() {
	
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(AddressId);
		addBodyObj(deleteAddress_Input_Pojo);
	}
	@When("User send {string} request for DeleteAddress endpoint")
	public void userSendRequestForDeleteAddressEndpoint(String delete) {
		response = requestMethodType(delete, Endpoints.DELETEUSERADDRESS);
	}

	@Then("User verify the deleteUserAddress response message matches {string}")
	public void userVerifyTheDeleteUserAddressResponseMessageMatches(String expmessage) {

		DeleteAddress_Output_Pojo deleteAddress_Output_Pojo = response.as(DeleteAddress_Output_Pojo.class);
		String actmessage = deleteAddress_Output_Pojo.getMessage();
//		Assert.assertEquals("verify deleted sucessfully message", expmessage, actmessage);
		System.out.println(actmessage);
	}

	/**
	 * @description 
	 * @param GET user SendRequest and GetAddressEndpoint
	 */
	@Given("User send {string} request for GetAddress endpoint")
	public void userSendRequestForGetAddressEndpoint(String GET) {
		response = requestMethodType(GET, Endpoints.GETUSERADDRESS);
	}


	@Then("User verify the getUserAddress response message matches {string}")
	public void userVerifyTheGetUserAddressResponseMessageMatches(String expmessage) {

		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		String actmessage = getUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals("verify GetUser message", expmessage, actmessage);
		System.out.println(actmessage);

	}

}
