/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evotingcontroller;

import evoting.dao.UserDAO;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeleteUserControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd=null;
        HttpSession sess= request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return ;
        }
        String data=request.getParameter("data");
        System.out.println("data in deletecontrollerservlet:"+ data);
        try{
            if(data!=null && data.equalsIgnoreCase("uid"))
            {
               ArrayList<String> userIdList=UserDAO.getUserId();
                System.out.println(userIdList+"servlet mein dao se ayi useridlist");
               request.setAttribute("userIdList",userIdList);
               request.setAttribute("result" ,"userIdList");
            }
            else
            {
                UserDetails ud=UserDAO.getUserDetailsById(data);
               
               request.setAttribute("userdetails",ud);
               request.setAttribute("result","details");
               request.setAttribute("update","update");
               
                System.out.println("in else of updateusercontrollerservlet");
            }
         rd=request.getRequestDispatcher("updateuser.jsp");
        }
        catch(SQLException rx)
        {
            rx.printStackTrace();
    }
        finally{
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
