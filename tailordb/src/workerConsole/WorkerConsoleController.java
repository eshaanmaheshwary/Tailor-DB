package workerConsole;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class WorkerConsoleController 
{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML
    private ImageView image;
    
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtName"
    private TextField txtName; // Value injected by FXMLLoader

    @FXML // fx:id="txtMobile"
    private TextField txtMobile; // Value injected by FXMLLoader

    @FXML // fx:id="txtAddress"
    private TextField txtAddress; // Value injected by FXMLLoader

    @FXML // fx:id="comboSpecialization"
    private ComboBox<String> comboSpecialization; // Value injected by FXMLLoader

    @FXML // fx:id="txtSpecialization"
    private TextField txtSpecialization; // Value injected by FXMLLoader
    String txtPath = "", selItems = "";
    PreparedStatement pst;
    @FXML
    void doSpecialization(ActionEvent event) 
    {
    	String sitem=comboSpecialization.getSelectionModel().getSelectedItem();
    	if(sitem==null) return;
        selItems+=sitem+",";
        txtSpecialization.setText(selItems+"");
    }
    
    @FXML
    void doBrowse(ActionEvent event) 
    {
    	FileChooser r = new FileChooser();
    	File s = r.showOpenDialog(null);
    	if(s != null)
    	{
    		try
    		{
    			Image images = new Image(new FileInputStream(s.getAbsolutePath().toString()));
    			image.setImage(images);
    			txtPath = new String( s.getAbsolutePath().toString());
    		}
    		catch(FileNotFoundException e)
    		{
    			e.printStackTrace();
    		}
    	}
    	else
    	{
    		showMsg("No File Selected");
    	}
    }

    @FXML
    void doDelete(ActionEvent event) 
    {
    	try{
    		pst=con.prepareStatement("delete  from workers where wname=?");
    		pst.setString(1, txtName.getText());
    		int count=pst.executeUpdate();
    		if(count==0)
    				showMsg("Invaild Worker Name!");
    		else
    			showMsg("Record Deleted Successfully!");
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }

    @FXML
    void doFetch(ActionEvent event) 
    {
    	try{
			pst=con.prepareStatement("select * from workers where wname=?"); 
			pst.setString(1, txtName.getText());
			ResultSet table=pst.executeQuery();
			boolean jasus=false;
			image.setImage(null);
			while(table.next())
			{
				jasus=true;
				String name =table.getString("wname");
    			String mobile= table.getString("mobile");
    			String addr=table.getString("address");
    			String spl = table.getString("spl");
    			String imagess = table.getString("picpath");
    			txtName.setText(name);;
    			txtMobile.setText(mobile);
    			txtAddress.setText(addr);
    			txtSpecialization.setText(spl);
    			Image images = new Image(new FileInputStream(imagess));
    			image.setImage(images);
			}
			
			if(jasus==false)
				showMsg("Invalid Worker Name!");
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
    }

    @FXML
    void doNew(ActionEvent event) 
    {
    	txtName.setText("");
    	txtMobile.setText("");
    	txtAddress.setText("");
    	txtSpecialization.setText("");
    	comboSpecialization.getSelectionModel().select(0);
    	txtSpecialization.setText("");
    	image.setImage(null);
    	selItems = "";
    }

    @FXML
    void doSave(ActionEvent event) 
    {
    	try 
    	{
    		pst = con.prepareStatement("insert into workers values (?,?,?,?,?,current_date())");
    		pst.setString(1, txtName.getText());
    		pst.setString(2, txtMobile.getText());
    		pst.setString(4, txtAddress.getText());
    		pst.setString(3, txtPath);
    		pst.setString(5, txtSpecialization.getText());
    		pst.executeUpdate();
    		showMsg("Worker Details have been Saved Successfully!");
    	}
    		
    	catch (SQLException e) 
    	{
    		e.printStackTrace();
    	}
    }
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("INFORMATION");
    	alert.setContentText(msg);
    	alert.show();
    }

    @FXML
    void doUpdate(ActionEvent event) 
    {
    	try {
			pst=con.prepareStatement("update workers set mobile=?,picpath = ?,address = ?,spl = ? where wname =?");
			pst.setString(5, txtName.getText());
			pst.setString(1, txtMobile.getText());
			pst.setString(4, txtSpecialization.getText());
			pst.setString(3, txtAddress.getText());
			pst.setString(2, txtPath);
			int count=pst.executeUpdate();
			if(count==0)
			showMsg("Invalid Worker Name!");
			else
				showMsg("Workers Record has been Updated!");

		}
	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
    	comboSpecialization.getItems().clear();
        ArrayList<String> ary = new ArrayList<String>(Arrays.asList("Select","T - Shirts/Shirts","Trousers/Jeans","Kurta - Pyjama","Coat","Blazers","Basket","Suit"));
            comboSpecialization.getItems().addAll(ary);
            comboSpecialization.getSelectionModel().select(0);
            
    	
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'WorkerConsole.fxml'.";
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'WorkerConsole.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'WorkerConsole.fxml'.";
        assert comboSpecialization != null : "fx:id=\"comboSpecialization\" was not injected: check your FXML file 'WorkerConsole.fxml'.";
        assert txtSpecialization != null : "fx:id=\"txtSpecialization\" was not injected: check your FXML file 'WorkerConsole.fxml'.";
        con = MySQLConnection.doConnect();
    	
    }
}
