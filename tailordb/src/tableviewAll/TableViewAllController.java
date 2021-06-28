package tableviewAll;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewAllController 
{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboChooseW"
    private ComboBox<String> comboChooseW; // Value injected by FXMLLoader

    @FXML // fx:id="comboChooseS"
    private ComboBox<String> comboChooseS; // Value injected by FXMLLoader

    @FXML // fx:id="txtMobile"
    private TextField txtMobile; // Value injected by FXMLLoader

    @FXML // fx:id="dpDeadline"
    private DatePicker dpDeadline; // Value injected by FXMLLoader

    @FXML // fx:id="tblAll"
    private TableView<AllBean> tblAll; // Value injected by FXMLLoader
    
    
    void fillColumns()
    {
    	TableColumn<AllBean, Integer> Order=new TableColumn<AllBean, Integer>("Order ID");
       	Order.setCellValueFactory(new PropertyValueFactory<>("oid"));
    	Order.setMinWidth(20);
    	
    	TableColumn<AllBean, String> name=new TableColumn<AllBean, String>("Customer Name");
    	name.setCellValueFactory(new PropertyValueFactory<AllBean,String>("custname"));
    	name.setMinWidth(100);
    	
    	TableColumn<AllBean, String> mobile=new TableColumn<AllBean, String>("Customer Mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<AllBean,String>("custmobile"));
    	mobile.setMinWidth(110);
    	
    	TableColumn<AllBean, String> dress=new TableColumn<AllBean, String>("Dress");
    	dress.setCellValueFactory(new PropertyValueFactory<AllBean,String>("dress"));
    	dress.setMinWidth(100);
    	
    	TableColumn<AllBean, String> worker=new TableColumn<AllBean, String>("Worker");
    	worker.setCellValueFactory(new PropertyValueFactory<AllBean,String>("spl"));
    	worker.setMinWidth(100);
    	
    	TableColumn<AllBean, String> dooo=new TableColumn<AllBean, String>("Order Date");
    	dooo.setCellValueFactory(new PropertyValueFactory<AllBean,String>("doo"));
    	dooo.setMinWidth(100);
    	
    	TableColumn<AllBean, String> dodd=new TableColumn<AllBean, String>("Due Date");
    	dodd.setCellValueFactory(new PropertyValueFactory<AllBean,String>("dod"));
    	dodd.setMinWidth(100);
    	
    	TableColumn<AllBean, String> amount=new TableColumn<AllBean, String>("Amount");
    	amount.setCellValueFactory(new PropertyValueFactory<AllBean,String>("amount"));
    	amount.setMinWidth(100);
    	
    	TableColumn<AllBean, Integer> sts=new TableColumn<AllBean, Integer>("Order Status");
    	sts.setCellValueFactory(new PropertyValueFactory<AllBean,Integer>("status"));
    	sts.setMinWidth(100);
    	
    	tblAll.getColumns().clear();
    	tblAll.getColumns().addAll(Order, name, mobile, dress, worker, dooo, dodd, amount, sts);
    }
   
    @FXML
    void doFetch(ActionEvent event) 
    {
    	fillColumns();
    	PreparedStatement pst=null;
    	try{
    		int index = comboChooseS.getSelectionModel().getSelectedIndex();
    		if(index == 1)
    		{
    		pst=con.prepareStatement("select * from measurements where status = 1 and spl = ?");
    		pst.setString(1, comboChooseW.getSelectionModel().getSelectedItem());
    		}
    		else if(index == 2)
    		{
    			pst=con.prepareStatement("select * from measurements where status = 2 and spl = ?");
    		pst.setString(1, comboChooseW.getSelectionModel().getSelectedItem());
    		}
    		else if(index == 3)
    		{
    			pst=con.prepareStatement("select * from measurements where status = 0 and spl = ?");
        		pst.setString(1, comboChooseW.getSelectionModel().getSelectedItem());
    		}
    	}
    	catch(Exception exp){}
    	ObservableList<AllBean> ary=fetchAllRecords(pst);
    	tblAll.setItems(ary);
    }

    @FXML
    void doFullTable(ActionEvent event) 
    {
    	fillColumns();
    	PreparedStatement pst=null;
    	try{
    		pst=con.prepareStatement("select * from measurements");
    	}
    	catch(Exception exp){}
    	ObservableList<AllBean> ary=fetchAllRecords(pst);
    	tblAll.setItems(ary);
    }

    @FXML
    void doGetAll(ActionEvent event) 
    {
    	fillColumns();
    	PreparedStatement pst=null;
    	try{
    		
    		pst=con.prepareStatement("select * from measurements where dod <= ?");
    		pst.setDate(1, Date.valueOf(dpDeadline.getValue()));
    	}
    	catch(Exception exp){}
    	ObservableList<AllBean> ary=fetchAllRecords(pst);
    	tblAll.setItems(ary);
    }

    @FXML
    void doSearch(ActionEvent event) 
    {
    	fillColumns();
    	PreparedStatement pst=null;
    	try{
    		
    		pst=con.prepareStatement("select * from measurements where custmobile = ?");
    		pst.setString(1, txtMobile.getText());
    	}
    	catch(Exception exp){}
    	ObservableList<AllBean> ary=fetchAllRecords(pst);
    	tblAll.setItems(ary);
    }
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
    	
    	ArrayList<String> ary = new ArrayList<String>(Arrays.asList("Select","New Orders","Orders in Progress","Delivered Orders"));
        comboChooseS.getItems().addAll(ary);
        comboChooseS.getSelectionModel().select(0);
       
        assert comboChooseW != null : "fx:id=\"comboChooseW\" was not injected: check your FXML file 'TableViewAll.fxml'.";
        assert comboChooseS != null : "fx:id=\"comboChooseS\" was not injected: check your FXML file 'TableViewAll.fxml'.";
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'TableViewAll.fxml'.";
        assert dpDeadline != null : "fx:id=\"dpDeadline\" was not injected: check your FXML file 'TableViewAll.fxml'.";
        assert tblAll != null : "fx:id=\"tblAll\" was not injected: check your FXML file 'TableViewAll.fxml'.";
        con = MySQLConnection.doConnect();
        workerss();
    }
    public void workerss()
    {
    	comboChooseW.getItems().clear();
    	try
    	{
    		pst = con.prepareStatement("select * from workers");
    		ResultSet table = pst.executeQuery();
    		while(table.next())
    		{
    			String name = table.getString("wname");
    			comboChooseW.getItems().add(name);
    		}
    		
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    PreparedStatement pst;
    ObservableList<AllBean> fetchAllRecords(PreparedStatement pst)
    {
    ObservableList<AllBean> ary=FXCollections.observableArrayList();
    	try{
    		
    		ResultSet table= pst.executeQuery();
    		
    		while(table.next())
    		{
    			int Orderr = table.getInt("oid");
    			String namee = table.getString("custname");
    			String mobilee = table.getString("custmobile");
    			String dresss = table.getString("dress");
    			String spls = table.getString("spl");
    			String doos = table.getDate("doo").toString();
    			String dods = table.getDate("dod").toString();
    			String amounts = table.getString("amount");
    			int statuss = table.getInt("status");
    			AllBean obj=new AllBean(Orderr, namee, mobilee, dresss, spls, doos, dods, amounts, statuss);
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
