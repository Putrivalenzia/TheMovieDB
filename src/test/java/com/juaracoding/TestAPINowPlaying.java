package com.juaracoding;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestAPINowPlaying {
    @Test
    public void testApiNowPlaying() {
        given()
                .queryParam("api_key", "fae96c5adcd32daca4ea00e0698235a0")
                .when()
                .get("https://api.themoviedb.org/3/movie/now_playing")
                .then()
                .statusCode(200)
                .body("results.id[0]", equalTo(667538))
                .body("results.original_title[0]", equalTo("Transformers: Rise of the Beasts"));
    }

    @Test
    public void testApiMoviePopular() {
        given()
                .queryParam("api_key", "fae96c5adcd32daca4ea00e0698235a0")
                .when()
                .get("https://api.themoviedb.org/3/movie/popular")
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
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYWU5NmM1YWRjZDMyZGFjYTRlYTAwZTA2OTgyMzVhMCIsInN1YiI6IjY0ZGI3MGJiMDAxYmJkMDQxOTJjZDMyYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KywKCQFEYluCbSdM5EF2poDkVq1PMWFA4h4_EkHrhHI")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("https://api.themoviedb.org/3/movie/569094/rating")
                .then()
                .statusCode(201)
                .log().all();
    }

}


