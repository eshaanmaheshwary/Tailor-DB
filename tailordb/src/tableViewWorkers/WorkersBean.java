package tableViewWorkers;

public class WorkersBean 
{
 String wname;
 String mobile;
 String address;
 String spl;

 public WorkersBean() {}

public WorkersBean(String wname, String mobile, String address, String spl) {
	super();
	this.wname = wname;
	this.mobile = mobile;
	this.address = address;
	this.spl = spl;
}

public String getWname() {
	return wname;
}

public void setWname(String wname) {
	this.wname = wname;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getSpl() {
	return spl;
}

public void setSpl(String spl) {
	this.spl = spl;
}


}



