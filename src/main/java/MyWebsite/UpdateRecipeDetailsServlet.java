package MyWebsite;

import java.io.IOException;
import java.io.InputStream;

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
import beans.RecipeDetailBean;
import beans.SessionBean;

import java.util.ArrayList;

import service.RecipeListService;


@WebServlet("/UpdateRecipeDetailsServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
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
		
		HttpSession session = request.getSession();
        SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
        int recipeId = sessionBean.getcurrentRecipeId();
        
        String recipeName = request.getParameter("recipeName");
        int recipeYield = Integer.valueOf(request.getParameter("recipeYield"));
        String recipeYieldunit = request.getParameter("recipeYieldunit");
        String recipePreptime = request.getParameter("recipePreptime");
        String recipeCooktime = request.getParameter("recipeCooktime");
        String recipeDirections = request.getParameter("recipeDirections");
        
    	Part filePart = request.getPart("recipePhoto");
    	String photoName="";
    	InputStream photo=null;
    			
    	if (filePart != null) {
            // obtains input stream of the upload file
    		photoName=filePart.getName();
            photo=filePart.getInputStream();
        }
    	
        String ingredientName[] = request.getParameterValues("ingredientName");
        String ingredientAmt1[] = request.getParameterValues("ingredientAmt1");
        String ingredientAmt2[] = request.getParameterValues("ingredientAmt2");
        String ingredientUnit[] = request.getParameterValues("ingredientUnit");
        ArrayList<IngredientBean> ingredientlist = new ArrayList<IngredientBean>();
        
        
        
        RequestDispatcher rd = null;
        RecipeListService recipeService = new RecipeListService();
       
        for (int i=0 ;  i<ingredientName.length; i++ ) {
        	if (ingredientName[i] != "") {
        		ingredientlist.add(new IngredientBean(ingredientAmt1[i], ingredientAmt2[i], ingredientUnit[i], ingredientName[i]));
        	}
        }
        
        RecipeDetailBean recipeobj = new RecipeDetailBean(recipeId, recipeName, recipeYield, recipeYieldunit, recipePreptime, recipeCooktime, recipeDirections, photoName, photo, ingredientlist);
 
        recipeService.updateRecipeDetails(recipeobj);
        MsgBean msgobj = new MsgBean();
        msgobj.setMessage(photoName);

        request.setAttribute("currentPage","0");
        request.setAttribute("recordsPerPage","10");
        request.setAttribute("filterValue","");
        request.setAttribute("nOfPages","nOfPages");
        
        request.setAttribute("msgBean", msgobj);
        rd=request.getRequestDispatcher("listrecipe.jsp");  
        rd.forward(request, response); 
	}
}
