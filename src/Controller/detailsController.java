package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBConnection.ConnectionUtil;
import application.Attendance;
import application.Details;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class detailsController {
	
	static Connection con = null;

	//Details dtls;


    @FXML
    private Button backButton;

    @FXML
    private Label stuName;

    @FXML
    private Label stuCourse;

    @FXML
    private Label stuEnrol;

    @FXML
    private Label stuADD;

    @FXML
    private Label stuPhone;

    @FXML
    private Label stuYOJ;

    @FXML
    private Label stuParent;

    @FXML
    private Label parPhone;

    @FXML
    private Label stuAttend;
    
    @FXML
    void backButtonAction(ActionEvent event) throws SQLException {
    	((Button)(event.getSource())).getScene().getWindow().hide();
    }


    @FXML
    void initialize() {
    	System.out.println("controller Details.name=" + Details.name);
    	stuName.setText(Details.name);
		stuCourse.setText(Details.course);
		stuEnrol.setText(Details.roll);
		stuADD.setText(Details.address);
		stuPhone.setText(Details.phone);
		stuYOJ.setText(Details.yoj);
		stuParent.setText(Details.parent);
		parPhone.setText(Details.parPhone);
		stuAttend.setText(Details.attendance);
		System.out.println("****************************" + Details.roll + "***********");
    	

    }
}

