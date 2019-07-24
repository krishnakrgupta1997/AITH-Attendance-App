package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.ConnectionUtil;
import application.Student;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StudentTableUtil {
	
	static ObservableList<Student> list = FXCollections.observableArrayList();
	static ObservableList<Student> getStudentList(String course) throws SQLException {
		con = ConnectionUtil.getDataBaseConnection();
		
		 String SQL = "Select * from student where course = ?"; 
		 PreparedStatement pst = con.prepareStatement(SQL);
		 pst.setString(1, course);
      ResultSet rs = pst.executeQuery();  
      while(rs.next()){
          //Student cm = new Student(rs.getInt("rno"), rs.getString("name"),false);
      	Student cm = new Student();
      	cm.setFullName(rs.getString("name"));
      	cm.setRoll(rs.getString("rno"));
      	cm.setCourse(course);
      	cm.setSingle(false);
          list.add(cm); 
      } 
	  return list;
	}
	
	static Connection con = null;
	
	
	
	static TableView<Student> tables = new TableView<>(list);

	  TableView<Student> table = new TableView<Student>();	  
	   static TableColumn<Student, String> fullNameCol = new TableColumn<Student, String>("Full Name");
	   static TableColumn<Student, String> rollCol = new TableColumn<Student, String>("Roll");
	   static TableColumn<Student, Boolean> singleCol = new TableColumn<Student, Boolean>("Attendance");
	   static TableColumn<Student, Void> viewCol = new TableColumn<Student, Void>("Details");
	  
	  // ==== FULL NAME (TEXT FIELD) ===
	  public static TableColumn<Student, String> getFullNameColumn(){
		  fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		/*
		  fullNameCol.setCellFactory(TextFieldTableCell.<Student> forTableColumn());
		
		  fullNameCol.setMinWidth(200);
		  
		  fullNameCol.setOnEditCommit((CellEditEvent<Student, String> event) -> {
		      TablePosition<Student, String> pos = event.getTablePosition();

		      String newFullName = event.getNewValue();

		      int row = pos.getRow();
		      Student Student = event.getTableView().getItems().get(row);

		      Student.setFullName(newFullName);
		  });*/
		  
		  return fullNameCol;
	  }
//--------------------------------------------------------------------------------------------------------------------------------	
	  
	  public static TableColumn<Student, String> getRollColumn(){
		  rollCol.setCellValueFactory(new PropertyValueFactory<>("rollNo"));
		  return rollCol;
	  }
	  
	  
//----------------------------------------------------------------------------------------------------------------------------------	  
	  
	  // ==== CheckBox ====
	  public static TableColumn<Student, Boolean> getAttendanceColumn(){
		  /*singleCol.setCellValueFactory(
					new PropertyValueFactory<Student, String>("single")
				);*/
	// On Cell edit commit (for FullName column)
	  
	  singleCol.setCellValueFactory(new Callback<CellDataFeatures<Student, Boolean>, ObservableValue<Boolean>>() {

	      @Override
	      public ObservableValue<Boolean> call(CellDataFeatures<Student, Boolean> param) {
	          Student Student = param.getValue();

	          SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(Student.isSingle());

	          booleanProp.addListener(new ChangeListener<Boolean>() {

	              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
	            		  Boolean newValue) {
	                  Student.setSingle(newValue);
	              }		

				
	          });
	          return booleanProp;
	      }
	  });
	
	  singleCol.setCellFactory(new Callback<TableColumn<Student, Boolean>, TableCell<Student, Boolean>>(){
		  public TableCell<Student, Boolean> call(TableColumn<Student, Boolean> p){
			  CheckBoxTableCell<Student, Boolean> cell = new CheckBoxTableCell<Student, Boolean>();
	          cell.setAlignment(Pos.CENTER);
	          return cell;
		  }

	  });
	
	  singleCol.setMinWidth(200);
	  return singleCol;
  }
//---------------------------------------------------------------------------------------------------------------------
	  //viewCol(Button)
	  static TableColumn<Student, Void>  addButtonToTable() {
	        //TableColumn<Student, Void> viewCol = new TableColumn("Button Column");

	        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = new Callback<TableColumn<Student, Void>, TableCell<Student, Void>>() {
	            @Override
	            public TableCell<Student, Void> call(final TableColumn<Student, Void> param) {
	                final TableCell<Student, Void> cell = new TableCell<Student, Void>() {

	                    private final Button btn = new Button("View");

	                    {
	                    	
	                        btn.setOnAction((ActionEvent event) -> {
	                            
	                        	/////////////////WORK START FROM HERE
	                        	Student stu= (Student) getTableRow().getItem();
	                        	
							Stage stage = new Stage();
		    		        Details wlcm;
							
							wlcm = new Details(stu.getRollNo(),stu.getCourse());   
							wlcm.start(stage);
							stage.show();
		    		        //((Button)(event.getSource())).getScene().getWindow().hide();

	                        });
	                    }

	                    @Override
	                    public void updateItem(Void item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setGraphic(null);
	                        } else {
	                            setGraphic(btn);
	                        }
	                    }
	                };
	                return cell;
	            }
	        };

	        viewCol.setCellFactory(cellFactory);

	        return viewCol;

	    }
	  
	  
//--------------------------------------------------------------------------------------------------------------


}



