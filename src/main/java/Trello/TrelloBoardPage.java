package Trello;

import io.restassured.response.Response;

public class TrelloBoardPage {
    private final TrelloApiClient apiClient;
    private String boardId;
    private String listId;

    public TrelloBoardPage(TrelloApiClient apiClient) {
        this.apiClient = apiClient;
    }

    // Method to create a Trello board and return its ID
    public String createBoard(String boardName) {
        Response response = apiClient.createBoard(boardName);
        String boardId = response.jsonPath().getString("id");
        System.out.println("Board created: " + boardId);
        return boardId;
    }

    public String createList(String boardId, String listName) {
        Response response = apiClient.createList(boardId, listName);
        String listId = response.jsonPath().getString("id");
        System.out.println("List created: " + listId);
        return listId;
    }

    public String createCard(String listId, String cardName) {
        Response response = apiClient.createCard(listId, cardName);
        String cardId = response.jsonPath().getString("id");
        System.out.println("Card created: " + cardId);
        return cardId;
    }

    public void updateCard(String cardId, String updatedCardName) {
        apiClient.updateCard(cardId, updatedCardName);
        System.out.println("Card with ID " + cardId + " is updated to: " + updatedCardName);
    }

    public void deleteCard(String cardId) {
        apiClient.deleteCard(cardId);
        System.out.println("Card with ID " + cardId + " is deleted successfully!");
    }

    public void moveCard(String cardId, String listId) {
        apiClient.moveCard(cardId, listId);
        System.out.println("Card with ID " + cardId + " moved to list with ID " + listId);
    }

    public void closeBoard(String boardId) {
        apiClient.closeBoard(boardId);
        System.out.println("Board with ID " + boardId + " is closed.");
    }

    public void deleteBoard(String boardId) {
        closeBoard(boardId);
        apiClient.deleteBoard(boardId);
        System.out.println("Board with ID " + boardId + " is deleted.");
    }

}
