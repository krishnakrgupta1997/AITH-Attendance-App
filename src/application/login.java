package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;

import javafx.scene.effect.DropShadow;

import javafx.scene.effect.Reflection;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;

import javafx.scene.text.FontWeight;

import javafx.scene.text.Text;

import javafx.stage.Stage;

import application.Main;

import DBConnection.ConnectionUtil;

public class login extends Application {

  

 String user = "";

 String pw = "";

 String checkUser, checkPw;

  Connection connection = null;
  PreparedStatement preparedStatement = null;
  ResultSet resultSet = null;

    public static void main(String[] args) {

        launch(args);

    }

      

    @Override

    public void start(Stage primaryStage) {
    	connection = ConnectionUtil.getDataBaseConnection();

        //primaryStage.setTitle("Attendance AITH-Login");

         

        BorderPane bp = new BorderPane();

        bp.setPadding(new Insets(10,50,50,50));

         

        //Adding HBox

        HBox hb = new HBox();

        hb.setPadding(new Insets(20,20,20,30));

         

        //Adding GridPane

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(20,20,20,20));

        gridPane.setHgap(5);

        gridPane.setVgap(5);

         

       //Implementing Nodes for GridPane

        Label lblUserName = new Label("Username");

        final TextField txtUserName = new TextField();

        Label lblPassword = new Label("Password");

        final PasswordField pf = new PasswordField();

        Button btnLogin = new Button("Login");
        
        Button newUser = new Button("New User");

        final Label lblMessage = new Label();

         

        //Adding Nodes to GridPane layout

        gridPane.add(lblUserName, 0, 0);

        gridPane.add(txtUserName, 1, 0);

        gridPane.add(lblPassword, 0, 1);

        gridPane.add(pf, 1, 1);

        gridPane.add(btnLogin, 2, 1);

        gridPane.add(newUser, 3, 1);
        
        gridPane.add(lblMessage, 1, 2);

         

                 

        //Reflection for gridPane

        Reflection r = new Reflection();

        r.setFraction(0.7f);

        gridPane.setEffect(r);

         

        //DropShadow effect

        DropShadow dropShadow = new DropShadow();

        dropShadow.setOffsetX(5);

        dropShadow.setOffsetY(5);

         

        //Adding text and DropShadow effect to it

        Text text = new Text("Attendance AITH Login");

        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));

        text.setEffect(dropShadow);

         

        //Adding text to HBox

        hb.getChildren().add(text);

                           

        //Add ID's to Nodes

    bp.setId("bp");

        gridPane.setId("root");

        btnLogin.setId("btnLogin");
        
        newUser.setId("newUser");

        text.setId("text");

                 

        
       

        //Add HBox and GridPane layout to BorderPane Layout

        bp.setTop(hb);

        bp.setCenter(gridPane);          

        //Adding BorderPane to the scene and loading CSS

     Scene scene = new Scene(bp);

     scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

     primaryStage.setScene(scene);
     primaryStage.setTitle("AITH Attendance Login");
       
     //Action for btnLogin

       btnLogin.setOnAction(new EventHandler() {

        public void handle(Event event) {

         checkUser = txtUserName.getText().toString();

         checkPw = pf.getText().toString();
         
         String sql = "SELECT * FROM user WHERE username = ? and password = ?";
         
         try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, checkUser);
			preparedStatement.setString(2, checkPw);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				lblMessage.setText("Incorrect user or pw.");

		        lblMessage.setTextFill(Color.RED);
				
			}else {
				lblMessage.setText("Congratulations!");

		          lblMessage.setTextFill(Color.GREEN);
		          
		          Stage stage = new Stage();
		          Main wlcm = new Main();
		          wlcm.start(stage);
		          stage.show();
		          ((Button)(event.getSource())).getScene().getWindow().hide();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         txtUserName.setText("");

         pf.setText("");

        }

		

        });
       
       newUser.setOnAction(new EventHandler() {

           public void handle(Event event) {
            
        	   Stage stage = new Stage();
               User wlcms;
       		
       			wlcms = new User();   
       			wlcms.start(stage);
       			stage.show();
       			System.out.println("Add User Button Clicked");
		         // ((Button)(event.getSource())).getScene().getWindow().hide();
           }

   		

           });


     //primaryStage.setResizable(false);

     primaryStage.show();

    }

}