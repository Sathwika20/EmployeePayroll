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

}
