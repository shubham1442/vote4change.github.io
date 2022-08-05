/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evotingcontroller;

import evoting.dao.VoteDAO;
import evoting.dto.CandidateInfoDTO;
import evoting.dto.VoteDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddVoterControllerServlet extends HttpServlet {

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
        
         HttpSession session=request.getSession();
        String userid =(String)session.getAttribute("userid");
        
        if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try
        {
            String candidateId=request.getParameter("candidateid");
            System.out.println("AVCS"+candidateId+userid);
           
             VoteDTO obj= new VoteDTO(candidateId,userid);
             boolean result=VoteDAO.addVote(obj);
             CandidateInfoDTO candidate=VoteDAO.getVote(candidateId);
             // System.out.println(candidate);
            System.out.println(result);
            if(result==true)
            {
             session.setAttribute("candidate",candidate);
            }
            request.setAttribute("result", result);
          RequestDispatcher rd=request.getRequestDispatcher("verifyvote.jsp");
              rd.forward(request,response);
        }
        
        catch(Exception ex)
        {
            System.out.println("Exception in AddVoteServlet ");
            ex.printStackTrace();
             request.setAttribute("Exception",ex); 
           RequestDispatcher rd =request.getRequestDispatcher("ShowException.jsp");
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
