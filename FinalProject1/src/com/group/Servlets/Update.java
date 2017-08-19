package com.group.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import java.sql.*; 
/**
 * Servlet implementation class update
 */
@WebServlet(name= "Update", urlPatterns= {"/Update"})
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
		 PrintWriter out = response.getWriter();
	        HttpSession session = request.getSession();
	       String userId=(String) session.getAttribute("session1");
	       //out.println(userId);
	        ServletContext sc1 = getServletContext();
	        DbMgr dbMgr = (DbMgr) sc1.getAttribute("DbMgr"); 
	        Connection con = dbMgr.getConnection();
	       
	       String Address =request.getParameter("Address");
	       
	       String city =request.getParameter("userCity");
	       PreparedStatement pr;
	        try {
	            pr = con.prepareStatement("update Registeredusers set address=?, city=? where user_id=?");
	            pr.setString(1,Address);
	            pr.setString(2,city);
	            pr.setString(3,userId);
	            pr.executeUpdate();
	            response.sendRedirect("UpdatedProfile.html");
	       //response.sendRedirect("/final.html");
	        } catch (SQLException ex) {
	            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
		//catch (SQLException e) {
			//e.printStackTrace();
			//}
		
		
		
		//RequestDispatcher rd = sc1.getRequestDispatcher("/UpdatedProfile.html");
		//PrintWriter out1 = response.getWriter();
		//out1.println("<font color=green>profile updated" + "</font>");
		//rd.include(request, response);
		
		//doGet(request, response);
	}

	}
	}