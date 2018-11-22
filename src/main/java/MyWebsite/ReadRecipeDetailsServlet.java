package MyWebsite;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MsgBean;
import beans.RecipeDetailBean;
import beans.SessionBean;
import service.RecipeListService;

@WebServlet("/ReadRecipeDetailsServlet")
public class ReadRecipeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadRecipeDetailsServlet() {        
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
     
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");		
		
        int recipeId = sessionBean.getcurrentRecipeId();
               
        MsgBean msgobj = new MsgBean();
        msgobj.setMessage("fields:" +  recipeId + ":" + recipeId + ":");
        request.setAttribute("msgBean", msgobj);
        
        RecipeDetailBean recipeobj = new RecipeDetailBean();
        
        RequestDispatcher rd = null;
        RecipeListService recipeService = new RecipeListService();
        
        recipeobj = recipeService.getRecipeDetails(recipeId);
        
        request.setAttribute("recipeDetailBean",recipeobj);
        
        rd=request.getRequestDispatcher("editrecipe.jsp");
        rd.forward(request, response);  
	}
}
