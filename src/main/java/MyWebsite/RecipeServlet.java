package MyWebsite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MsgBean;
import beans.RecipeBean;
import beans.RecipeDetailBean;
import beans.SessionBean;
import service.RecipeDataService;


/**
 * Servlet implementation class RecipeServlet
 */
@WebServlet("/RecipeServlet")

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
		// TODO Auto-generated method stub
		LOGGER.info("Logger Name: "+LOGGER.getName());
		
		// get session attributes
		HttpSession session = request.getSession();
		if (request.getParameter("JSESSIONID") != null) {
		    Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
		    response.addCookie(userCookie);
		} else {
		    String sessionId = session.getId();
		    Cookie userCookie = new Cookie("JSESSIONID", sessionId);
		    response.addCookie(userCookie);
		}
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		if (sessionBean == null) {
			sessionBean = new SessionBean();
			session.setAttribute("sessionBean", sessionBean);
		}
		
		MsgBean msgobj = new MsgBean();
    	request.setAttribute("msgBean", msgobj);    	
    	
		String param = "";
		int index = 0;
		String action = "";
		int recipeId = 0;
		String place = "";
		
        RequestDispatcher rd = null;
        try {
        //get action and recipe id
		param = (String)request.getParameter("actionAndrecipeid");
        index = param.indexOf(",");
        action = param.substring(0,index);
              
        recipeId=Integer.valueOf(param.substring(index+1));
        sessionBean.setcurrentRecipeId(recipeId);
        
        RecipeDataService recipeService = new RecipeDataService();
        
        if (action.equals("Edit")) {
            //get recipe detail object
            RecipeDetailBean recipeobj = recipeService.getRecipeDetails(recipeId);  
        	request.setAttribute("recipeDetailBean",recipeobj);        	
        	rd=request.getRequestDispatcher("editrecipe.jsp");
            rd.forward(request, response);
            
        } else if (action.equals("Show")) {
            //get recipe detail object
            RecipeDetailBean recipeobj = recipeService.getRecipeDetails(recipeId);            
        	request.setAttribute("recipeDetailBean",recipeobj);
            rd=request.getRequestDispatcher("showrecipe.jsp");
            rd.forward(request, response);
            
        } else if (action.equals("Delete")) {
        	int rc=recipeService.deleteRecipe(recipeId);
        }
            
        ArrayList<RecipeBean> recipelistobj = new ArrayList<RecipeBean>();
        recipelistobj = recipeService.findRecipes(sessionBean.getCurrentPage(), sessionBean.getRecordsPerPage(), sessionBean.getFilterValue());
            
        int rows = recipeService.getNumberOfRows(sessionBean.getFilterValue());
        int noOfPages = rows / sessionBean.recordsPerPage;
        
        if (noOfPages % sessionBean.recordsPerPage > 0) {
            noOfPages++;
        }
        
        sessionBean.setnoOfPages(noOfPages);
        sessionBean.setRecipeListObj(recipelistobj);
        rd=request.getRequestDispatcher("listrecipe.jsp");
        rd.forward(request, response);      
             
        }
	    catch (Exception e) {
	        LOGGER.log(Level.SEVERE, "Caught exception while in doPost. Please investigate: " 
	                + e 
	                + Arrays.asList(e.getStackTrace())
	                .stream()
	                .map(Objects::toString)
	                .collect(Collectors.joining("\n")), e
	        );
	        msgobj.setMessage(place + ":" + session.getId() + ":" + session + ":" + sessionBean);
	         
	    }
        finally {

            rd=request.getRequestDispatcher("listrecipe.jsp");
            rd.forward(request, response);
        }

	}

}
