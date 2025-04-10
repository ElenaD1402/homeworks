package org.elena.hw22;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {

    @Test
    public void testHttpStatusCode() {
        Response response = given().log().all()
                .get("http://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200).extract().response();
        System.out.println(response.prettyPrint());
    }

    @Test
    public void testHttpResponseHeader() {
        boolean headerExists = given()
                .get("http://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200).extract().headers().hasHeaderWithName("content-type");
        System.out.println("Header exists - " + headerExists);
        Assert.assertTrue(headerExists, "Header \"content-type\" does not exist");
        String header = given()
                .get("http://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200).extract().header("content-type");
        System.out.println("Header is \"" + header + "\"");
        Assert.assertEquals(header, "application/json; charset=utf-8", header + "is not \"application/json; charset=utf-8\"");
    }

    @Test
    public void testHttpResponseBody() {
        Response response = given()
                .get("http://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200).extract().response();
        int ids = response.jsonPath().getList("id").size();
        System.out.println("The number of users is " + ids);
        Assert.assertEquals(ids, 100, "The number of users is not equal to 100");
    }

    @Test
    public void testCreatePost() {
        ResponseBody responseBody = given().log().all()
                .header("content-type", "application/json; charset=utf-8")
                .body("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}")
                .post("http://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201).extract().body().as(ResponseBody.class);
        System.out.println(responseBody.toString());
        Assert.assertFalse(responseBody.toString().isEmpty(), "Response body is empty");
        int id = responseBody.getId();
        System.out.println("\"id\" of the new post is " + id);
    }

    @Test
    public void testUpdatePost() {
        ResponseBody responseBody = given().log().all()
                .header("content-type", "application/json; charset=utf-8")
                .body("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1, \"id\": 1}")
                .put("http://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200).extract().body().as(ResponseBody.class);
        System.out.println(responseBody.toString());
        Assert.assertTrue(responseBody.toString().contains("\"id\": 1"), "Response body does not contain \"id\": 1");
    }

    @Test
    public void testDeletePost() {
        Response response = given().log().all()
                .delete("http://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200).extract().response();
        System.out.println(response.prettyPrint());
    }
}
