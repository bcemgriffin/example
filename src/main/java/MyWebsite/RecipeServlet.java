package MyWebsite;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.IngredientBean;
import beans.MsgBean;
import beans.RecipeBean;
import beans.RecipeDetailBean;
import beans.SessionBean;
import service.RecipeDataService;


/**
 * Servlet implementation class RecipeServlet
 */
@WebServlet("/RecipeServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(RecipeServlet.class.getName());   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		MsgBean msgobj = new MsgBean();
    	request.setAttribute("msgBean", msgobj); 
    	
		// TODO Auto-generated method stub
		LOGGER.info("Logger Name: "+LOGGER.getName());
        
		// get session attributes
		HttpSession session = request.getSession();

		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		if (sessionBean == null) {
			sessionBean = new SessionBean();
			session.setAttribute("sessionBean", sessionBean);
	        
	        // Save default values in session
			sessionBean.setFilterValue("");
			sessionBean.setCurrentPage(1);
			sessionBean.setRecordsPerPage(10);
		} 
		
		//Get parameter values
		String action = request.getParameter("action") != null ? request.getParameter("action") : "List";
		
		String filterValue = request.getParameter("filterValue") != null ? request.getParameter("filterValue") : sessionBean.getFilterValue();
        sessionBean.setFilterValue(filterValue);
		
		int currentPage = request.getParameter("currentPage") != null ? Integer.valueOf(request.getParameter("currentPage")) : sessionBean.getCurrentPage();
        sessionBean.setCurrentPage(currentPage);
        
        int recordsPerPage = request.getParameter("recordsPerPage") != null ? Integer.valueOf(request.getParameter("recordsPerPage")) : sessionBean.getRecordsPerPage();
		sessionBean.setRecordsPerPage(recordsPerPage);
    	
		int recipeId = request.getParameter("recipeid") != null ? Integer.valueOf(request.getParameter("recipeid")) : sessionBean.getcurrentRecipeId() ;
		sessionBean.setcurrentRecipeId(recipeId);
		
        RequestDispatcher rd = null;
        try {
            
	        RecipeDataService recipeService = new RecipeDataService();
	        
	        if (action.equals("Edit")) {
	             //get recipe detail object
	            RecipeDetailBean recipeobj = recipeService.getRecipeDetails(recipeId);  
	        	request.setAttribute("recipeDetailBean",recipeobj);        	
	        	rd=request.getRequestDispatcher("editrecipe.jsp");
	            
	        } else if (action.equals("Show")) {     	
	            //get recipe detail object
	            RecipeDetailBean recipeobj = recipeService.getRecipeDetails(recipeId);            
	        	request.setAttribute("recipeDetailBean",recipeobj);
	            rd=request.getRequestDispatcher("showrecipe.jsp");
	            
	        } else if (action.equals("Delete")) {
	            //Save Recipe ID in the session data
	            sessionBean.setcurrentRecipeId(recipeId);
	        	int rc=recipeService.deleteRecipe(recipeId);
	            msgobj.setMessage("rc:" + rc);
	            
	        } else if (action.equals("Add") || action.equals("Update")) {
	        	String recipeName = request.getParameter("recipeName");
	            int recipeYield = Integer.valueOf(request.getParameter("recipeYield"));
	            String recipeYieldunit = request.getParameter("recipeYieldunit");
	            String recipePreptime = request.getParameter("recipePreptime");
	            String recipeCooktime = request.getParameter("recipeCooktime");
	            String recipeDirections = request.getParameter("recipeDirections");
	     
	            String photoName=""; 
	            String savePath="";
	            Part filePart = request.getPart("recipePhoto");
	            if (filePart != null) {
	            	photoName =Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	            	//photoName = "recipe" + recipeId + "photo";
	            	Map<String,String> envmap = System.getenv();
	            	savePath=envmap.get("CATALINA_HOME") + "/webapps/MyWebsite/images/" + photoName;
	        		
	        		try {
	        			filePart.write(savePath);}
	        		catch (Exception e) {
	        			msgobj.setMessage("Write exception: " + e);	        		
	        		}	                
	            }
	        	
	            String ingredientName[] = request.getParameterValues("ingredientName");
	            String ingredientAmt1[] = request.getParameterValues("ingredientAmt1");
	            String ingredientAmt2[] = request.getParameterValues("ingredientAmt2");
	            String ingredientUnit[] = request.getParameterValues("ingredientUnit");
	            ArrayList<IngredientBean> ingredientlist = new ArrayList<IngredientBean>();
	           
	            for (int i=0 ;  i<ingredientName.length; i++ ) {
	            	if (ingredientName[i].length() > 0) {
	             		ingredientlist.add(new IngredientBean(ingredientAmt1[i], ingredientAmt2[i], ingredientUnit[i], ingredientName[i]));
	            	}
	            }
	            
	            //RecipeId will be 0 when adding new recipe until SQL Insert returns generated key
	            RecipeDetailBean recipeobj = new RecipeDetailBean(recipeId, recipeName, recipeYield, recipeYieldunit, recipePreptime, recipeCooktime, recipeDirections, photoName, ingredientlist);
	            
	            int rc=0;
	            if (action.equals("Add")) {
	                rc=recipeService.insertRecipeDetails(recipeobj);
	            } else {
	            	//Run update process
	            	rc=recipeService.updateRecipeDetails(recipeobj);
	            }
	            
	            msgobj.setMessage("rc:" + rc + "Recipe: " + recipeobj.toString());
	            request.setAttribute("msgBean", msgobj);
	            
	            rd=request.getRequestDispatcher("listrecipe.jsp");
	        }
	        
	        //Default action is List  also called for Delete, Add and Update because we need to refresh RecipeList
	        if (action.equals("List") || action.equals("Delete") || action.equals("Add") || action.equals("Update")) {
	        	
		        int rows = recipeService.getNumberOfRows(sessionBean.getFilterValue());
		        sessionBean.setnoOfPages(((rows/sessionBean.getRecordsPerPage()) + (rows % sessionBean.getRecordsPerPage() > 0 ? 1 : 0)));
		        
		        if (sessionBean.getCurrentPage() > sessionBean.getnoOfPages()) {
		        	sessionBean.setCurrentPage(sessionBean.getCurrentPage()-1);
		        }
		        
		        ArrayList<RecipeBean> recipelistobj = new ArrayList<RecipeBean>();
		        recipelistobj = recipeService.findRecipes(sessionBean.getCurrentPage(), sessionBean.getRecordsPerPage(), sessionBean.getFilterValue());
		        
		        sessionBean.setRecipeListObj(recipelistobj);
		        rd=request.getRequestDispatcher("listrecipe.jsp");
   
		        msgobj.setMessage("CurrPg:" + sessionBean.getCurrentPage() + " noOfPg:" + sessionBean.getnoOfPages() + " Rows:" + rows);
	        }	             
        }
	    catch (Exception e) {
	        LOGGER.log(Level.SEVERE, "Caught exception while in doPost. Please investigate: " 
	                + e 
	                + Arrays.asList(e.getStackTrace())
	                .stream()
	                .map(Objects::toString)
	                .collect(Collectors.joining("\n")), e
	        );
	        msgobj.setMessage(session.getId() + ":" + session + ":" + sessionBean);
	        
        	sessionBean.setCurrentPage(0);
            rd=request.getRequestDispatcher("listrecipe.jsp");
	    }
        finally {
            rd.forward(request, response);
        }
	}

}
