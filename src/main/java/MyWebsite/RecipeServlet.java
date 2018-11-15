package MyWebsite;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");

        RequestDispatcher rd = null;
        
		String param = (String)request.getParameter("actionAndrecipeid");
        int index = param.indexOf(",");
        String action = param.substring(0,index);
        sessionBean.setcurrentRecipeId(Integer.valueOf(param.substring(index+1)));

      
        MsgBean msgobj = new MsgBean();
        msgobj.setMessage("fields:" +  index + " " + action + " " + sessionBean.getcurrentRecipeId());
        request.setAttribute("msgBean", msgobj);
        
        if (action.equals("Edit")) {
        	rd=request.getRequestDispatcher("ReadRecipeDetailsServlet");
            rd.forward(request, response);
        } else if (action.equals("Show")) {
            rd=request.getRequestDispatcher("ShowRecipeDetailsServlet");
            rd.forward(request, response);
        } else {
            rd=request.getRequestDispatcher("listrecipe.jsp");
            rd.forward(request, response);
        }

	}

}
