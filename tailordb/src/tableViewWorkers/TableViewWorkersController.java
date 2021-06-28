package tableViewWorkers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewWorkersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<WorkersBean> tblWorkers;

    @FXML
    void doShowAll(ActionEvent event) 
    {
    	TableColumn<WorkersBean, String> snameCol=new TableColumn<WorkersBean, String>("Worker Name");
       	snameCol.setCellValueFactory(new PropertyValueFactory<>("wname"));
    	snameCol.setMinWidth(100);
    	
    	TableColumn<WorkersBean, String> mobile=new TableColumn<WorkersBean, String>("Worker Mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean
    	mobile.setMinWidth(100);
    	
    	TableColumn<WorkersBean, String> adres=new TableColumn<WorkersBean, String>("Worker Address");
    	adres.setCellValueFactory(new PropertyValueFactory<>("address"));
    	adres.setMinWidth(300);
    	
    	TableColumn<WorkersBean, String> spli=new TableColumn<WorkersBean, String>("Worker Specialization");
    	spli.setCellValueFactory(new PropertyValueFactory<>("spl"));
    	spli.setMinWidth(200);
    	
    	tblWorkers.getColumns().clear();
    	tblWorkers.getColumns().addAll(snameCol,mobile,adres,spli);
    	ObservableList<WorkersBean> ary=fetchAllRecords();
    	tblWorkers.setItems(ary);
    }
    Connection con;
    @FXML
    void initialize() 
    {
    	con= MySQLConnection.doConnect();
        assert tblWorkers != null : "fx:id=\"tblWorkers\" was not injected: check your FXML file 'TableViewWorkers.fxml'.";

    }
    PreparedStatement pst;
    ObservableList<WorkersBean> fetchAllRecords()
    {
    ObservableList<WorkersBean> ary = FXCollections.observableArrayList();
    	try{
    		pst=con.prepareStatement("select * from workers");
    		ResultSet table= pst.executeQuery();
    		
    		while(table.next())
    		{
    			String mobile=table.getString("mobile");
    			String name= table.getString("wname");
    			String adress=table.getString("address");
    			String splii=table.getString("spl");
    			WorkersBean obj=new WorkersBean(name, mobile, adress, splii);
    			ary.add(obj);
    		}
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	return ary;

    }
}
