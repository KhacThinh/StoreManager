package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.SanPham;
import service.SanPhamService;
import service.imple.SanPhamServiceImple;

import java.io.IOException;

@WebServlet({
        "/san-pham/index",
        "/san-pham/create",
        "/san-pham/insert",
        "/san-pham/edit",
        "/san-pham/update",
        "/san-pham/delete"
})
public class SanPhamServlet extends HttpServlet {
    private final SanPhamService sanPhamService;

    public SanPhamServlet() {
        sanPhamService = new SanPhamServiceImple();
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
        request.setAttribute("list", sanPhamService.findAllByObject());
        request.getRequestDispatcher("/views/san-pham/index.jsp")
                .forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/san-pham/create.jsp")
                .forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SanPham sanPham = sanPhamService
                .findAllByObject()
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
        request.setAttribute("sanPham", sanPham);
        request.getRequestDispatcher("/views/san-pham/update.jsp")
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
        int id = sanPhamService.findAllByObject().size();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        SanPham sanPham = new SanPham(++id, ma, ten);
        sanPhamService.save(sanPham);
        response.sendRedirect("/StoreManager_war_exploded/san-pham/index");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        SanPham sanPham = new SanPham(id, ma, ten);
        sanPhamService.update(sanPham);
        response.sendRedirect("/StoreManager_war_exploded/san-pham/index");
    }


}
