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
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    Client newClient = new Client("Anne", myStylist.getId());
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

    // @Test
    // public void removeClientForm_removesClient() {
    //   Stylist stylist = new Stylist("Grace", "Hopper");
    //   stylist.save();
    //   Client client = new Client("Richard", "Roe");
    //   client.save();
    //   client.assignStylist(stylist.getId());
    //   goTo("http://localhost:4567/stylists/" +
    //     Integer.toString(stylist.getId()));
    //   click(".btn-link", withText("Remove Richard Roe"));
    //   assertThat(!(pageSource()).contains("Roe, Richard"));
    // }
    //
    // @Test
    // public void updateClientForm_ChangesName() {
    //   Stylist stylist = new Stylist("Grace", "Hopper");
    //   stylist.save();
    //   Client client = new Client("Richard", "Roe");
    //   client.save();
    //   client.assignStylist(stylist.getId());
    //   goTo("http://localhost:4567/clients/" +
    //     Integer.toString(client.getId()));
    //   fill("#newfirstname").with("Larry");
    //   fill("#newlastname").with("Loe");
    //   submit(".btn", withText("Update Name"));
    //   assertThat(!(pageSource()).contains("Richard Roe"));
    //   assertThat(pageSource()).contains("Larry Loe");
    // }
}
