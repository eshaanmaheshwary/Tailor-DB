package tableViewCustomers;

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
import tableViewWorkers.MySQLConnection;

public class TableViewCustomersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CustomersBean> tblCustomers;

    @FXML
    void doFetch(ActionEvent event) 
    {
    	TableColumn<CustomersBean, Integer> Order=new TableColumn<CustomersBean, Integer>("Order ID");
       	Order.setCellValueFactory(new PropertyValueFactory<>("oid"));
    	Order.setMinWidth(20);
    	
    	TableColumn<CustomersBean, String> name=new TableColumn<CustomersBean, String>("Customer Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("custname"));
    	name.setMinWidth(160);
    	
    	TableColumn<CustomersBean, String> mobile=new TableColumn<CustomersBean, String>("Customer Mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("custmobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<CustomersBean, String> dres=new TableColumn<CustomersBean, String>("Dress");
    	dres.setCellValueFactory(new PropertyValueFactory<>("dress"));
    	dres.setMinWidth(100);
    	
    	TableColumn<CustomersBean, String> spli=new TableColumn<CustomersBean, String>("Worker alloted");
    	spli.setCellValueFactory(new PropertyValueFactory<>("spl"));
    	spli.setMinWidth(200);
    	
    	TableColumn<CustomersBean, String> date1=new TableColumn<CustomersBean, String>("Order Date");
    	date1.setCellValueFactory(new PropertyValueFactory<>("doo"));
    	date1.setMinWidth(100);
    	
    	TableColumn<CustomersBean, String> date2=new TableColumn<CustomersBean, String>("Delivery Date");
    	date2.setCellValueFactory(new PropertyValueFactory<>("dod"));
    	date2.setMinWidth(100);
    	
    	TableColumn<CustomersBean, String> price=new TableColumn<CustomersBean, String>("Amount");
    	price.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	price.setMinWidth(100);
    	
    	TableColumn<CustomersBean, Integer> stat=new TableColumn<CustomersBean, Integer>("Status");
       	stat.setCellValueFactory(new PropertyValueFactory<>("status"));
    	stat.setMinWidth(20);
    	
    	tblCustomers.getColumns().clear();
    	tblCustomers.getColumns().addAll(Order, name, mobile, dres, spli, date1, date2, price, stat);
    	ObservableList<CustomersBean> ary = fetchAllRecords();
    	tblCustomers.setItems(ary);
    }
    Connection con;
    @FXML
    void initialize() 
    {
    	con= MySQLConnection.doConnect();
        assert tblCustomers != null : "fx:id=\"tblCustomers\" was not injected: check your FXML file 'TableViewCustomers.fxml'.";

    }
    PreparedStatement pst;
    ObservableList<CustomersBean> fetchAllRecords()
    {
    ObservableList<CustomersBean> ary = FXCollections.observableArrayList();
    	try{
    		pst=con.prepareStatement("select * from measurements");
    		ResultSet table= pst.executeQuery();
    		
    		while(table.next())
    		{
    			int Order = table.getInt("oid");
    			String name= table.getString("custname");
    			String mobile=table.getString("custmobile");
    			String dres = table.getString("dress");
    			String splii=table.getString("spl");
    			String date1 = table.getString("doo");
    			String date2 = table.getString("dod");
    			String price = table.getString("amount");
    			int stat = table.getInt("status");
    			CustomersBean obj=new CustomersBean(Order, name, mobile, dres, splii, date1, date2, price, stat);
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
