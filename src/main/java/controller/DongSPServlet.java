package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.DongSP;
import service.DongSanPhamService;
import service.imple.DongSPServiceImple;

import java.io.IOException;

@WebServlet({
        "/dong-sp/index",
        "/dong-sp/create",
        "/dong-sp/insert",
        "/dong-sp/edit",
        "/dong-sp/update",
        "/dong-sp/delete"
})
public class DongSPServlet extends HttpServlet {
    private final DongSanPhamService dongSPService;

    public DongSPServlet() {
        dongSPService = new DongSPServiceImple();
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
        request.setAttribute("list", dongSPService.findAllByObject());
        request.getRequestDispatcher("/views/dong-sp/index.jsp")
                .forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/dong-sp/create.jsp")
                .forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
//        DongSP mauSac = dongSPService
//                .findAllByObject()
//                .stream()
//                .filter(t -> t.getId() == id)
//                .findFirst()
//                .orElse(null);
//        request.setAttribute("dongSP", mauSac);
        request.getRequestDispatcher("/views/dong-sp/update.jsp")
                .forward(request, response);
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
        int id = dongSPService.findAllByObject().size();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
//        DongSP mauSac = new DongSP(++id, ma, ten);
//        dongSPService.save(mauSac);
//        request.setAttribute("list", dongSPService.findAllByObject());
        request.getRequestDispatcher("/views/dong-sp/index.jsp")
                .forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
//        DongSP mauSac = new DongSP(id, ma, ten);
//        dongSPService.update(mauSac);
        response.sendRedirect("/StoreManager_war_exploded/dong-sp/index");
    }


}
