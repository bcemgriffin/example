package service;

import beans.IngredientBean;
import beans.RecipeBean;
import beans.RecipeDetailBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// Start of recipe service logic

public class RecipeDataService {

    public ArrayList<RecipeBean> findRecipes(int currentPage, int recordsPerPage, String filterValue)  {
    	
    	Context envContext = null;
    	Connection con = null;
    	Statement st = null;
    	ResultSet rs = null;
    	ArrayList<RecipeBean> recipelistobj = new ArrayList<RecipeBean>();
	    int start = currentPage * recordsPerPage - recordsPerPage;
	    String sql = "select * from RECIPE where name like \"%" + filterValue + "%\" ORDER BY name ASC LIMIT " + start + ", " + recordsPerPage;
    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
//    		con = DriverManager.getConnection("jdbc:mysql://mysql.griffin.local:3306/MyWebsite", "admin", "mysqladmin123");

    		envContext = new InitialContext();
//    		Context initContext  = (Context)envContext.lookup("java:/comp/env");
//    		DataSource ds = (DataSource)initContext.lookup("jdbc/recipeDB");
    		DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/recipeDB");
    		con = ds.getConnection();
    		
    		st = con.createStatement();
    		rs = st.executeQuery(sql);
    		while (rs.next()) {
	    		recipelistobj.add(new RecipeBean(rs.getInt(1), rs.getString(2)));
			}
	        rs.close();
	        st.close();
	        con.close();    
        } catch (NamingException ne) {
    		// TODO Auto-generated catch block
    		ne.printStackTrace();
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
	    		recipeobj.setPhotoName(rs.getString(8));

			}
	        rs.close();
	        st.close();
	        
	        st = con.createStatement();
	      	sql = "select * from INGREDIENT where recipe_id=" + String.valueOf(recipeId);
    		rs = st.executeQuery(sql);
	    	while (rs.next()) {
	    		recipeobj.ingredientlist.add(new IngredientBean(rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(3)));
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
    		if (recipe.getPhotoName().isEmpty()) {
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
    		} else {
        		ps = con.prepareStatement("update RECIPE set name=?, yield=?, yield_unit=?, prep_time=?, cook_time=?, directions=?, photo_name=? where id=?");
        		ps.setString(1,recipe.getName()); 
                ps.setInt(2,recipe.getYield()); 
                ps.setString(3,recipe.getYieldunit()); 
                ps.setString(4,recipe.getPreptime()); 
                ps.setString(5,recipe.getCooktime());
                ps.setString(6,recipe.getDirections());            
                ps.setString(7,recipe.getPhotoName());
                ps.setInt(8,recipe.getId());
                i=ps.executeUpdate();
                ps.close();    			
    		}
	        ps = con.prepareStatement("delete from INGREDIENT where recipe_id=?");
    		ps.setInt(1,recipe.getId());
            i=ps.executeUpdate();
            ps.close();
            
            ArrayList<IngredientBean> ingredientList = recipe.getIngredientlist();
            for (IngredientBean ingredient : ingredientList ) {
		        ps = con.prepareStatement("insert into INGREDIENT (recipe_id, name, amt, amt_string1, amt_string2, unit) values (?, ?, ?, ?, ?, ?)");
	    		ps.setInt(1,recipe.getId());
	    		ps.setString(2, ingredient.getIngredientName());
	    		ps.setDouble(3, ingredient.getIngredientAmt());
	    		ps.setString(4, ingredient.getIngredientAmtString1());
	    		ps.setString(5, ingredient.getIngredientAmtString2());
	    		ps.setString(6, ingredient.getIngredientUnit());
	            i=ps.executeUpdate();
	            ps.close();
            }
	        
	        con.close();
    	} catch (SQLException se) {
    		// TODO Auto-generated catch block
    		se.printStackTrace();
    		i=1;
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
    
    public int insertRecipeDetails(RecipeDetailBean recipe)  {
    	Connection con = null;
    	PreparedStatement ps = null;
    	Statement stmt = null;
    	
    	int recipeId = 100;
    	int i=0;
    	String sql="";
    	    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://mysql.griffin.local:3306/MyWebsite", "admin", "mysqladmin123");
			stmt=con.createStatement();
    		if (recipe.getPhotoName().isEmpty()) {
	    		sql="insert into RECIPE (name, yield, yield_unit, prep_time, cook_time, directions) values ('" + recipe.getName() + "', " + recipe.getYield() + ", '" + recipe.getYieldunit() + "', '" + recipe.getPreptime() + "', '" + recipe.getCooktime() + "', '" + recipe.getDirections() + "')";
    		} else {
	    		sql="insert into RECIPE (name, yield, yield_unit, prep_time, cook_time, directions, photo_name) values ('" + recipe.getName() + "', " + recipe.getYield() + ", '" + recipe.getYieldunit() + "', '" + recipe.getPreptime() + "', '" + recipe.getCooktime() + "', '" +  recipe.getDirections() + "', '" +  recipe.getPhotoName() + "')";
    		}
    		i=stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
    		ResultSet rs = stmt.getGeneratedKeys();
    		if (rs != null && rs.next()) {
    		    recipeId = rs.getInt(1);
    		}
            
            stmt.close();
    		
    		ps = con.prepareStatement("delete from INGREDIENT where recipe_id=?");
    		ps.setInt(1,recipeId);
            ps.executeUpdate();
            ps.close();
            
            ArrayList<IngredientBean> ingredientList = recipe.getIngredientlist();
            for (IngredientBean ingredient : ingredientList ) {
		        ps = con.prepareStatement("insert into INGREDIENT (recipe_id, name, amt, amt_string1, amt_string2, unit) values (?, ?, ?, ?, ?, ?)");
	    		ps.setInt(1,recipeId);
	    		ps.setString(2, ingredient.getIngredientName());
	    		ps.setDouble(3, ingredient.getIngredientAmt());
	    		ps.setString(4, ingredient.getIngredientAmtString1());
	    		ps.setString(5, ingredient.getIngredientAmtString2());
	    		ps.setString(6, ingredient.getIngredientUnit());
	            ps.executeUpdate();
	            ps.close();
            }
	        
	        con.close();
    	} catch (SQLException se) {
    		File file = new File("/opt/tomcat/images/test.log");
    		PrintStream pstream=null;
			try {
				pstream = new PrintStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		// TODO Auto-generated catch block
 
  	      se.printStackTrace(pstream);
     		
    		recipeId=999;
    	} catch (ClassNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	      recipeId=990;
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

    	return recipeId;
    }    
    public int deleteRecipe(int recipeId)  {
    	Connection con = null;
    	PreparedStatement ps = null;
    	int i = 0;
    	    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://mysql.griffin.local:3306/MyWebsite", "admin", "mysqladmin123");

	        ps = con.prepareStatement("delete from RECIPE where id=?");
    		ps.setInt(1,recipeId);
            i=ps.executeUpdate();
            ps.close();
            
	        ps = con.prepareStatement("delete from INGREDIENT where recipe_id=?");
    		ps.setInt(1,recipeId);
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
