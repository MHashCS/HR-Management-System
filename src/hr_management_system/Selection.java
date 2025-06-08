/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr_management_system;

/**
 *
 * @author ABS
 */
public class Selection extends Recruitment {
    
    public Selection(String name, String education, String skills, float testScores, String interviewerComments) {
        super(name, education, skills, testScores, interviewerComments);
    }
    Connector c= new Connector();

    public void insert(){
        String query = "insert into Selection (Candidate_name, Candidate_education, Candidate_skills,Candidate_score, Candidate_comment )values('" + super.getName() + "','" + super.getEducation() + "' ,'" + super.getSkills()+ "','" + super.getTestScores()+ "','" + super.getInterviewerComments()+ "' )";
        c.runDML(query);

        System.out.println("Added");
            
    }
    
}
