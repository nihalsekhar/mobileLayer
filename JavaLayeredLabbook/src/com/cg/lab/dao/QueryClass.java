package com.cg.lab.dao;

public class QueryClass {

	public static final String insertQuery = "insert into purchase_details values(mob_seq.nextval,?,?,?,?,sysdate)";
	public static final String updateMobileCountQuery = "update mobiles set quantity = quantity-1 where mobileid = ?";
	public static final String getDetailsQuery ="select * from mobiles";
	public static final String deleteQuery = "delete from mobiles where mobileid=?";
	public static final String selectQueryBasedOnPrice = "select * from mobiles where price between ? and ?";
	public static final String checkMobileQuantityQuery = "select quantity from mobiles where mobileid = ?";
}
