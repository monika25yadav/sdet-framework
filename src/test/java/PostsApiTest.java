import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

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

}
