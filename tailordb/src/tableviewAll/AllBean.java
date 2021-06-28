package tableviewAll;

public class AllBean 
{
int oid;
String custname;
String custmobile;
String dress;
String spl;
String doo;
String dod;
String amount;
int status;
public AllBean() {}
public AllBean(int oid, String custname, String custmobile, String dress, String spl, String doo, String dod, String amount, int status) 
{
	super();
	this.oid = oid;
	this.custname = custname;
	this.custmobile = custmobile;
	this.dress = dress;
	this.spl = spl;
	this.doo = doo;
	this.dod = dod;
	this.amount = amount;
	this.status = status;
}
public int getOid() 
{
	return oid;
}
public void setOid(int oid) 
{
	this.oid = oid;
}
public String getCustname()
{
	return custname;
}
public void setCustname(String custname) 
{
	this.custname = custname;
}
public String getCustmobile() 
{
	return custmobile;
}
public void setCustmobile(String custmobile) 
{
	this.custmobile = custmobile;
}
public String getDress() 
{
	return dress;
}
public void setDress(String dress) 
{
	this.dress = dress;
}
public String getSpl() 
{
	return spl;
}
public void setSpl(String spl) 
{
	this.spl = spl;
}
public String getDoo() 
{
	return doo;
}
public void setDoo(String doo) 
{
	this.doo = doo;
}
public String getDod() 
{
	return dod;
}
public void setDod(String dod) 
{
	this.dod = dod;
}
public String getAmount() 
{
	return amount;
}
public void setAmount(String amount) 
{
	this.amount = amount;
}
public int getStatus() 
{
	return status;
}
public void setStatus(int status) 
{
	this.status = status;
};	
}
