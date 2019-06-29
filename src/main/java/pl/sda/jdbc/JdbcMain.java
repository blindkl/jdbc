package pl.sda.jdbc;

import jdk.nashorn.internal.codegen.CompilerConstants;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMain {
    public static void main(String[] args) {
        //        statement();
//        preparedStatement();
//        callableStatement();
//        preparedStatement2();
//        System.out.println("Employees by salary range");
//        System.out.println(findEmployeeBySalaryRange(500, 2500));

//        sqlInjectionStatement("KING");
//        sqlInjectionStatement("KING'; drop table sdajdbc.salgrade; -- ");
        addPayout(new BigDecimal(500));
    }

    private static void addPayout(BigDecimal amount) {
        String query = "Update sdajdbc.employee set total_payouts = COALESCE(total_payouts, 0) + ?";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBigDecimal(1, amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getPreparedStatement2() {
        try (Connection connection = getConnection()) {
            int minSalary = 100;
            String query = "select ename, job, sal, mgr from sdajdbc.employee where sal > ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, minSalary);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int sal = resultSet.getInt("sal");
                Integer mgr = resultSet.getInt("mgr");
                if (resultSet.wasNull()) {
                    mgr = null;
                }
                System.out.println(ename + " " + job + " " + sal + " " + mgr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void callableStatement() {
        try (Connection connection = getConnection()) {
            String query = "{call sdajdbc.getName(?,?)}";
            CallableStatement callableStatement =
                    connection.prepareCall(query);
            callableStatement.setInt(1, 7654);
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            callableStatement.execute();
            String name = callableStatement.getString(2);
            System.out.println(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void preparedStatement() {
        try (Connection connection = getConnection()) {
            int minSalary = 100;
            String query = "select ename, job, sal from sdajdbc.employee where sal > ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, minSalary);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int sal = resultSet.getInt("sal");
                System.out.println(ename + " " + job + " " + sal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void statement() {
        try (Connection connection = getConnection()) {
            String query = "select ename, job, sal from sdajdbc.employee where sal > 100";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
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


    public static List<String> findEmployeeBySalaryRange(int salaryMin, int salaryMax) {
        List<String> employees = new ArrayList<>();
        String query = "SELECT empno, ename, sal FROM sdajdbc.employee WHERE sal > ? " +
                "AND sal < ? ";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, salaryMin);
            preparedStatement.setInt(2, salaryMax);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add("" +
                        resultSet.getInt("empno") +
                        resultSet.getString("ename") +
                        resultSet.getInt("sal")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static void sqlInjectionStatement(String firstName) {
        try (Connection connection = getConnection()) {
            String query =
                    "select ename, job, sal " +
                            "from sdajdbc.employee " +
                            "where ename = '" + firstName + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int sal = resultSet.getInt("sal");
                System.out.println(ename + " " + job + " " + sal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
