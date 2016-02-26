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
    submit(".btn");
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
}
