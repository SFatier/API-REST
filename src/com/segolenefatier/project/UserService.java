package com.segolenefatier.project;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class UserService {
	
   //list is working as a database
   UserDao user = new UserDao();

   @GET
   @Path("/users")
   @Produces(MediaType.APPLICATION_JSON)
   public List<User> recuper_students(){
	   return user.getAllUsers();
   }	
   
   @GET
   @Path("/user/{uid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response selectUser(@PathParam("uid") int uid){
	   User user3;
	   try{
		   user3 = user.getUser(uid);
		   if (user3 == null){
			   return Response.status(404).entity("Not found ErrorResponse").build();
		   }		   
	   }catch(Exception e){
		   return Response.status(401).entity("Must be Connect --> verification Wamp or function connection").build();
	   }
	   return Response.status(200).entity(user3).build();
   }
   @DELETE
   @Path("/user/{uid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response deleteUser(@PathParam ("uid") int uid){
	   Boolean validation =  user.isAdmin(109);
	   int user_test;
	   try{
		   if (validation == false){
			   return Response.status(403).entity("Must be admin").build();
		   }else{
			   User user3 = user.getUser(uid);
			   if (user3 == null){
				   return Response.status(202).entity("No datas ExistingUser").build();
			   }
			   user_test = user.DeleteUser(uid);
			   if (user_test == 0){
				 return Response.status(404).entity("Not found Error Response").build();  
			   	}
		   }
	   }catch(Exception e){
		   return Response.status(401).entity("Must be Connect --> verification Wamp or function connection").build();
	   }
	   return Response.status(204).build();
   }   
   @PUT
   @Path("/user/{uid}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateUser(@PathParam ("uid") int uid, String firstname, String lastname, String mail, String password, String role)
   {
	   Boolean validation =  user.isAdmin(109);
	   int user_test;
	   try{
		   if (validation == false){
			   return Response.status(403).entity("Must be admin").build();
		   }else{
			   User user3 = user.getUser(uid);
			   if (user3 == null){
				   return Response.status(202).entity("No datas ExistingUser").build();
			   }
			   user_test = user.UpdateUser(uid, "tddoto", "Rosdde1", "maàil", "passworddd", "role");
			   if (user_test == 0){
				 return Response.status(404).entity("Not found Error Response").build();  
			   	}
		   }
	   }catch(Exception e){
		   return Response.status(401).entity("Must be Connect --> verification Wamp or function connection").build();
	   }
	   return Response.status(200).build();
   }
   @POST
   @Path("/users")
   @Produces(MediaType.APPLICATION_JSON)
   public Response insertUser()
   {
	   Boolean validation =  user.isAdmin(109);
	   User user2 = null;
	   try {
		   if (validation == false){
			   return Response.status(403).entity("Must be admin").build();
		   }else{
			   user2 = user.InsertUser("etnayeeziurhiu", "etnarzrkiii5", "etnaezrzerr@etnjhia9.net", "2zer0f416", "normal");
			    if (user2 == null){
			    	return Response.status(400).entity("ErrorResponse").build();
			    }
		   }
	   }catch (Exception e){
		   return Response.status(401).entity("Must be Connect --> verification Wamp or function connection").build();
	   }
	  return Response.status(201).entity(user2).build();
   }
   
   @GET
   @Path("/users/search/name={name}&count={count}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response SearchNameCount(@PathParam("name") String name, @PathParam("count") String count) {
	   User user2 = null;
	   try {
			   user2 = user.SearchUserNameCount(name, count);
			    if (user2 == null){
			    	return Response.status(400).entity("ErrorResponse").build();
			    }
	   }catch (Exception e){
		   return Response.status(401).entity("Must be Connect --> verification Wamp or function connection").build();
	   }
	  return Response.status(200).entity(user2).build();  
   }
   
   @GET
   @Path("/users/search/email={email}&count={count}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response SearchEmailCount(@PathParam("email") String email, @PathParam("count") String count) {
	   User user2 = null;
	   try {
			   user2 = user.SearchUserEmailCount(email, count);
			    if (user2 == null){
			    	return Response.status(400).entity("ErrorResponse").build();
			    }
	   }catch (Exception e){
		   return Response.status(401).entity("Must be Connect --> verification Wamp or function connection").build();
	   }
	  return Response.status(200).entity(user2).build();  
   }
   
   @GET
   @Path("/users/search/name={name}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response SearchName(@PathParam("name") String name) {
	   User user2 = null;
	   try {
			   user2 = user.SearchUserName(name);
			    if (user2 == null){
			    	return Response.status(400).entity("ErrorResponse").build();
			    }
	   }catch (Exception e){
		   return Response.status(401).entity("Must be Connect --> verification Wamp or function connection").build();
	   }
	  return Response.status(200).entity(user2).build();  
   }
   
   @GET
   @Path("/users/search/email={email}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response SearchEmail(@PathParam("email") String email) {
	   User user2 = null;
	   try {
			   user2 = user.SearchUserEmail(email);
			    if (user2 == null){
			    	return Response.status(400).entity("ErrorResponse").build();
			    }
	   }catch (Exception e){
		   return Response.status(401).entity("Must be Connect --> verification Wamp or function connection").build();
	   }
	  return Response.status(200).entity(user2).build();  
   }

}