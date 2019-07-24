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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class userController {
	
	Connection con = ConnectionUtil.getDataBaseConnection();
	PreparedStatement pst;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField newPass;

    @FXML
    private TextField newUser;

    @FXML
    private Button addUserButton;
    
    @FXML
    private Label confirmUpdate;

    @FXML
    void addUserButtonAction(ActionEvent event) {
    	try {
			 String query = "INSERT INTO user(userName, password) VALUES(?, ?)";
			 	
			 	
				pst = con.prepareStatement(query);
				pst.setString(1, newUser.getText());
				pst.setString(2, newPass.getText());
				
				pst.executeUpdate();
				confirmUpdate.setText("User Added Successfully!");
				newUser.getScene().getWindow().hide();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }

    @FXML
    void initialize() {
        
    }
}
