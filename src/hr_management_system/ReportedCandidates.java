/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr_management_system;

import java.util.ArrayList;

/**
 *
 * @author ABS
 */
public class ReportedCandidates extends Employee{
    
    private String reasonForReporting;

    public ReportedCandidates(String reasonForReporting, String employeeName, String employeeDesignation, int employeeSalary, int employeeAge, String employeeStatus) {
        super(employeeName, employeeDesignation, employeeSalary, employeeAge, employeeStatus);
        this.reasonForReporting = reasonForReporting;
    }
    
    public void insert(){
        String query = "insert into Report (emp_name , emp_designation, emp_salary ,emp_age,emp_status,reason)values('" + this.employeeName + "','" + this.employeeDesignation + "' ,'" + this.employeeSalary+ "' ,'"+ this.employeeAge + "','"+ this.employeeStatus + "','"+ this.reasonForReporting + "')";
        c.runDML(query);

        System.out.println("Added");
    }
     public void update(int id){
         try{
            c.runDML("update emp_info set emp_name='"+this.employeeName+"',emp_designation='"+this.employeeDesignation+"',emp_salary='"+this.employeeSalary+"',emp_age='"+this.employeeAge+"',emp_status='"+this.employeeStatus+"'where emp_id= "+id);
        }catch(Exception e){e.printStackTrace();}
         
     }
    
    
  

    public String getResonForReporting() {
        return reasonForReporting;
    }

   

    @Override
    public String toString() {
        return "ReportedCandidates{" + "resonForReporting=" + reasonForReporting +'}';
    }
    
}
