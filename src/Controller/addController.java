package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBConnection.ConnectionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addController {

	Connection con = ConnectionUtil.getDataBaseConnection();
	
	PreparedStatement pst;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField stuName;

    @FXML
    private Button addButton;

    @FXML
    private TextField stuEnrol;

    @FXML
    private TextField stuCourse;

    @FXML
    private TextField stuAddress;

    @FXML
    private TextField stuPhone;

    @FXML
    private TextField stuYOJ;

    @FXML
    private TextField stuPar;

    @FXML
    private TextField parPhone;
    
    @FXML
    private Label confirmUpdate;

    @FXML
    void addButtonAction(ActionEvent event) {
    	try {
			 String query = "INSERT INTO student(rno, name, contact, yoj, prntName, prntCntct, course, address) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			 	
			 	
				pst = con.prepareStatement(query);
				pst.setString(1, stuEnrol.getText());
				pst.setString(2, stuName.getText());
				pst.setString(3, stuPhone.getText());
				pst.setString(4, stuYOJ.getText());
				pst.setString(5, stuPar.getText());
				pst.setString(6, parPhone.getText());
				pst.setString(7, stuCourse.getText());
				pst.setString(8, stuAddress.getText());
				pst.executeUpdate();
				confirmUpdate.setText("Student Added Successfully!");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }

    @FXML
    void initialize() {
        

    }
}
