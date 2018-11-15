package service;

import MyWebsite.RecipeBean;
import MyWebsite.RecipeDetailBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecipeListService {

    public ArrayList<RecipeBean> findRecipes(int currentPage, int recordsPerPage, String filterValue)  {
    	Connection con = null;
    	Statement st = null;
    	ResultSet rs = null;
    	ArrayList<RecipeBean> recipelistobj = new ArrayList<RecipeBean>();
	    int start = currentPage * recordsPerPage - recordsPerPage;
	    String sql = "select * from RECIPE where name like \"%" + filterValue + "%\" LIMIT " + start + ", " + recordsPerPage;
    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://mysql.griffin.local:3306/MyWebsite", "admin", "mysqladmin123");
    		st = con.createStatement();
    		rs = st.executeQuery(sql);
    		while (rs.next()) {
	    		recipelistobj.add(new RecipeBean(rs.getInt(1), rs.getString(2)));
			}
	        rs.close();
	        st.close();
	        con.close();    		
        } catch (SQLException se) {
        		// TODO Auto-generated catch block
        		se.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} finally {
            //finally block used to close resources
            try {
               if(st!=null)
                  st.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
               if(con!=null)
               con.close();
            } catch(SQLException se) {
               se.printStackTrace();
            } //end finally try
         } //end try

	    return recipelistobj;
    }
    
    public int getNumberOfRows(String filterValue) {
        
        int numOfRows = 0;
    	Connection con = null;
    	Statement st = null;
    	ResultSet rs = null;
    	
        String sql = "SELECT COUNT(id) FROM RECIPE where name like \"%" + filterValue + "%\"";
            
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://mysql.griffin.local:3306/MyWebsite", "admin", "mysqladmin123");
    		st = con.createStatement();
    		rs = st.executeQuery(sql);
	    	while (rs.next()) {
	    		numOfRows = rs.getInt(1);
			}
	        rs.close();
	        st.close();
	        con.close();
    	} catch (SQLException se) {
    		// TODO Auto-generated catch block
    		se.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} finally {
            //finally block used to close resources
            try {
               if(st!=null)
                  st.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
               if(con!=null)
               con.close();
            } catch(SQLException se) {
               se.printStackTrace();
            } //end finally try
         } //end try

        return numOfRows;
    }
    
    public RecipeDetailBean getRecipeDetails(int recipeId) {
    	Connection con = null;
    	Statement st = null;
    	ResultSet rs = null;
       	String sql = "select * from RECIPE where id=" + String.valueOf(recipeId);
	    RecipeDetailBean recipeobj = new RecipeDetailBean();
    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://mysql.griffin.local:3306/MyWebsite", "admin", "mysqladmin123");
    		st = con.createStatement();
    		rs = st.executeQuery(sql);
	    	if (rs.next()) {
	    		recipeobj.setId(rs.getInt(1));
	    		recipeobj.setName(rs.getString(2));
	    		recipeobj.setYield(rs.getInt(3));
	    		recipeobj.setYieldunit(rs.getString(4));
	    		recipeobj.setPreptime(rs.getString(5));
	    		recipeobj.setCooktime(rs.getString(6));
	    		recipeobj.setDirections(rs.getString(7));
			}
	        rs.close();
	        st.close();
	        con.close();
    	} catch (SQLException se) {
    		// TODO Auto-generated catch block
    		se.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} finally {
            //finally block used to close resources
            try {
               if(st!=null)
                  st.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
               if(con!=null)
               con.close();
            } catch(SQLException se) {
               se.printStackTrace();
            } //end finally try
         } //end try
        
	    return recipeobj;
    }
    public int updateRecipeDetails(RecipeDetailBean recipe)  {
    	Connection con = null;
    	PreparedStatement ps = null;
    	int i = 0;
    	    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://mysql.griffin.local:3306/MyWebsite", "admin", "mysqladmin123");
    		ps = con.prepareStatement("update RECIPE set name=?, yield=?, yield_unit=?, prep_time=?, cook_time=?, directions=? where id=?");
    		ps.setString(1,recipe.getName()); 
            ps.setInt(2,recipe.getYield()); 
            ps.setString(3,recipe.getYieldunit()); 
            ps.setString(4,recipe.getPreptime()); 
            ps.setString(5,recipe.getCooktime());
            ps.setString(6,recipe.getDirections());
            ps.setInt(7,recipe.getId());
            i=ps.executeUpdate();
            
	        ps.close();
	        con.close();
    	} catch (SQLException se) {
    		// TODO Auto-generated catch block
    		se.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} finally {
            //finally block used to close resources
            try {
               if(ps!=null)
                  ps.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
               if(con!=null)
               con.close();
            } catch(SQLException se) {
               se.printStackTrace();
            } //end finally try
         } //end try

    	return i;


    }
}
