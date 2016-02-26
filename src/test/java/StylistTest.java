import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Stylist firstStylist = new Stylist("Jamie");
    Stylist secondStylist = new Stylist("Jamie");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesStylistIntoDatabase_true() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void find_findsStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

  @Test
  public void getClients_returnsAllClientsFromDatabase_clientList() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    Client firstClient = new Client("Jane", myStylist.getId(), "12/12/2016", "9:00 AM");
    firstClient.save();
    Client secondClient = new Client("Sue", myStylist.getId(), "12/12/2016", "9:00 AM");
    secondClient.save();
    Client[] clientList = new Client[] {firstClient, secondClient};
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clientList)));
  }

  @Test
  public void update_updateStylistName_newName() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    String newName = "Tina";
    myStylist.update(newName);
    assertTrue(myStylist.getName().equals(newName));
  }

  @Test
  public void delete_deletesStylistFromDatabase() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    myStylist.delete();
    assertEquals(0, Stylist.all().size());
  }

  @Test
  public void deleteClients_deletesClientsFromDBwhenTheirStylistIsDeleted() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    Client firstClient = new Client("Sue", myStylist.getId(), "12/12/2016", "9:00 AM");
    firstClient.save();
    Client secondClient = new Client("Jane", myStylist.getId(), "12/12/2016", "9:00 AM");
    secondClient.save();
    myStylist.deleteClients();
    myStylist.delete();
    assertEquals(0, Client.all().size());
  }
}
