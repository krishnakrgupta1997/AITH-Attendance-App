package application;

public class Student {
    //private String rollNo;
    private String fullName;
    private String rollNo;
    private String course;
    private Boolean single;
 /*
    public Student(int roll, String fullName, boolean b) {
    	this.rollNo = roll;
        this.fullName = fullName;
        this.single = (b);
    }
    
    public Student() {
		// TODO Auto-generated constructor stub
	}
*/
	public String getFullName() {
        return fullName;
    }
 
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getRollNo() {
        return rollNo;
    }
 
    public void setRoll(String rollNo) {
        this.rollNo = rollNo;
    }
    
    public String getCourse() {
        return course;
    }
 
    public void setCourse(String course) {
        this.course = course;
    }
    
 
    public Boolean isSingle() {
        return single;
    }
 
    public void setSingle(Boolean newValue) {
        this.single = newValue;
    }
}


