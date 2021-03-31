package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class EmployeePayrollTest{
    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        List<EmployeePayrollData> employeePayrollDataList = employeePayroll.readData();
        Assert.assertEquals(4, employeePayrollDataList.size());
    }
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldReturn1() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        employeePayroll.readData();
        int salaryUpdated= employeePayroll.updateEmployeeDataUsingStatement();
        Assert.assertEquals(1, salaryUpdated);
    }
    @Test
    public void givenNewSalaryForEmployee_WhenUpdatedUsingPreparedStatement_ShouldReturn1() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        employeePayroll.readData();
        int salaryUpdated= employeePayroll.updateEmployeeDataUsingPreparedStatement("Charlie", 3000000);
        Assert.assertEquals(1, salaryUpdated);
    }
    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        String sql = "SELECT * FROM payroll_service WHERE start BETWEEN CAST('2019-01-01' AS DATE) AND DATE(NOW());";
        List<EmployeePayrollData> employeePayrollDataList = employeePayroll.readData();
        Assert.assertEquals(2, employeePayrollDataList.size());
    }
    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchSumByGender() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        String sql = "SELECT SUM(salary) FROM payroll_service WHERE gender='M' GROUP BY gender;";
        String fn = "SUM(salary)";
        double result = employeePayroll.DataBaseFunctionsUsingGender(sql, fn);
        Assert.assertEquals(3500000, result);

    }


    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchAvgByGender() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        String sql = "SELECT AVG(salary) FROM payroll_service WHERE gender='M' GROUP BY gender;";
        String fn = "AVG(salary)";
        double result = employeePayroll.DataBaseFunctionsUsingGender(sql, fn);
        Assert.assertEquals(1750000, result);

    }

    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchMaxByGender() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        String sql = "SELECT MAX(salary) FROM payroll_service WHERE gender='M' GROUP BY gender;";
        String fn = "MAX(salary)";
        double result = employeePayroll.DataBaseFunctionsUsingGender(sql, fn);
        Assert.assertEquals(2500000, result);

    }

    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchMinByGender() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        String sql = "SELECT MIN(salary) FROM payroll_service WHERE gender='M' GROUP BY gender;";
        String fn = "MIN(salary)";
        double result = employeePayroll.DataBaseFunctionsUsingGender(sql, fn);
        Assert.assertEquals(1000000, result);

    }

    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchCountByGender() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        String sql = "SELECT COUNT(*) FROM payroll_service WHERE gender='M' GROUP BY gender;";
        String fn = "COUNT(*)";
        double result = employeePayroll.DataBaseFunctionsUsingGender(sql, fn);
        Assert.assertEquals(2, result);

    }

}
