import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.theInstance;

public class PostsApiTest {
    @Test
    public void getPostById() {
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("userId", equalTo(1))
            .body("title", not(emptyString()));
    }

    @Test
    public void getAllPostsReturnsList() {
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
}

@Test
public void createPostReturns201() {
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .header("Content-Type", "application/json")
        .body("""
            {
              "title": "KolorBear Adventures",
              "body": "A post created by automation",
              "userId": 1
            }
            """)
    .when()
        .post("/posts")
    .then()
        .statusCode(201)
        .body("title", equalTo("KolorBear Adventures"))
        .body("id", notNullValue());
}

@Test
public void updateCreatedPostTitle() {
    // full response captured into a Response object
    // 1. Create a new post and extract the full response
    Response response = given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .header("Content-Type", "application/json")
        .body("""
            {
              "title": "KolorBear Adventures",
              "body": "A post created by automation",
              "userId": 1
            }
            """)
    .when()
        .post("/posts")
    .then()
        .statusCode(201)
        .extract().response();   // <- pull the whole response out
        System.out.println("PUT response: " + response.getBody().asString());
        // 2. Extract the id assigned by the server
        int createdId = response.path("id");   

        // 3. Update the post utilizing the dynamic id
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .header("Content-Type", "application/json")
        .body("""
            {
              "title": "KolorBear Adventures Vol. 2",
              "body": "A post created by automation",
              "userId": 1
            }
            """)
    .when()
       // .put("/posts/" + createdId)  // This produces error in the API because it is a fake API, but in a real API this would work
        .put("/posts/1")  // This works because the fake API does not support dynamic ids
    .then()
        .statusCode(200)
        .body("title", equalTo("KolorBear Adventures Vol. 2"))
        .body("id", equalTo(1));  // This is not the createdId because the fake API does not support dynamic ids

}

}
