package Trello;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TrelloApiClient {
    private static final String BASE_URL = "https://api.trello.com/1";
    private String apiKey;
    private String token;

    public TrelloApiClient(String apiKey, String token) {
        this.apiKey = apiKey;
        this.token = token;
        RestAssured.baseURI = BASE_URL;

    }

    public Response createBoard(String boardName) {
        return given()
                .queryParam("name", boardName)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .post("/boards");
    }

    public Response createList(String boardId, String listName) {
        return given()
                .queryParam("name", listName)
                .queryParam("idBoard", boardId)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .post("/lists");
    }

    public Response createCard(String listId, String cardName) {
        return given()
                .queryParam("name", cardName)
                .queryParam("idList", listId)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .post("/cards");
    }
    public Response updateCard(String cardId, String newName) {
        return given()
                .queryParam("name", newName)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .put("/cards/" + cardId);
    }

    public Response deleteCard(String cardId) {
        return given()
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .delete("/cards/" + cardId);
    }

    public Response deleteBoard(String boardId) {
        return given()
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .delete("/boards/" + boardId);
    }
}
