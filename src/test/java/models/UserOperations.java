package models;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserOperations{
    public static User createUser(){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        User user = User.createRandomUser();

        given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200);
        return user;
    }

    public static Response loginUser(User user){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        Map<String, String> body = new HashMap<String, String>();
        body.put("email", user.getEmail());
        body.put("password", user.getPassword());
        return given()
                .header("Content-type", "application/json")
                .body(body)
                .when()
                .post("/api/auth/login");
    }

    public static String loginUserAndGetAccessToken(User user){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        var response = loginUser(user);
        response.then().assertThat().statusCode(200);
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        return jsonObject.get("accessToken").toString();
    }

    public static void deleteUser(User user){
        if (user == null){
            return;
        }

        String accessTokenToDelete = loginUserAndGetAccessToken(user);
        deleteUserByToken(accessTokenToDelete);
    }

    public static void deleteUserByToken(String accessToken) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202);
    }
}