package com.group.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class Profile
 */
@WebServlet(name= "Profile", urlPatterns= {"/Profile"})
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		
		//PrintWriter out1 = response.getWriter();
	        //HttpSession session = request.getSession();
	       //String userId=(String) session.getAttribute("session1");
	       //out.println(userId);
	        //ServletContext sc1 = getServletContext();
	        //DbMgr dbMgr = (DbMgr) sc1.getAttribute("DbMgr"); 
	        //Connection con1 = dbMgr.getConnection();
	        
	        String Phonenumber = request.getParameter("cellnumber");
			String userdate = request.getParameter("date");
			String car = request.getParameter("vehicle");
			String  travelers = request.getParameter("travelers");
			String name = request.getParameter("name");
			String email =  request.getParameter("email");
			//create a connection with database
			ServletContext ctx = getServletContext();
			DbMgr dbmgr = (DbMgr) ctx.getAttribute("DbMgr");
            Connection con = dbmgr.getConnection();
            String insrtQuery = "INSERT INTO BookUser1(phone_number, userdate, car, travelers,refer_name,refer_email)"
					+ " values (?,?,?,?,?,?)";
	       
	       PreparedStatement pr;
	       boolean success = false;
	        try {
	            pr = con.prepareStatement(insrtQuery);
	            pr.setString(1,Phonenumber);
	            pr.setString(2,userdate);
	            pr.setString(3,car);
	            pr.setString(4,travelers);
	            pr.setString(5, name);
	            pr.setString(6,email);
	            success = pr.execute();
	       //response.sendRedirect("Booked.html");
	       //response.sendRedirect("/final.html");
	        } catch (SQLException ex) {
	        	
	            ex.printStackTrace();}
	        
		//catch (SQLException e) {
			//e.printStackTrace();
			//}
		
		
		
		RequestDispatcher rd = ctx.getRequestDispatcher("/Booked.html");
		PrintWriter out1 = response.getWriter();
		out1.println("<font color=green>You are successfully booked" + "</font>");
		rd.include(request, response);
		
		//doGet(request, response);
	}

	}
	
