package MyWebsite;

import java.io.IOException;
import service.RecipeListService;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MsgBean;
import beans.RecipeBean;
import beans.SessionBean;

import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class ReadRecipesServlet
 */
@WebServlet("/ReadRecipesServlet")
public class ReadRecipesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadRecipesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
        MsgBean msgobj = new MsgBean();
        request.setAttribute("msgBean",msgobj);
            
        SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
        String filterValue = sessionBean.getFilterValue();
        if (request.getParameter("filterValue") != null) {
        	filterValue = request.getParameter("filterValue");
        };
              
        if (filterValue == null) {
        	filterValue="";
        }

        int currentPage = sessionBean.getCurrentPage();
        if (request.getParameter("currentPage") != null) {
        	currentPage = Integer.valueOf(request.getParameter("currentPage"));
        };
                
        RequestDispatcher rd = null;
        RecipeListService recipeService = new RecipeListService();
        ArrayList<RecipeBean> recipelistobj = new ArrayList<RecipeBean>();
        recipelistobj = recipeService.findRecipes(currentPage, sessionBean.recordsPerPage, filterValue);
        
        int rows = recipeService.getNumberOfRows(filterValue);
        int noOfPages = rows / sessionBean.recordsPerPage;
        
        if (noOfPages % sessionBean.recordsPerPage > 0) {
            noOfPages++;
        }
        
        sessionBean.setCurrentPage(currentPage);
        sessionBean.setnoOfPages(noOfPages);
        sessionBean.setFilterValue(filterValue);
        sessionBean.setRecipeListObj(recipelistobj);
        
        rd=request.getRequestDispatcher("listrecipe.jsp");
        rd.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

