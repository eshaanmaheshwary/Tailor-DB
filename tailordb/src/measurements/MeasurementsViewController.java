package measurements;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import workerConsole.MySQLConnection;

public class MeasurementsViewController 
{
PreparedStatement pst;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCustomName;

    @FXML
    private TextField txtCustomMobile;

    @FXML
    private ComboBox<String> comboDresses;

    @FXML
    private ComboBox<String> comboSpecialist;

    @FXML
    private TextArea txtMeasurements;

    @FXML
    private ComboBox<Integer> comboDays;

    @FXML
    private DatePicker datepickerDOD;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextArea txtCustomizations;

    @FXML
    private TextField txtOID;
    
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String data = df.format(new java.util.Date());
    LocalDate date = LocalDate.parse(data);
    int noOfDaysBetween;
    @FXML
    void doDOD(ActionEvent event) 
    {
         LocalDate dateNew = datepickerDOD.getValue();
         if(dateNew == null)
        	 return;
         noOfDaysBetween = (int)ChronoUnit.DAYS.between(date, dateNew);
         int days = Integer.parseInt(noOfDaysBetween+"");
         comboDays.getSelectionModel().select(days);
         showMsg("Your Order will be delivered after "+noOfDaysBetween+" days!");
    }

    @FXML
    void doDresses(ActionEvent event) 
    {
    	int index = comboDresses.getSelectionModel().getSelectedIndex();
        if(index==0)
        return;
        else if(index == 1)
            txtPrice.setText("Rs. 1300");
            else if(index == 2)
            	txtPrice.setText("Rs.1600");
            else if(index == 3)
            	txtPrice.setText("Rs. 2500");
            else if(index == 4)
            	txtPrice.setText("Rs. 3500");
            else if(index == 5)
            	txtPrice.setText("Rs. 4000");
            else if(index == 6)
            	txtPrice.setText("Rs. 3500");
            else if(index == 7)
            	txtPrice.setText("Rs. 10,000"); 
        workerss();
        
        }

    @FXML
    void doFind(ActionEvent event) 
    {
    	try{
			pst=con.prepareStatement("select * from measurements where custname =? and dress = ? order by doo desc limit 1"); 
			pst.setString(1, txtCustomName.getText());
			pst.setString(2, comboDresses.getSelectionModel().getSelectedItem());
			ResultSet table=pst.executeQuery();
			boolean jasus=false;
			while(table.next())
			{
				jasus=true;
				int OrderID=table.getInt("oid");
    			String name= table.getString("custname");
    			String mob = table.getString("custmobile");
    			String dress = table.getString("dress");
    			String specialist = table.getString("spl");
    			String mes = table.getString("measurement");
    			String dod1=table.getDate("dod").toString();
    			String amt = table.getString("amount");
    			String wish = table.getString("wish");
    			int day = table.getInt("days");
    			txtCustomName.setText(name);
    			txtCustomMobile.setText(mob);
    	        txtMeasurements.setText(mes);
    	        txtCustomizations.setText(wish);
    	        txtPrice.setText(amt);
    	        comboDresses.getSelectionModel().select(dress);
    	        comboSpecialist.getSelectionModel().select(specialist);
    	        txtOID.setText(String.valueOf(OrderID));
    			datepickerDOD.getEditor().setText(dod1);
    			comboDays.getSelectionModel().select(day);
			}
			
			if(jasus==false)
				showMsg("Invalid Customer Name!");
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
    }

    @FXML
    void doNew(ActionEvent event) 
    {
    	txtCustomName.setText("");
    	txtCustomMobile.setText("");
    	txtMeasurements.setText("");
    	txtCustomizations.setText("");
    	comboDresses.getSelectionModel().select(0);
    	txtPrice.setText("");
    	comboDays.getSelectionModel().select(0);
    	comboSpecialist.getItems().clear();
    	datepickerDOD.getEditor().clear();
    	txtOID.setText("");
    }

    @FXML
    void doSpecialists(ActionEvent event) 
    {

    }

    @FXML
    void doStore(ActionEvent event) 
    {
    	try 
    	{
    		pst = con.prepareStatement("insert into measurements values (null,?,?,?,?,?,current_date(),?,?,?,?,?)");
    		pst.setString(1, txtCustomName.getText());
    		pst.setString(2, txtCustomMobile.getText());
    		pst.setString(3, comboDresses.getSelectionModel().getSelectedItem());
    		pst.setString(4, comboSpecialist.getSelectionModel().getSelectedItem());
    		pst.setString(5, txtMeasurements.getText());
    		pst.setDate(6, Date.valueOf(datepickerDOD.getValue()));
    		pst.setString(7, txtPrice.getText());
    		pst.setString(8, txtCustomizations.getText());
    		pst.setInt(9,1);
    		pst.setInt(10, comboDays.getSelectionModel().getSelectedItem());
    		pst.executeUpdate();
    		ResultSet rs = pst.getGeneratedKeys();
    		while(rs.next())
    		{
    		showMsg("Customer Details have been Saved Successfully!\nOrder ID: "+rs.getInt(1));
    		}
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
    Connection con;
    @FXML
    void initialize() 
    {
    	comboDresses.getItems().clear();
        ArrayList<String> ary = new ArrayList<String>(Arrays.asList("Select","T - Shirts/Shirts","Trousers/Jeans","Kurta - Pyjama","Coat","Blazers","Basket","Suit"));
            comboDresses.getItems().addAll(ary);
            comboDresses.getSelectionModel().select(0);
            
        for(int i =0; i<=31;i++)
        {
        	comboDays.getItems().add(i);  
        }
          
        assert txtCustomName != null : "fx:id=\"txtCustomName\" was not injected: check your FXML file 'MeasurementsView.fxml'.";
        assert txtCustomMobile != null : "fx:id=\"txtCustomMobile\" was not injected: check your FXML file 'MeasurementsView.fxml'.";
        assert comboDresses != null : "fx:id=\"comboDresses\" was not injected: check your FXML file 'MeasurementsView.fxml'.";
        assert comboSpecialist != null : "fx:id=\"comboSpecialist\" was not injected: check your FXML file 'MeasurementsView.fxml'.";
        assert txtMeasurements != null : "fx:id=\"txtMeasurements\" was not injected: check your FXML file 'MeasurementsView.fxml'.";
        assert comboDays != null : "fx:id=\"comboDays\" was not injected: check your FXML file 'MeasurementsView.fxml'.";
        assert datepickerDOD != null : "fx:id=\"datepickerDOD\" was not injected: check your FXML file 'MeasurementsView.fxml'.";
        assert txtPrice != null : "fx:id=\"txtPrice\" was not injected: check your FXML file 'MeasurementsView.fxml'.";
        assert txtCustomizations != null : "fx:id=\"txtCustomizations\" was not injected: check your FXML file 'MeasurementsView.fxml'.";
        assert txtOID != null : "fx:id=\"txtOID\" was not injected: check your FXML file 'MeasurementsView.fxml'.";
        con = MySQLConnection.doConnect();
    }
    public void workerss()
    {
    	comboSpecialist.getItems().clear();
    	try
    	{
    		pst = con.prepareStatement("select wname from workers where spl like ?");
    		pst.setString(1, "%"+comboDresses.getSelectionModel().getSelectedItem()+"%");
    		ResultSet table = pst.executeQuery();
    		while(table.next())
    		{
    			String name = table.getString("wname");
    			comboSpecialist.getItems().add(name);
    		}
    		
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
}
