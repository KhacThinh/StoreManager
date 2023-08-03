package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.ChucVu;
import service.ChucVuService;
import service.imple.ChucVuServiceImple;

import java.io.IOException;
import java.util.UUID;

@WebServlet({
        "/chuc-vu/index",
        "/chuc-vu/create",
        "/chuc-vu/insert",
        "/chuc-vu/edit",
        "/chuc-vu/update",
        "/chuc-vu/delete"
})
public class ChucVuServlet extends HttpServlet {
    private final ChucVuService chucVuService;

    public ChucVuServlet() {
        chucVuService = new ChucVuServiceImple();
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
        request.setAttribute("list", chucVuService.findAllByObject());
        request.getRequestDispatcher("/views/chuc-vu/index.jsp")
                .forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/chuc-vu/create.jsp")
                .forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        ChucVu chucVu = chucVuService.findById(id);
        request.setAttribute("chucVu", chucVu);
        request.getRequestDispatcher("/views/chuc-vu/update.jsp")
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
        boolean checkMa = chucVuService
                .findAllByObject()
                .stream()
                .anyMatch(sanPham -> sanPham.getMa().equalsIgnoreCase(ma));
        if (checkMa) {
            stringBuilder.append("trùng mã.");
        }
        ChucVu chucVu = new ChucVu(null, ma, ten);
        if (stringBuilder.length() > 0) {
            request.setAttribute("chucVu", chucVu);
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.setAttribute("list", chucVuService.findAllByObject());
            request.getRequestDispatcher("/views/chuc-vu/index.jsp")
                    .forward(request, response);
        } else {
            chucVuService.save(chucVu);
            response.sendRedirect("/StoreManager_war_exploded/chuc-vu/index");
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
            ChucVu chucVu = chucVuService.findById(id);
            request.setAttribute("chucVu", chucVu);
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.getRequestDispatcher("/views/chuc-vu/update.jsp")
                    .forward(request, response);
        } else {
            ChucVu chucVu = new ChucVu(id, ma, ten);
            chucVuService.update(chucVu);
            response.sendRedirect("/StoreManager_war_exploded/chuc-vu/index");
        }
    }


}
