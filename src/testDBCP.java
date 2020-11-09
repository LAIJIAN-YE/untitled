import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testDBCP {
    public static void main(String[] args)  {
        Connection connection=DBCPutil.getconetion();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String test;
        System.out.println("更新1");
        System.out.println("更新2");
        String sq1="update account set money =5000 where `name`='C'";
        try {
             preparedStatement = connection.prepareStatement(sq1);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBCPutil.release(connection,preparedStatement,resultSet);

        }

    }
}
