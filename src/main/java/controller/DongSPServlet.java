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
import java.util.UUID;

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
        UUID id = UUID.fromString(request.getParameter("id"));
        DongSP mauSac = dongSPService.findById(id);
        request.setAttribute("dongSP", mauSac);
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
        StringBuilder stringBuilder = new StringBuilder();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        if (ma.equals("")) {
            stringBuilder.append("trỗng mã ");
        }
        if (ten.equals("")) {
            stringBuilder.append("trỗng tên ");
        }
        boolean checkMa = dongSPService
                .findAllByObject()
                .stream()
                .anyMatch(sanPham -> sanPham.getMa().equalsIgnoreCase(ma));
        if (checkMa) {
            stringBuilder.append("trùng mã ");
        }
        if (stringBuilder.length() > 0) {
            DongSP mauSac = new DongSP(null, ma, ten);
            request.setAttribute("dongSP", mauSac);
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.setAttribute("list", dongSPService.findAllByObject());
            request.getRequestDispatcher("/views/dong-sp/index.jsp")
                    .forward(request, response);
        } else {
            DongSP mauSac = new DongSP(null, ma, ten);
            dongSPService.save(mauSac);
            response.sendRedirect("/StoreManager_war_exploded/dong-sp/index");
        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        StringBuilder stringBuilder = new StringBuilder();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        if (ma.equals("")) {
            stringBuilder.append("trỗng mã ");
        }
        if (ten.equals("")) {
            stringBuilder.append("trỗng tên ");
        }
        if (stringBuilder.length() > 0) {
            DongSP mauSac = dongSPService.findById(id);
            request.setAttribute("dongSP", mauSac);
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.getRequestDispatcher("/views/dong-sp/update.jsp")
                    .forward(request, response);
        } else {
            DongSP mauSac = new DongSP(id, ma, ten);
            dongSPService.update(mauSac);
            response.sendRedirect("/StoreManager_war_exploded/dong-sp/index");
        }
    }


}
