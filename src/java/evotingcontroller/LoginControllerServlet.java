
package evotingcontroller;

import evoting.dao.UserDAO;
import evoting.dto.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginControllerServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd=null;
       String userid=request.getParameter("userid");
       String password=request.getParameter("password");
      //  System.out.println(userid+password);
       UserDTO user=new UserDTO(userid,password);
       try
       {
          // System.out.println("LoginControllerServlet");
           String result=UserDAO.validateUser(user);
           //System.out.println(result);
           request.setAttribute("result",result);
           request.setAttribute("userid",userid);
           rd=request.getRequestDispatcher("LoginResponse.jsp");
           
       }
       catch(Exception ex)
       {
          ex.printStackTrace(); 
           rd=request.getRequestDispatcher("ShowException.jsp");
           request.setAttribute("Exception",ex);
       }
       finally
       {
           rd.forward(request,response);
       }
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
