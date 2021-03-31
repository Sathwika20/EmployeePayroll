package com.bridgelabz;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class EmployeePayroll {
     private static final String URL = "jdbc:mysql://localhost:3306/payroll_services?useSSL=false";
     private static final String user = "root";
     private static final String password = "sathWIKA@20";
    private Connection establishConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver found!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        listDrivers();
        try {
            System.out.println("\nConnecting to database: " + URL);
            connection = DriverManager.getConnection(URL, user, password);
            System.out.println("Connection established with: " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = driverList.nextElement();
            System.out.println("Driver: " + driverClass.getClass().getName());
        }
    }

    public List<EmployeePayrollData> readData() {
        String sql = "SELECT * FROM payroll_service;";
        List<EmployeePayrollData> employeePayrollData = new ArrayList<>();
        try (Connection connection = this.establishConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("salary");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollData.add(new EmployeePayrollData(id, name, gender, startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollData;
    }
    public int updateEmployeeDataUsingStatement() {
        String sql = String.format("update payroll_service set salary = %.2f where name = '%s';", 30000000.0, "Charlie");
        try (Connection connection = this.establishConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
    int updateEmployeeDataUsingPreparedStatement(String name, double salary) {
        String sql = String.format("update payroll_service set salary = %f where name = '%s';", salary, name);
        try (Connection connection = this.establishConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            return prepareStatement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static void main(String[] args) {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        employeePayroll.establishConnection();
    }
}
