package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.NSX;
import service.NhaSanXuatService;
import service.imple.NSXServiceImple;

import java.io.IOException;

@WebServlet({
        "/nsx/index",
        "/nsx/create",
        "/nsx/insert",
        "/nsx/edit",
        "/nsx/update",
        "/nsx/delete"
})
public class NSXServlet extends HttpServlet {
    private final NhaSanXuatService nsxService;

    public NSXServlet() {
        nsxService = new NSXServiceImple();
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
        request.setAttribute("list", nsxService.findAllByObject());
        request.getRequestDispatcher("/views/nsx/index.jsp")
                .forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/nsx/create.jsp")
                .forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        NSX nsx = nsxService
//                .findAllByObject()
//                .stream()
//                .filter(t -> t.getId() == id)
//                .findFirst()
//                .orElse(null);
//        request.setAttribute("nsx", nsx);
//        request.getRequestDispatcher("/views/nsx/update.jsp")
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
//        int id = nsxService.findAllByObject().size();
//        String ma = request.getParameter("ma");
//        String ten = request.getParameter("ten");
//        NSX nsx = new NSX(++id, ma, ten);
//        nsxService.save(nsx);
//        request.setAttribute("list", nsxService.findAllByObject());
//        request.getRequestDispatcher("/views/nsx/index.jsp")
//                .forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String ma = request.getParameter("ma");
//        String ten = request.getParameter("ten");
//        NSX nsx = new NSX(id, ma, ten);
//        nsxService.update(nsx);
//        response.sendRedirect("/StoreManager_war_exploded/nsx/index");
    }


}
