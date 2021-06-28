package dressDelivery;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import workerConsole.MySQLConnection;

public class DressDeliveryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtOID;

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtAmount;
    PreparedStatement pst;
    @FXML
    void doAccess(ActionEvent event) 
    {
    	try{
			pst=con.prepareStatement("select * from measurements where oid =? "); 
			pst.setString(1, txtOID.getText());
			ResultSet table=pst.executeQuery();
			boolean jasus=false;
			while(table.next())
			{
				jasus=true;
    			String dress = table.getString("dress");
    			String amt = table.getString("amount");
    			txtAmount.setText(amt);
    	        txtItem.setText(dress);
			}
			
			if(jasus==false)
				showMsg("Invalid Order ID!");
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
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
    void doDelivered(ActionEvent event) 
    {
    	try{
			pst=con.prepareStatement("update measurements set status = ? where oid =?"); 
			pst.setString(2, txtOID.getText());
			pst.setInt(1, 0);
			pst.executeUpdate();
			int count=pst.executeUpdate();
			if(count==0)
			showMsg("Invalid Order ID!");
			else
				showMsg("Customer's Dress has been Delivered Successfully!");

		}
	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    Connection con;
    @FXML
    void initialize() 
    {
        assert txtOID != null : "fx:id=\"txtOID\" was not injected: check your FXML file 'DressDelivery.fxml'.";
        assert txtItem != null : "fx:id=\"txtItem\" was not injected: check your FXML file 'DressDelivery.fxml'.";
        assert txtAmount != null : "fx:id=\"txtAmount\" was not injected: check your FXML file 'DressDelivery.fxml'.";
        con = MySQLConnection.doConnect();
    }
}
