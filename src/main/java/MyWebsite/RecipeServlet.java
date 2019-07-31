package MyWebsite;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		// get session attributes
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");

        RequestDispatcher rd = null;
        
        //get action and recipe id
		String param = (String)request.getParameter("actionAndrecipeid");
        int index = param.indexOf(",");
        String action = param.substring(0,index);
        sessionBean.setcurrentRecipeId(Integer.valueOf(param.substring(index+1)));
        
        //get recipe detail object
        RecipeDetailBean recipeobj = new RecipeDetailBean();
        RecipeDataService recipeService = new RecipeDataService();
        recipeobj = recipeService.getRecipeDetails(sessionBean.getcurrentRecipeId());
        
        if (action.equals("Edit")) {
        	request.setAttribute("recipeDetailBean",recipeobj);
        	rd=request.getRequestDispatcher("editrecipe.jsp");
            rd.forward(request, response);
        } else if (action.equals("Show")) {
        	request.setAttribute("recipeDetailBean",recipeobj);
            rd=request.getRequestDispatcher("showrecipe.jsp");
            rd.forward(request, response);
        } else if (action.equals("Delete")) {
        	int rc=recipeService.deleteRecipe(sessionBean.getcurrentRecipeId());
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
        }else if (action.equals("List")) {
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
        } else {
            rd=request.getRequestDispatcher("listrecipe.jsp");
            rd.forward(request, response);
        }

	}

}