package MyWebsite;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Paths;

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
import java.util.Map;

import service.RecipeDataService;


@WebServlet("/AddRecipeDetailsServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class AddRecipeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRecipeDetailsServlet() {        
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
		
		response.setContentType("text/html;charset=UTF-8");
		MsgBean msgobj = new MsgBean();
		
		HttpSession session = request.getSession();
        SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
                
        String recipeName = request.getParameter("recipeName");
        int recipeYield = Integer.valueOf(request.getParameter("recipeYield"));
        String recipeYieldunit = request.getParameter("recipeYieldunit");
        String recipePreptime = request.getParameter("recipePreptime");
        String recipeCooktime = request.getParameter("recipeCooktime");
        String recipeDirections = request.getParameter("recipeDirections");
 
        String photoName=""; 
        String savePath="";
        File fileSaveDir = null;
        
        Part filePart = request.getPart("recipePhoto");
        if (filePart != null) {
        	photoName =Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        	//photoName = "recipe" + recipeId + "photo";
        	Map<String,String> envmap = System.getenv();
        	savePath=envmap.get("CATALINA_HOME") + "/webapps/MyWebsite/images/" + photoName;
    		//savePath="/opt/tomcat/images/" + photoName;
    		
    		fileSaveDir = new File(savePath);
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
        
        
        
        RequestDispatcher rd = null;
        RecipeDataService recipeService = new RecipeDataService();
       
        for (int i=0 ;  i<ingredientName.length; i++ ) {
        	if (ingredientName[i].length() > 0) {
         		ingredientlist.add(new IngredientBean(ingredientAmt1[i], ingredientAmt2[i], ingredientUnit[i], ingredientName[i]));
        	}
        }
        
        RecipeDetailBean recipeobj = new RecipeDetailBean(0, recipeName, recipeYield, recipeYieldunit, recipePreptime, recipeCooktime, recipeDirections, photoName, ingredientlist);
        
        int rc=0;
        rc=recipeService.insertRecipeDetails(recipeobj);
        
        msgobj.setMessage("rc:" + rc);

        request.setAttribute("currentPage","0");
        request.setAttribute("recordsPerPage","10");
        request.setAttribute("filterValue","");
        request.setAttribute("nOfPages","nOfPages");
        
        request.setAttribute("msgBean", msgobj);
        rd=request.getRequestDispatcher("listrecipe.jsp");  
        rd.forward(request, response); 
	}
	
}