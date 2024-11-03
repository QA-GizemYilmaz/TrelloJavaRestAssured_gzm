import Trello.TrelloApiClient;
import Trello.TrelloBoardPage;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TrelloTest {
    private TrelloApiClient apiClient;
    private String boardId;
    private String listId;
    private String cardID_1;
    private String cardID_2;



    @Test
    public void trelloBoardTest() {
        //String a = "secret";
        //String token = "secret";

        TrelloApiClient apiClient = new TrelloApiClient(a, t);
        TrelloBoardPage boardPage = new TrelloBoardPage(apiClient);

        //Creating a Board on Trello
        Response response = apiClient.createBoard("boardName");
        boardId = response.jsonPath().getString("id");
        System.out.println("Board created: " + boardId);

        //Creating a List on Trello
        Response response2 = apiClient.createList(boardId, "listName");
        listId = response2.jsonPath().getString("id");
        System.out.println("List created: " + listId);

        //Creating a Card on Trello
        Response response3 = apiClient.createCard(listId, "cardName1");
        cardID_1 = response3.jsonPath().getString("id");
        System.out.println("Card ID 1: " + cardID_1);
        //Creating second card
        Response response4 = apiClient.createCard(listId, "cardName2");
        cardID_2 = response4.jsonPath().getString("id");
        System.out.println("Card ID 2: " + cardID_2);

        //Choose randomly a card and update it
        String randomCardId = Math.random() > 0.5 ? cardID_1 : cardID_2;
        apiClient.updateCard(randomCardId, "Updated_Card");
        System.out.println("Card ID with " + randomCardId + "is updated randomly");

        //Deleting Cards on Trello
        apiClient.deleteCard(cardID_1);
        apiClient.deleteCard(cardID_2);
        System.out.println("Cards are removed successfully ! ");

        //Deleting Board on Trello
        apiClient.deleteBoard(boardId);
        System.out.println("Board is removed successfully ! ");


    }
}