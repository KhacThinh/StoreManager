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
import java.util.UUID;

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
        UUID id = UUID.fromString(request.getParameter("id"));
        MauSac mauSac = mauSacService.findById(id);
        request.setAttribute("mauSac", mauSac);
        request.getRequestDispatcher("/views/mau-sac/update.jsp")
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
        boolean checkMa = mauSacService
                .findAllByObject()
                .stream()
                .anyMatch(sanPham -> sanPham.getMa().equalsIgnoreCase(ma));
        if (checkMa) {
            stringBuilder.append("trùng mã ");
        }
        if (stringBuilder.length() > 0) {
            MauSac mauSac = new MauSac(null, ma, ten);
            request.setAttribute("mauSac", mauSac);
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.setAttribute("list", mauSacService.findAllByObject());
            request.getRequestDispatcher("/views/mau-sac/index.jsp")
                    .forward(request, response);
        } else {
            MauSac mauSac = new MauSac(null, ma, ten);
            mauSacService.save(mauSac);
            response.sendRedirect("/StoreManager_war_exploded/mau-sac/index");
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
            MauSac mauSac = mauSacService.findById(id);
            request.setAttribute("mauSac", mauSac);
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.getRequestDispatcher("/views/mau-sac/update.jsp")
                    .forward(request, response);
        } else {
            MauSac mauSac = new MauSac(id, ma, ten);
            mauSacService.update(mauSac);
            response.sendRedirect("/StoreManager_war_exploded/mau-sac/index");
        }
    }


}
