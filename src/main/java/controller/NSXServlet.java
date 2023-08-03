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
import java.util.UUID;

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
        UUID id = UUID.fromString(request.getParameter("id"));
        NSX nsx = nsxService.findById(id);
        request.setAttribute("nsx", nsx);
        request.getRequestDispatcher("/views/nsx/update.jsp")
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
        boolean checkMa = nsxService
                .findAllByObject()
                .stream()
                .anyMatch(sanPham -> sanPham.getMa().equalsIgnoreCase(ma));
        if (checkMa) {
            stringBuilder.append("trùng mã ");
        }
        if (stringBuilder.length() > 0) {
            NSX nsx = new NSX(null, ma, ten);
            request.setAttribute("nsx", nsx);
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.setAttribute("list", nsxService.findAllByObject());
            request.getRequestDispatcher("/views/nsx/index.jsp")
                    .forward(request, response);
        } else {
            NSX nsx = new NSX(null, ma, ten);
            nsxService.save(nsx);
            response.sendRedirect("/StoreManager_war_exploded/nsx/index");

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
            NSX nsx = nsxService.findById(id);
            request.setAttribute("nsx", nsx);
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.getRequestDispatcher("/views/nsx/update.jsp")
                    .forward(request, response);
        } else {
            NSX nsx = new NSX(id, ma, ten);
            nsxService.update(nsx);
            response.sendRedirect("/StoreManager_war_exploded/nsx/index");
        }
    }


}
