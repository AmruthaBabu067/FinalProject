package com.group.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group.data.DbMgr;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String selectQuery = "select user_id from Registeredusers "
							+ "where user_name=? and user_password=?";
		
		ServletContext ctx = getServletContext();
		DbMgr dbMgr = (DbMgr) ctx.getAttribute("DbMgr");

		Connection con = dbMgr.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ps.setString(1, userName);
			ps.setString(2, userPassword);
			ResultSet results = ps.executeQuery();
			HttpSession session = request.getSession();
		     
			String userId="";
			while(results!=null && results.next()){
				userId = results.getString("user_id");
				session.setAttribute("session1", "user_name");
			}
			if(userId!=""){
				RequestDispatcher rd = ctx.getRequestDispatcher("/UserProfile.html");
				
				PrintWriter out = response.getWriter();
				//out.println("<font color=green> Welcome! </font>");
				rd.include(request, response);
			}else{
				RequestDispatcher rd = ctx.getRequestDispatcher("/error.html");
				//PrintWriter out = response.getWriter();
				//out.println("<font color=blue> Oops! User Not Found </font>");
				rd.include(request, response);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
