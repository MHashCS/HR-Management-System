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
public class Department {
    private String name;
    
    ArrayList<Employee> list_emp = new ArrayList<Employee>(); 
    
    ArrayList<Project> list_proj = new ArrayList<Project>(); 

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getList_emp() {
        return list_emp;
    }

    public void setList_emp(ArrayList<Employee> list_emp) {
        this.list_emp = list_emp;
    }

    public ArrayList<Project> getList_proj() {
        return list_proj;
    }

    public void setList_proj(ArrayList<Project> list_proj) {
        this.list_proj = list_proj;
    }

    @Override
    public String toString() {
        return "Department{" + "name=" + name + ", list_emp=" + list_emp + ", list_proj=" + list_proj + '}';
    }
    
    
}
