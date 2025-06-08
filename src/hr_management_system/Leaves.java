/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr_management_system;

/**
 *
 * @author ABS
 */
public class Leaves  {
    private String leaveDuration;
    private String reasonForLeave;
    private String statusOfLeave;
    private String name;
    Connector c= new Connector();

    public Leaves(String leaveDuration, String reasonForLeave, String statusOfLeave, String name) {
        this.leaveDuration = leaveDuration;
        this.reasonForLeave = reasonForLeave;
        this.statusOfLeave = statusOfLeave;
        this.name = name;
    }

    
    
    public void insert(){
        String query = "insert into Leave (Employee_Name, reasonforleave, leaveduration ,statusofleave)values('" + this.name + "','" + this.reasonForLeave + "' ,'" + this.leaveDuration+ "' ,'"+ this.statusOfLeave + "')";
        c.runDML(query);

        System.out.println("Added");
    }
    public void update(int id){
        try{
            
            
            c.runDML("update Leave set Employee_Name='"+this.name+"',reasonforleave='"+this.reasonForLeave+"',leaveduration='"+this.leaveDuration+"',statusofleave='"+this.statusOfLeave+"'where ID= "+id);
        }catch(Exception e){e.printStackTrace();}
    }
    public void Delete(int id){
        String query = "DELETE FROM Leave WHERE ID = "+ id;
        c.runDML(query);
    
    }

  
    public String getLeaveDuration() {
        return leaveDuration;
    }

    public String getReasonForLeave() {
        return reasonForLeave;
    }

    public String getStatusOfLeave() {
        return statusOfLeave;
    }

    @Override
    public String toString() {
        return "Leaves{" + "leaveDuration=" + leaveDuration + ", reasonForLeave=" + reasonForLeave + ", statusOfLeave=" + statusOfLeave + '}';
    }

    
    
    
}
