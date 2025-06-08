/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr_management_system;

/**
 *
 * @author ABS
 */
public class Recruitment {
    private String name;
    private String education;
    private String skills;
    private float testScores;
    private String interviewerComments;
    Connector c= new Connector();


    public Recruitment(String name, String education, String skills, float testScores, String interviewerComments) {
        this.name = name;
        this.education = education;
        this.skills = skills;
        this.testScores = testScores;
        this.interviewerComments = interviewerComments;
    }
    public void insert(){
        String query = "insert into Recruitment (Candidate_name, Candidate_education, Candidate_skills,Candidate_score, Candidate_comment )values('" + this.name + "','" + this.education + "' ,'" + this.skills+ "','" + this.testScores+ "','" + this.interviewerComments+ "' )";
        c.runDML(query);

        System.out.println("Added");
    }
    public void update(int id){
        try{
            
            
            c.runDML("update Recruitment set Candidate_name='"+this.name+"',Candidate_education='"+this.education+"',Candidate_skills='"+this.skills+"',Candidate_score='"+this.testScores+"',Candidate_comment='"+this.interviewerComments+"'where ID= "+id);
        }catch(Exception e){e.printStackTrace();}
    }
    public void Delete(int id){
        String query = "DELETE FROM Recruitment WHERE ID = "+ id;
        c.runDML(query);
    
    }

    public String getName() {
        return name;
    }

    public String getEducation() {
        return education;
    }

    public String getSkills() {
        return skills;
    }

    public float getTestScores() {
        return testScores;
    }

    public String getInterviewerComments() {
        return interviewerComments;
    }
    
}
