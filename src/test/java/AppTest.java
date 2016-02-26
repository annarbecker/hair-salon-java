import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.junit.ClassRule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Hair Salon");
  }

  @Test
  public void stylistIsCreatedAndDisplayed() {
    goTo("http://localhost:4567/");
    fill("#stylist").with("Jane");
    submit("#addStylist");
    assertThat(pageSource()).contains("Jane");
  }

  @Test
  public void clientIsDisplayed() {
    Stylist myStylist = new Stylist("Jane");
    myStylist.save();
    Client newClient = new Client("Anne", myStylist.getId());
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    newClient.save();
    goTo(stylistPath);
    assertThat(pageSource().contains("Anne"));
  }

  @Test
    public void deleteStylist() {
      Stylist myStylist = new Stylist("Jamie");
      myStylist.save();
      goTo("http://localhost:4567/");
      click(".delete", withText("Delete"));
      assertThat(!(pageSource()).contains("Jamie"));
    }

  @Test
  public void deleteClient() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    Client myClient = new Client("Sue", myStylist.getId());
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    myClient.save();
    myClient.delete();
    goTo(stylistPath);
    submit(".delete");
    assertThat(!(pageSource()).contains("Sue"));
  }

  @Test
  public void updateStylist() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d/update", myStylist.getId());
    goTo(stylistPath);
    fill("#updateName").with("John");
    submit("#updateBtn");
    assertThat(pageSource()).contains("John");
  }

  @Test
  public void updateClient() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    Client client = new Client("Sue", myStylist.getId());
    client.save();
    String clientPath = String.format("http://localhost:4567/add-clients/%d/update", client.getId());
    goTo(clientPath);
    fill("#updateName").with("Mike");
    submit("#updateBtn");
    assertThat(pageSource()).contains("Mike");
  }

  @Test
  public void stylistIsDeleted() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d/delete", myStylist.getId());
    goTo(stylistPath);
    assertThat((pageSource()).contains("deleted"));
  }

  @Test
  public void clientIsDeleted() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    Client client = new Client("Sue", myStylist.getId());
    client.save();
    String clientPath = String.format("http://localhost:4567/add-clients/%d/delete", client.getId());
    goTo(clientPath);
    assertThat((pageSource()).contains("deleted"));
  }

  @Test
  public void stylistIsUpdated() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d/updated", myStylist.getId());
    goTo(stylistPath);
    assertThat((pageSource()).contains("updated"));
  }

  @Test
  public void clientIsUpdated() {
    Stylist myStylist = new Stylist("Jamie");
    myStylist.save();
    Client client = new Client("Sue", myStylist.getId());
    client.save();
    String clientPath = String.format("http://localhost:4567/add-clients/%d/update", client.getId());
    goTo(clientPath);
    assertThat((pageSource()).contains("updated"));
  }
}
