package MyWebsite;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;

import service.RecipeListService;
@WebServlet("/UpdateRecipeDetailsServlet")
public class UpdateRecipeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRecipeDetailsServlet() {        
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
     
        int recipeId = Integer.valueOf(request.getParameter("recipeId"));
        String recipeName = request.getParameter("recipeName");
        int recipeYield = Integer.valueOf(request.getParameter("recipeYield"));
        String recipeYieldunit = request.getParameter("recipeYieldunit");
        String recipePreptime = request.getParameter("recipePreptime");
        String recipeCooktime = request.getParameter("recipeCooktime");
        String recipeDirections = request.getParameter("recipeDirections");
        int sqlrc = 0;
        RecipeDetailBean recipeobj = new RecipeDetailBean(recipeId, recipeName, recipeYield, recipeYieldunit, recipePreptime, recipeCooktime, recipeDirections);
        
        RequestDispatcher rd = null;
        RecipeListService recipeService = new RecipeListService();
        
        sqlrc = recipeService.updateRecipeDetails(recipeobj);
        MsgBean msgobj = new MsgBean();
//        msgobj.setMessage("sql:" + sqlrc + "fields:" + recipeId + recipeName + recipeYield + recipeYieldunit + recipePreptime + recipeCooktime + recipeDirections);

        request.setAttribute("currentPage","0");
        request.setAttribute("recordsPerPage","10");
        request.setAttribute("filterValue","");
        request.setAttribute("nOfPages","nOfPages");
        
        request.setAttribute("msgBean", msgobj);
        rd=request.getRequestDispatcher("listrecipe.jsp");  
        rd.forward(request, response); 
	}
}
