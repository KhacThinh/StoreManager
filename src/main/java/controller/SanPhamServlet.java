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
import java.util.UUID;

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
        UUID id = UUID.fromString(request.getParameter("id"));
        SanPham sanPham = sanPhamService.findById(id);
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
        StringBuilder stringBuilder = new StringBuilder();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        if (ma.equals("")) {
            stringBuilder.append("trỗng mã ");
        }
        if (ten.equals("")) {
            stringBuilder.append("trỗng tên ");
        }
        boolean checkMa = sanPhamService
                .findAllByObject()
                .stream()
                .anyMatch(sanPham -> sanPham.getMa().equalsIgnoreCase(ma));
        if (checkMa) {
            stringBuilder.append("trùng mã ");
        }
        if (stringBuilder.length() > 0) {
            SanPham sanPham = new SanPham(null, ma, ten);
            request.setAttribute("sanPham", sanPham);
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.setAttribute("list", sanPhamService.findAllByObject());
            request.getRequestDispatcher("/views/san-pham/index.jsp")
                    .forward(request, response);
        } else {
            SanPham sanPham = new SanPham(null, ma, ten);
            sanPhamService.save(sanPham);
            response.sendRedirect("/StoreManager_war_exploded/san-pham/index");
        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        UUID id = UUID.fromString(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        if (ma.equals("")) {
            stringBuilder.append("trỗng mã ");
        }
        if (ten.equals("")) {
            stringBuilder.append("trỗng tên ");
        }
        if (stringBuilder.length() > 0) {
            SanPham sanPham = sanPhamService.findById(id);
            request.setAttribute("sanPham", sanPham);
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.getRequestDispatcher("/views/san-pham/update.jsp")
                    .forward(request, response);
        } else {
            SanPham sanPham = new SanPham(id, ma, ten);
            sanPhamService.update(sanPham);
            response.sendRedirect("/StoreManager_war_exploded/san-pham/index");

        }
    }


}
