import java.util.List;
import org.sql2o.*;

public class Client {
  private String name;
  private int stylistId;
  private int id;

  public Client(String name, int stylistId) {
    this.name = name;
    this.stylistId = stylistId;
  }

  public String getName() {
    return name;
  }

  public int getStylistId() {
    return stylistId;
  }

  public int getId() {
    return id;
  }

  public static List<Client> all() {
    String sql = "SELECT id, name, stylistId FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }




}
