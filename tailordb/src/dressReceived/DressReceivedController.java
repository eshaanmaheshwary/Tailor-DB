package dressReceived;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import workerConsole.MySQLConnection;

public class DressReceivedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtRecieved;
    PreparedStatement pst;
    @FXML
    void doReceived(ActionEvent event) 
    {
    	try{
			pst=con.prepareStatement("update measurements set status = ? where oid =?"); 
			pst.setString(2, txtRecieved.getText());
			pst.setInt(1, 2);
			pst.executeUpdate();
			int count=pst.executeUpdate();
			if(count==0)
			showMsg("Invalid Order ID!");
			else
				showMsg("Customer's Dress has been Received Successfully!");

		}
	catch (SQLException e) {
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
    Connection con;
    @FXML
    void initialize() 
    {
        assert txtRecieved != null : "fx:id=\"txtRecieved\" was not injected: check your FXML file 'DressReceived.fxml'.";
        con = MySQLConnection.doConnect();
    }
}
