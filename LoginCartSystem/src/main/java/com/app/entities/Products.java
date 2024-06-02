package com.app.entities;

public class Products {
	private int pid;
	private String pname;
	private int quantity;
	
	public Products(int pid, String pname, int quantity) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.quantity = quantity;
	}

	public Products(String pname, int quantity) {
		super();
		this.pname = pname;
		this.quantity = quantity;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Products [pid=" + pid + ", pname=" + pname + ", quantity=" + quantity + "]";
	}
	
	
	
	
}
