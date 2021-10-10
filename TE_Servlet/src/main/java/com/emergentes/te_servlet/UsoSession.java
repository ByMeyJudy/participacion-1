//MAMANI MAMANI MERY JUDITH
package com.emergentes.te_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UsoSession", urlPatterns = {"/UsoSession"})
public class UsoSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsoCookies</title>");
            out.println("</head>");
            out.println("<body>");
            HttpSession session = request.getSession(true);
            //datos de sesion
            Date created = new Date(session.getCreationTime());
            Date accessed = new Date(session.getLastAccessedTime());
            out.println("ID: " + session.getId() + "<br>");
            out.println("Creado: " + created + "<br>");
            out.println("Ultimo acceso: " + accessed + "<br>");
            //introduciendo datos
            String nomDato = request.getParameter("nomDato");
            if (nomDato != null && nomDato.length() > 0) {
                String valor = request.getParameter("valor");
                session.setAttribute(nomDato, valor);
            }
            //mostrar dato de la sesin
            Enumeration e = session.getAttributeNames();
            while (e.hasMoreElements()) {
                String nombre = (String) e.nextElement();
                String valor = session.getAttribute(nombre).toString();
                out.println(nombre + " = " + valor + "<br>");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
