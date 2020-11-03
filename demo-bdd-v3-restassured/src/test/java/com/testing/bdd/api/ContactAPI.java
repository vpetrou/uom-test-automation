package com.testing.bdd.api;

import com.testing.bdd.utils.BaseTest;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.Assert;

public class ContactAPI extends BaseTest {

    public static String contactID;

    public void searchContactById(String email) {
        response = RestAssured
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(URL + "/contacts/" + contactID)
                .then()
                .extract()
                .response();

        response.then().statusCode(200);
        Assert.assertTrue(response.asString().contains(email));
    }

    public void addNewContact(DataTable testData) {
        String address = getValue(testData, "Address");
        String city = getValue(testData, "City");
        String email = getValue(testData, "Email");
        String name = getValue(testData, "Name");
        String phone = getValue(testData, "Phone");
        String gender = getValue(testData, "Gender");
        String status = getValue(testData, "Status");
        String disabled = "false";
        if (status.equalsIgnoreCase("enabled")) {
            disabled = "true";
        }

        JSONObject contactObject = new JSONObject()
                .put("address",address)
                .put("city",city)
                .put("disabled",disabled)
                .put("email", email)
                .put("gender", gender.substring(0, 1).toLowerCase())
                .put("name", name)
                .put("phone", phone);

        System.out.println(contactObject.toString());
        response = RestAssured
                .given()
                .baseUri(URL + "/contacts")
                .contentType(ContentType.JSON)
                .body(contactObject.toString())
                .post()
                .then()
                .extract()
                .response();
        response.then().statusCode(200);

        contactID = JsonPath.from(response.asString()).get("id").toString();
        System.out.println("ID of the New Contact: " + contactID);
    }

    public static void cleanup() {
        setURL();
        response = RestAssured
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .when()
                .delete(URL + "/contacts/delete")
                .then()
                .extract()
                .response();

        response.then().statusCode(200);
    }

}
