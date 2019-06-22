package pl.sda.jdbc;

import java.sql.*;

public class JdbcMain {
    public static void main(String[] args) {
        statement();
    }

    private static void statement() {
        try(Connection connection = getConnection()) {
        String query = "select ename, job, sal from sdajdbc.employee where sal > 100";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int sal = resultSet.getInt("sal");
                System.out.println(ename + " " + job + " " + sal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "slepy7");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
