import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class test  {


    public static void main(String[] args) throws IOException {
        Connection connection =null;
        Statement statement=null;
        ResultSet resultSet=null;
        InputStream resourceAsStream = test.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties=new Properties();
        properties.load(resourceAsStream);
        String url= properties.getProperty("url");
        String driver= properties.getProperty("driver");
        String username= properties.getProperty("user");
        String password= properties.getProperty("password");

        try {
            Class.forName(driver);
//            Class.forName("com.mysql.jdbc.Driver");
//            String url="jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";
//            String username="root";
//            String password="123456";


            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String sql="select * from users";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                System.out.println(resultSet.getObject("id"));
                System.out.println(resultSet.getObject("birthday"));
                System.out.println(resultSet.getObject("name"));
                System.out.println(resultSet.getObject("password"));
                System.out.println(resultSet.getObject("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

        }
    }
}
