package com.display;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectDB extends HttpServlet{
	 public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
          Statement stmt=null;
          ResultSet rs=null;
          Connection conn= null;
         
          String host = "localhost";
          String port = "5432";
          String db_name = "postgres";
          String username = "postgres";
          String password = "33744525";
     
          // Set response content type
          response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          String title = "Database Result";
          String docType =
          "<!doctype html" +
          "transitional//en\">\n";
          out.println(docType +
          "<html>\n" +
          "<head><title>" + title + "</title></head>\n" +
          "<body>");
          try
          {
               // Register JDBC driver
              Class.forName("org.postgresql.Driver");

               // Open a connection
              conn = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", "" + username + "", "" + password + "");
              
               // Execute SQL query
               stmt = conn.createStatement();
               String sql;
               sql = "SELECT * FROM students2";
               rs = stmt.executeQuery(sql);
               out.println("<table border=1 >");
               out.println("<caption><h2>Students Record</h2></caption>");

               out.println("<tr style='background-color:#ffffb3; color:red'>");
               out.println("<th>student_id</th>");
               out.println("<th> name</th>");
               out.println("<th>grade</th>");
               out.println("<th>marks</th>");
               out.println("</tr>");
               // Extract data from result set
               while(rs.next())
               {
                    //Retrieve by column name
                    String student_id = rs.getString("student_id");
                    String name = rs.getString("name");
                    String grade = rs.getString("grade");
                    String marks = rs.getString("marks");
                   // int marks = rs.getInt("marks");

                    //Display values
                    out.println("<tr style='background-color:#b3ffd9;'>");
                    out.println("<td>" + student_id + "</td>");
                    out.println("<td>" + name + "</td>");
                    out.println("<td>" + grade + "</td>");
                    out.println("<td>" + marks + "</td>");
                    out.println("</tr>");
               }
               out.println("</table>");
               out.println("</bod;=y></html>");

               // Clean-up environment
               rs.close();
               stmt.close();
               conn.close();
          }
          catch(SQLException se)
          {
               se.printStackTrace();
          }
          catch(Exception e)
          {
               e.printStackTrace();
          }
          finally
          {
               //finally block used to close resources
               try
               {
                    if(stmt!=null)
                         stmt.close();
               }
               catch(SQLException se2)
               {}// nothing we can do
               try
               {
               if(conn!=null)
                    conn.close();
               }
               catch(SQLException se)
               {
               se.printStackTrace();
               }
          }
     }

}
