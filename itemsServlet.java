/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cart.servlet;

import Controller.ItemController;
import Model.Item;
import Model.Mycart;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ashan
 */
public class itemsServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ItemController itemController=new ItemController();
            String severltname="";
            
            String itemIdG=request.getParameter("it");
            int cateG=Integer.parseInt(request.getParameter("cates"));
            int qtyG=Integer.parseInt(request.getParameter("qtyC"));
            
            if((itemIdG!=null)&&(!itemIdG.equals(""))&&(request.getParameter("cates")!=null)&&(qtyG!=0)){
               switch(cateG){
                   case 1:severltname="/foodView.jsp";
                       break;
                   case 2:severltname="/MusicalView.jsp";
                       break;
                   case 3:severltname="";
                       break;
                   case 4:severltname="";
                       break;
                   case 5:severltname="";
                       break;
                   case 6:severltname="/fashionView.jsp";
                       break;
                   case 7:severltname="/ElectronicView.jsp";
               }
                try {
                   Item item= itemController.searchItem(itemIdG, cateG);
                   if(item!=null){
                       //Mycart(String itemID, String name, String desc, int qty, double price, double totalPrice, String url)
                       double prices=Double.parseDouble(item.getUnitprice());
                       Mycart mycart=new Mycart(item.getItemID(),item.getName(),item.getDesc(),qtyG,prices,prices*qtyG,item.getItemImageURL());
                       boolean addBool=itemController.mycartAdd(mycart);
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(itemsServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
             request.getRequestDispatcher(severltname).forward(request, response);
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
