/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr_management_system;

import javax.swing.JOptionPane;
import jframes.ManageEmployee;

/**
 *
 * @author ABS
 */
public class Employee {
    //declaring attributes
    
     public String employeeName;
     public String employeeDesignation;
     public int employeeSalary;
     public int employeeAge;
     public String employeeStatus;
    Connector c = new Connector();
    
    
    public Employee(String employeeName, String employeeDesignation, int employeeSalary, int employeeAge, String employeeStatus) {
        
        this.employeeName = employeeName;
        this.employeeDesignation = employeeDesignation;
        this.employeeSalary = employeeSalary;
        this.employeeAge = employeeAge;
        this.employeeStatus = employeeStatus;
    }
    public Employee(){}

    

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }
    public void Insert(){
        
        String query = "insert into emp_info (emp_name, emp_designation, emp_salary ,emp_age,emp_status)values('" + this.employeeName + "','" + this.employeeDesignation + "' ,'" + this.employeeSalary+ "' ,'"+ this.employeeAge + "','"+ this.employeeStatus + "')";
        c.runDML(query);

        System.out.println("Added");
    }
    public void Update(int id){
        try{
            
            
            c.runDML("update emp_info set emp_name='"+this.employeeName+"',emp_designation='"+this.employeeDesignation+"',emp_salary='"+this.employeeSalary+"',emp_age='"+this.employeeAge+"'where emp_id= "+id);
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void Delete(int id){
        String query = "DELETE FROM emp_info WHERE emp_id = "+ id;
        c.runDML(query);
            
    }
    
    
}
