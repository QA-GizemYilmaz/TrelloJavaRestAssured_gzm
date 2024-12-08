import Trello.TrelloApiClient;
import Trello.TrelloBoardPage;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TrelloTest {
    private TrelloApiClient apiClient;
    private String boardId;
    private String listId;


    @Test
    public void trelloBoardTest() {
        String api = "YOUR_API_KEY_HERE";
        String token = "YOUR_TOKEN_HERE";

        TrelloApiClient apiClient = new TrelloApiClient(api, token);
        TrelloBoardPage boardPage = new TrelloBoardPage(apiClient);


        //Creating a Board on Trello
        String boardId = boardPage.createBoard("VaultN");

        // Creating a List
        String BacklogListId = boardPage.createList(boardId, "Backlog");
        String TodolistId = boardPage.createList(boardId, "Todo");
        String DoinglistId = boardPage.createList(boardId, "Doing");
        String TestinglistId = boardPage.createList(boardId, "Testing");
        String DonelistId = boardPage.createList(boardId, "Done");


        //Creating a Card on Trello
        String SignupforTrello = boardPage.createCard(TodolistId, "Sign up for Trello");
        String Getkeyandtoken = boardPage.createCard(TodolistId, "Get key and token");
        String BuildAcollection = boardPage.createCard(TodolistId, "Build a collection");
        String WorkingonTask = boardPage.createCard(TodolistId, "Working on Task");
        String UIAutomation = boardPage.createCard(BacklogListId, "UI Automation");
        //String WritingTestScenarios = boardPage.createCard(BacklogListId, "Writing Test Scenarios");

        // Moving cards to the specified List
        boardPage.moveCard(SignupforTrello, DonelistId); // "Sign up for Trello" -> Done
        boardPage.moveCard(Getkeyandtoken, TestinglistId); // "Get key and token" -> Testing
        boardPage.moveCard(BuildAcollection, DoinglistId); // "Build a collection" -> Doing
        boardPage.moveCard(WorkingonTask, DoinglistId); // "Working on Task" -> Doing

        // Close the Board
        boardPage.closeBoard(boardId);

        // Delete the Board
        boardPage.deleteBoard(boardId);

        System.out.println("All operations completed successfully.");



    }
}