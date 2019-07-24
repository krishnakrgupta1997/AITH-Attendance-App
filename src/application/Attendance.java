package application;
//SimplestTableView.java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Controller.mainController;
import DBConnection.ConnectionUtil;
import application.Student;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Attendance extends Application {
	static Connection con = ConnectionUtil.getDataBaseConnection();
	
	private String course;

public static void main(String[] args) {
Application.launch(args);
}

int full =0;

public void start(Stage stage) throws SQLException {
	if(mainController.flag == 1)course = "CSE3";
	else course = "CSE4";

	//Create a TableView with
	TableView<Student> 
		tables = new TableView<>(StudentTableUtil.getStudentList(course));
	
tables.setEditable(true);
//Add columns to the TableView
tables.getColumns().addAll(
		StudentTableUtil.getRollColumn(),
		StudentTableUtil.getFullNameColumn(),
		StudentTableUtil.getAttendanceColumn(),
		StudentTableUtil.addButtonToTable()
);

VBox vb = new VBox(tables);
vb.setPrefSize(500.0, 350.0);
AnchorPane root = new AnchorPane(vb);

root.setTopAnchor(vb,63.0);
root.setLeftAnchor(vb,15.0);
root.setPrefHeight(341.0);
root.setPrefWidth(531.0);

//Declare buttons and MenuItems in AnchorPane
Button addUserBut = new Button("Add User");
//Button changePassBut = new Button("Change Password");
Button saveAttendanceBut = new Button("Save Attendance");
Button addStudentBut = new Button("Add Student");

MenuItem menuItem1 = new MenuItem("CSE 3");
MenuItem menuItem2 = new MenuItem("CSE 4");

MenuButton menu = new MenuButton("Take Attendance");
menu.getItems().add(menuItem1);
menu.getItems().add(menuItem2);


//Placing Menu
AnchorPane.setTopAnchor(menu,5.0);
AnchorPane.setLeftAnchor(menu, 20.0);

//Placing button in addUser
AnchorPane.setTopAnchor(addUserBut,5.0);
AnchorPane.setRightAnchor(addUserBut, 5.0);
//AnchorPane.setLeftAnchor(addUserBut, 20.0);
//AnchorPane.setBottomAnchor(addUserBut, 10.0);

//Placing button in addStudent
AnchorPane.setTopAnchor(addStudentBut,100.0);
AnchorPane.setRightAnchor(addStudentBut, 30.0);
//AnchorPane.setLeftAnchor(addStudentBut, 20.0);
//AnchorPane.setBottomAnchor(addStudentBut, 10.0);

//Placing button in refresh
AnchorPane.setTopAnchor(addStudentBut,50.0);
AnchorPane.setRightAnchor(addStudentBut, 30.0);
//AnchorPane.setLeftAnchor(addStudentBut, 20.0);
//AnchorPane.setBottomAnchor(addStudentBut, 10.0);

/*
//Placing button in changePass
AnchorPane.setTopAnchor(changePassBut,5.0);
AnchorPane.setRightAnchor(changePassBut, 100.0);
//AnchorPane.setLeftAnchor(changePassBut, 20.0);
//AnchorPane.setBottomAnchor(changePassBut, 10.0);
*/
//Placing button in saveAttendance
//AnchorPane.setTopAnchor(saveAttendanceBut,40.0);
AnchorPane.setRightAnchor(saveAttendanceBut, 30.0);
//AnchorPane.setLeftAnchor(saveAttendanceBut, 20.0);
AnchorPane.setBottomAnchor(saveAttendanceBut, 50.0);

//Add buttons to AnchorPane
root.getChildren().addAll(menu, addUserBut, addStudentBut, saveAttendanceBut);

menuItem1.setOnAction(new EventHandler<ActionEvent>() {
	@Override
	public void handle(ActionEvent event) {
		tables.getItems().clear();
		mainController.flag = 1;
    	Stage stage = new Stage();
    	
        Attendance cse = new Attendance();
        try {
			cse.start(stage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Stage st = (Stage) menu.getScene().getWindow();st.hide();
        stage.show();
	}
	
});

menuItem2.setOnAction(new EventHandler<ActionEvent>() {
	@Override
	public void handle(ActionEvent event) {
		tables.getItems().clear();

		mainController.flag = 2;
    	Stage stage = new Stage();
    	
        Attendance cse = new Attendance();
        try {
			cse.start(stage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Stage st = (Stage) menu.getScene().getWindow();st.hide();
        stage.show();
	}
	
});


addUserBut.setOnAction(new EventHandler<ActionEvent>() {
	@Override
	public void handle(ActionEvent event) {
		Stage stage = new Stage();
        User wlcm;
		
		wlcm = new User();   
		wlcm.start(stage);
		stage.show();
		System.out.println("Add User Button Clicked");
	}
	
});

addStudentBut.setOnAction(new EventHandler<ActionEvent>() {
	@Override
	public void handle(ActionEvent event) {
		Stage stage = new Stage();
        Add wlcm;
		
		wlcm = new Add();   
		wlcm.start(stage);
		stage.show();
		System.out.println("Add Student Button Clicked");
	}
	
});

saveAttendanceBut.setOnAction(new EventHandler<ActionEvent>() {
	@Override
	public void handle(ActionEvent event) {
		con = ConnectionUtil.getDataBaseConnection();
		
	String query="UPDATE student SET classesAttended = classesAttended+1 WHERE rno = ? AND course = ?";
	try {
		PreparedStatement pst = con.prepareStatement(query);
		for(Student stu: tables.getItems()){
			if(stu.isSingle()){
				// Increment into DB
				pst.setString(1, stu.getRollNo().toString());
				pst.setString(2, course);				
				pst.execute();
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		System.out.println("Button worked ....... DATA SAVED");
	}
	
});

Scene scene = new Scene(root,700,420);
stage.setScene(scene);
stage.setTitle(course);
stage.show();
}

}