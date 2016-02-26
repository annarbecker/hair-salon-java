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
    Client firstClient = new Client("Jane", 1, "12/12/16", "9:00 AM");
    Client secondClient = new Client("Jane", 1, "12/12/16", "9:00 AM");
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesClientToDatabase() {
    Client myClient = new Client("Jane", 1, "12/12/16", "9:00 AM");
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_savesStylistIdForClientIntoDatabase_true() {
    Stylist myStylist = new Stylist("Jane");
    myStylist.save();
    Client myClient = new Client("Anne", myStylist.getId(), "12/12/16", "9:00 AM");
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient.getStylistId(), myStylist.getId());
  }

  @Test
  public void find_findsClientInDatabase_true() {
    Client myClient = new Client("Jane", 1, "12/12/16", "9:00 AM");
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

  @Test
  public void update_updatesClientName() {
    Client myClient = new Client("Jane", 1, "12/12/16", "9:00 AM");
    myClient.save();
    String newName = "Sue";
    myClient.update(newName);
    assertTrue(Client.all().get(0).getName().equals(newName));
  }

  @Test
  public void delte_deletesClientFromDatabase() {
    Client myClient = new Client("Jane", 1, "12/12/16", "9:00 AM");
    myClient.save();
    myClient.delete();
    assertEquals(0, Client.all().size());
  }
}
