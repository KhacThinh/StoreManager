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
        int id = Integer.parseInt(request.getParameter("id"));
//        ChucVu chucVu = chucVuService.findAllByObject().stream()
//                .filter(t -> t.getId() == id)
//                .findFirst()
//                .orElse(null);
//        request.setAttribute("chucVu", chucVu);
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
        int id = chucVuService.findAllByObject().size();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
//        ChucVu chucVu = new ChucVu(++id, ma, ten);
//        if (chucVuService.save(chucVu)) {
//            request.setAttribute("list", chucVuService.findAllByObject());
//            request.getRequestDispatcher("/views/chuc-vu/index.jsp")
//                    .forward(request, response);
//        } else {
//            request.setAttribute("Loi", "Dữ Liệu Rỗng");
//            response.sendRedirect("/StoreManager_war_exploded/chuc-vu/create");
//        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
//        ChucVu chucVu = new ChucVu(id, ma, ten);
//        chucVuService.update(chucVu);
        response.sendRedirect("/StoreManager_war_exploded/chuc-vu/index");
    }


}
