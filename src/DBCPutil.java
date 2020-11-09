

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBCPutil {
  static String url="";
    static String username="";
    static String password="";
    static DataSource dataSource=null;
    static{
        try {


        InputStream resourceAsStream = DBCPutil.class.getClassLoader().getResourceAsStream("DBCP.properties");
        Properties properties=new Properties();
        properties.load(resourceAsStream);
        dataSource= BasicDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getconetion(){
        Connection connection=null;
        try {
             connection = dataSource.getConnection();
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
