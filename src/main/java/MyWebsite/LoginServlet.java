package MyWebsite;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MsgBean;
import beans.RecipeBean;
import beans.UserBean;

import javax.servlet.RequestDispatcher;
import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName=request.getParameter("uname");  
        String password=request.getParameter("password");
        
        UserBean userobj = new UserBean();
        userobj.setName(userName);
        userobj.setPassword(password);
        request.setAttribute("userLoginBean",userobj);
        
        MsgBean msgobj = new MsgBean();
        request.setAttribute("msgBean",msgobj);
                
        RequestDispatcher rd = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql.griffin.local:3306/MyWebsite",
			        "admin", "mysqladmin123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			rs = st.executeQuery("select * from USER where userName='" + userName + "' and password='" + password + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//		try {
//			if (rs.next()) {
								
		        List<RecipeBean> recipelistobj = new ArrayList<RecipeBean>();
		        int recipelistcnt=0; 
		        int currentPage=1;
		        int recordsPerPage=1;
		        
		        try {
					rs = st.executeQuery("select * from RECIPE LIMIT " + currentPage + ", " + recordsPerPage);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					while (rs.next()) {
						recipelistobj.add(new RecipeBean(rs.getInt(1), rs.getString(2)));
						++recipelistcnt;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        request.setAttribute("recipeListBean",recipelistobj);
		        msgobj.setMessage(Integer.toString(recipelistcnt));
		        rd=request.getRequestDispatcher("listrecipe.jsp");
				
//			 } else {
//				rd=request.getRequestDispatcher("index.jsp");
//				
//				msgobj.setMessage("Invalid User ID/Password Combination! Please Try Again.");
//			}
//		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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

