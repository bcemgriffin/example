package MyWebsite;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MainServlet() {        
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
 
		doPost(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String param = request.getParameter("actionAndrecipeid");
        int index = param.indexOf(",");
        String action = param.substring(0,index-1);
        String recipeId = String.valueOf(param.substring(index+1));
        RequestDispatcher rd = null;
        
        if (action == "Edit") {
        	request.setAttribute("recipeId",recipeId);

            rd=request.getRequestDispatcher("./ReadRecipeDetailsServlet");
            rd.forward(request, response);
        } else if (action == "Show") {
            rd=request.getRequestDispatcher("./ShowRecipeDetailsServlet");
            rd.forward(request, response);
        }
        
        rd=request.getRequestDispatcher("listrecipe.jsp");
        rd.forward(request, response);
    }
}
