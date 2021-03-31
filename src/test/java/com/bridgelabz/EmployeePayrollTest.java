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

}
