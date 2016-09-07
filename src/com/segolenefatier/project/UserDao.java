package com.segolenefatier.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
	
      private static String URL ="jdbc:mysql://localhost/restfull";
      private static String LOGIN = "root";
      private static String PASSWORD = "";
      
      Connection conn = null;
	  Statement stm = null;
	  ResultSet rslt = null;
	  ResultSet rslt1= null;
      
	  public void connection (){
		  try {
				Class.forName("com.mysql.jdbc.Driver");
		    	try {
					conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  		  } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
  		  }
	  }
	  
	  public void close(){
		  if(rslt != null) {
			  try {
				rslt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  }
		  
		  if (stm != null){
			  try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  }
		  
		  if (conn != null){
			  try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  }
	  }
	  
      public List<User> getAllUsers(){    	  
    	  List<User> users = new ArrayList<User>();
    	  connection();
    	  try {
    	   	  stm = conn.createStatement();
	    	  rslt = stm.executeQuery("Select * from user");	    	  	    	  
	    	  while (rslt.next())
	    	  {
	    		  int id = rslt.getInt("id");
	    		  String lastname = rslt.getString("lastname");
	    		  String firstname = rslt.getString("firstname");
	    		  String email = rslt.getString("email");
	    		  String password = rslt.getString("password");
	    		  String role = rslt.getString("role");
	    		  
	    		  User user = new User(id,lastname, firstname, email, password, role);
	    		  users.add(user);
	    	  }
    	  }catch (SQLException e){
    		  e.printStackTrace();
    	  }
    	  close();   	     	  
    	  return users;
      }
      
	  public User getUser(int uid){   	  
		  User user = null;
		  connection();
		  try{
	    	  stm = conn.createStatement();
	    	  rslt = stm.executeQuery("Select * from user where id = " + uid);
	    	  while (rslt.next())
	    	  {
	    		  int id = rslt.getInt("id");
	    		  String lastname = rslt.getString("lastname");
	    		  String firstname = rslt.getString("firstname");
	    		  String email = rslt.getString("email");
	    		  String password = rslt.getString("password");
	    		  String role = rslt.getString("role");	    		  
	    		  user = new User(id,lastname, firstname, email, password, role);
	    	  }
    	  }catch (SQLException e){
    		  e.printStackTrace();
    	  }
    	   close();  	     	  
    	  return user;
	   }
	
	  public int DeleteUser(int uid){
		   connection();
		   try{
		    	  stm = conn.createStatement();
		    	  stm.executeUpdate("DELETE FROM user WHERE id = " + uid);
		   
	    	  }catch (SQLException e){
	    		  return 0;
	    	  }
	    	  close();	
	    	  return 1;
	   }
	   
	  public int UpdateUser(int uid, String lastname, String firstname, String email, String password, String role){
		  connection();
		  try{
		    	  stm = conn.createStatement();
		    	  stm.executeUpdate("UPDATE user SET lastname = '"+ lastname +"', firstname = '"+ firstname+"',email = '"+email+"', password = '"+password+"' WHERE id = "+ uid);
		   
	    	  }catch (SQLException e){
	    		 return 0;
	    	  }
	    	close(); 
	    	return 1;
	   }

	  public User InsertUser(String lastname, String firstname, String email, String password, String role){
		  User user = null;
    	  int uid = 0;    	      
    	  connection();    	  
    	  try {
			stm = conn.createStatement();		
	    	  stm.executeUpdate("INSERT INTO `user` (`id`, `lastname`, `firstname`, `email`, `password`, `role`)"
	    	  		+ " VALUES (NULL, '"+ lastname +"', '"+ firstname  +"', '"+ email  +"', '"+ password  +"', '"+ role +"')");
    	  } catch (SQLException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  			}	    	  
	    	  try {
	    		  rslt = stm.getGeneratedKeys();	
		    	  while (rslt.next()){
		    		uid = rslt.getInt(1);    		
				 }					 
				rslt1= stm.executeQuery("select * from user  where id = " +  uid);			  	  
				while (rslt1.next()){
					int id = rslt1.getInt("id");
	   	    	  	String lastname_user = rslt1.getString("lastname");
	   	    	  	String firstname_user = rslt1.getString("firstname");
		   	    	String email_user = rslt1.getString("email");
	   	    	  	String password_user = rslt1.getString("password");
	   	    	  	String role_user = rslt1.getString("role");	  
					user = new User(id, lastname_user, firstname_user, email_user, password_user, role_user);
				}
	    	  } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    	  
			close();		    	     	    	  
    	  return user;
	  }
	  
	   
	  public Boolean isAdmin(int id){
		  String role = null;
		  connection();
		  try{
	    	  stm = conn.createStatement();
	    	  rslt = stm.executeQuery("Select role from user where id = " + id);
	    	  while(rslt.next()){
	    		  role = rslt.getString("role");
	    	  }
    	  }catch (SQLException e){
    		  e.printStackTrace();
    	  }
		  System.out.println(role);
    	   close();
    	   if (role.equals("admin")){
    		   return (true);
    	   }else{
    		   return (false);
    	   }
	  }
	 
	  public User SearchUserEmail(String q){
		  User user = null;
		  connection();
		  try{
	    	  stm = conn.createStatement();
	    	  rslt = stm.executeQuery("SELECT * FROM `user` WHERE email= '" + q +"'" );
	    	  while (rslt.next())
	    	  {
	    		  int id = rslt.getInt("id");
	    		  String lastname = rslt.getString("lastname");
	    		  String firstname = rslt.getString("firstname");
	    		  String email = rslt.getString("email");
	    		  String password = rslt.getString("password");
	    		  String role = rslt.getString("role");	    		  
	    		  user = new User(id,lastname, firstname, email, password, role);
	    	  }
    	  }catch (SQLException e){
    		  e.printStackTrace();
    	  }
    	   close();  	     	  
    	  return user;
	  }
	  
	  public User SearchUserName(String q){
		  User user = null;
		  connection();
		  try{
	    	  stm = conn.createStatement();
	    	  rslt = stm.executeQuery("SELECT * FROM `user` WHERE lastname= '"+ q +"'" );
	    	  while (rslt.next())
	    	  {
	    		  int id = rslt.getInt("id");
	    		  String lastname = rslt.getString("lastname");
	    		  String firstname = rslt.getString("firstname");
	    		  String email = rslt.getString("email");
	    		  String password = rslt.getString("password");
	    		  String role = rslt.getString("role");	    		  
	    		  user = new User(id,lastname, firstname, email, password, role);
	    	  }
    	  }catch (SQLException e){
    		  e.printStackTrace();
    	  }
    	   close();  	     	  
    	  return user;
	  }

	  
	  public User SearchUserEmailCount(String q, String count) {
		  User user = null;
		  connection();
		  try{
	    	  stm = conn.createStatement();
	    	  rslt = stm.executeQuery("Select * from user where email = '" + q + "' LIMIT " + count);
	    	  while (rslt.next())
	    	  {
	    		  int id = rslt.getInt("id");
	    		  String lastname = rslt.getString("lastname");
	    		  String firstname = rslt.getString("firstname");
	    		  String mail = rslt.getString("email");
	    		  String password = rslt.getString("password");
	    		  String role = rslt.getString("role");	    		  
	    		  user = new User(id,lastname, firstname, mail, password, role);
	    	  }
    	  }catch (SQLException e){
    		  e.printStackTrace();
    	  }
    	   close();  	     	  
    	  return user;
	  }

	  public User SearchUserNameCount(String q, String count) {
		  User user = null;
		  connection();
		  try{
	    	  stm = conn.createStatement();
	    	  rslt = stm.executeQuery("Select * from user where lastname = '"+ q + "' LIMIT " + count);
	    	  while (rslt.next())
	    	  {
	    		  int id = rslt.getInt("id");
	    		  String lastname = rslt.getString("lastname");
	    		  String firstname = rslt.getString("firstname");
	    		  String mail = rslt.getString("email");
	    		  String password = rslt.getString("password");
	    		  String role = rslt.getString("role");	    		  
	    		  user = new User(id,lastname, firstname, mail, password, role);
	    	  }
    	  }catch (SQLException e){
    		  e.printStackTrace();
    	  }
    	   close();  	     	  
    	  return user;
	  }
}