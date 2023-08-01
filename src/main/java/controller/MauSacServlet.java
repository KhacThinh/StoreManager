package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.MauSac;
import service.MauSacService;
import service.imple.MauSacServiceImple;

import java.io.IOException;

@WebServlet({
        "/mau-sac/index",
        "/mau-sac/create",
        "/mau-sac/insert",
        "/mau-sac/edit",
        "/mau-sac/update",
        "/mau-sac/delete"
})
public class MauSacServlet extends HttpServlet {
    private final MauSacService mauSacService;

    public MauSacServlet() {
        mauSacService = new MauSacServiceImple();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else {
            this.index(request, response);
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("list", mauSacService.findAllByObject());
        request.getRequestDispatcher("/views/mau-sac/index.jsp")
                .forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/mau-sac/create.jsp")
                .forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        MauSac mauSac = mauSacService
//                .findAllByObject()
//                .stream()
//                .filter(t -> t.getId() == id)
//                .findFirst()
//                .orElse(null);
//        request.setAttribute("mauSac", mauSac);
//        request.getRequestDispatcher("/views/mau-sac/update.jsp")
//                .forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("insert")) {
            this.insert(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int id = mauSacService.findAllByObject().size();
//        String ma = request.getParameter("ma");
//        String ten = request.getParameter("ten");
//        MauSac mauSac = new MauSac(++id, ma, ten);
//        mauSacService.save(mauSac);
//        request.setAttribute("list", mauSacService.findAllByObject());
//        request.getRequestDispatcher("/views/mau-sac/index.jsp")
//                .forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String ma = request.getParameter("ma");
//        String ten = request.getParameter("ten");
//        MauSac mauSac = new MauSac(id, ma, ten);
//        mauSacService.update(mauSac);
//        response.sendRedirect("/StoreManager_war_exploded/mau-sac/index");
    }


}
