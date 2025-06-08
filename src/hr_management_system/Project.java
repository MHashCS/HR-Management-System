/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr_management_system;

/**
 *
 * @author ABS
 */
public class Project {
    private String name;
    private String deadline;
    private String projectManager;
    private String department;
    Connector c= new Connector();

    public Project(String name, String deadline, String projectManager, String department) {
        this.name = name;
        this.deadline = deadline;
        this.projectManager = projectManager;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Project{" + "name=" + name + ", deadline=" + deadline + ", projectManager=" + projectManager + ", department=" + department + '}';
    }
    public void Insert(){
        String query = "insert into Project (Name, Deadline, Project_Manager ,Department)values('" + this.name + "','" + this.deadline + "' ,'" + this.projectManager+ "' ,'"+ this.department+ "')";
        c.runDML(query);

        System.out.println("Added");
    }
    public void Update(int id){
        try{
            
            
            c.runDML("update Project set Name='"+this.name+"',Deadline='"+this.deadline+"',Project_Manager='"+this.projectManager+"',Department='"+this.department+"'where ID= "+id);
        }catch(Exception e){e.printStackTrace();}
    }
    public void Delete(int id){
        String query = "DELETE FROM Project WHERE ID = "+ id;
        c.runDML(query);
    
    }
    
    
    
}
