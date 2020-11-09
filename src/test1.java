import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test1 {
    public static void main(String[] args) {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Connection connection = JDBCutil.getconetion();
        String sq1="update account set money=money-100 where `name`='A'";
        String sq2="update account set money=money+100 where `name`='B'";

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sq1);
            int i1 = preparedStatement.executeUpdate();
//            int o=1/0;
            preparedStatement= connection.prepareStatement(sq2);
            int i2=preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();

        }finally {
            JDBCutil.release(connection,preparedStatement,resultSet);
        }

    }
}
