import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Client.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Client firstClient = new Client("Jane", 1);
    Client secondClient = new Client("Jane", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesClientToDatabase() {
    Client myClient = new Client("Jane", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void find_findsClientInDatabase_true() {
    Client myClient = new Client("Jane", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }
}
