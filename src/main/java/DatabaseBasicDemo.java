import java.sql.*;

@Deprecated
public class DatabaseBasicDemo {
    public static void main(String[] args) {
        String databaseUrl="jdbc:postgresql://localhost:5432/postgres";
        String username="postgres";
        String password="lyhou123";

        String getAllPersonSQL= """
                SELECT * FROM my_database1;
                """;
        try(
                Connection con = DriverManager.getConnection(databaseUrl,username,password);
                Statement statement = con.createStatement();
        ){
          var resultSet=   statement.executeQuery(getAllPersonSQL);
          while(resultSet.next()){
              System.out.println("Person ID:"+resultSet.getInt("id"));
              System.out.println("Person Name:"+resultSet.getString("fullname"));
              System.out.println("Person Gender :"+resultSet.getString("gender"));
              System.out.println("-----------------------------------------");
          }

            System.out.println("We are able to connect to it now !");
        }catch (SQLException ex ){
            ex.printStackTrace();
        }



    }
}
