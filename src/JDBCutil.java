import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCutil {
  static String url="";
    static String username="";
    static String password="";
    static{
        try {


        InputStream resourceAsStream = JDBCutil.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties=new Properties();
        properties.load(resourceAsStream);
        url= properties.getProperty("url");
        String driver= properties.getProperty("driver");
        username= properties.getProperty("user");
        password= properties.getProperty("password");
        Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getconetion(){
        Connection connection=null;
        try {
             connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
    public static void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)  {
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
