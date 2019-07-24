package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.ConnectionUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Details extends Application {
	 Connection con = ConnectionUtil.getDataBaseConnection();


	ResultSet rs;
	
	PreparedStatement pst;
	
	public static String name;
	public static String course;
	public static String roll;
	public static String address;
	public static String phone;
	public static String yoj;
	public static String parent;
	public static String parPhone;
	public static String attendance;

	 Details(String rollNo, String course) {
		 System.out.println("Details rollNo = " + rollNo);
		 try {
			 String query = "SELECT * from student WHERE rno = ?  AND course = ?";
			 	roll = rollNo;
			 	
				pst = con.prepareStatement(query);
				pst.setString(1, rollNo);
				pst.setString(2, course);
				System.out.println("***Detail course = " + course + "roll: "+ rollNo);

				rs = pst.executeQuery();
				if(rs.next()) {
					name = rs.getString("name");
					this.course = rs.getString("course");
					roll = rs.getString("rno");
					address = rs.getString("address");
					phone = rs.getString("contact");
					yoj = rs.getString("yoj");
					parent = rs.getString("prntName");
					parPhone = rs.getString("prntCntct");
					attendance = rs.getString("classesAttended");
					System.out.println("***Detail try***");
				}
				System.out.println("***Detail try out***");

				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("***Detail catch***");

			}
	}
	 
	 public static String getter() {
		 return name;
	 }

	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			System.out.println("***Detail start***");

			Parent root = FXMLLoader.load(getClass().getResource("/FXML/Details.fxml"));
			Scene scene = new Scene(root,750,420);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Attendance AITH");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
