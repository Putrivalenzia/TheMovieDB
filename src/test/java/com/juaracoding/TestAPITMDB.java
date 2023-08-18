package com.juaracoding;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestAPITMDB {

    String baseUrl = "https://api.themoviedb.org";
    String apiKey = "fae96c5adcd32daca4ea00e0698235a0";
    String myToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYWU5NmM1YWRjZDMyZGFjYTRlYTAwZTA2OTgyMzVhMCIsInN1YiI6IjY0ZGI3MGJiMDAxYmJkMDQxOTJjZDMyYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KywKCQFEYluCbSdM5EF2poDkVq1PMWFA4h4_EkHrhHI";
    String endpoint (String endpoint){
       return baseUrl + endpoint;
    }
    @Test
    public void testApiNowPlaying() {
        given()
                .queryParam("api_key", apiKey)
                .when()
                .get(endpoint("/3/movie/now_playing"))
                .then()
                .statusCode(200)
                .body("results.id[0]", equalTo(976573))
                .body("results.original_title[0]", equalTo("Elemental"));
    }

    @Test
    public void testApiMoviePopular() {
        given()
                .queryParam("api_key", apiKey)
                .when()
                .get(endpoint("/3/movie/popular"))
                .then()
                .statusCode(200)
                .body("results.id[0]", equalTo(569094))
                .body("results.original_title[0]", equalTo("Spider-Man: Across the Spider-Verse"));
    }

    @Test
    public void testApiAddMovieRating() {
        JSONObject request = new JSONObject();
        request.put("value", 9.50);
        System.out.println(request.toJSONString());
        given()
                .header("Authorization", "Bearer " + myToken)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(endpoint("/3/movie/569094/rating"))
                .then()
                .statusCode(201)
                .log().all();
    }

}


