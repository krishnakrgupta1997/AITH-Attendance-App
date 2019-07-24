package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import application.Attendance;
import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class mainController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addUser;

    @FXML
    private Label welcome;
    
    public static int flag = 0;
    
    @FXML
    void addUserAction(ActionEvent event) {
    	Stage stage = new Stage();
        User wlcms;
		
		wlcms = new User();   
		wlcms.start(stage);
		stage.show();
		System.out.println("Add User Button Clicked");
    }


    @FXML
    void Attendance(ActionEvent event) throws SQLException {
    	//createNewScreen("/FXML/attendance.fxml");
    	flag = 1;
    	Stage stage = new Stage();
    	
        Attendance cse = new Attendance();
        cse.start(stage);
        Stage st = (Stage) addUser.getScene().getWindow();st.hide();
        stage.show();
    }

    @FXML
    void Attendance2(ActionEvent event) throws SQLException {
    	//createNewScreen("/FXML/attendance.fxml");
    	flag=2;
    	Stage stage = new Stage();
    	
        Attendance cse = new Attendance();
        cse.start(stage);
        Stage st = (Stage) addUser.getScene().getWindow();st.hide();
        stage.show();
    }
    
    @FXML
    void initialize() {

    }
}



