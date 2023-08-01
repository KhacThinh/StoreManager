package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.CuaHang;
import service.CuaHangService;
import service.imple.CuaHangServiceImple;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/cua-hang/index",      //GET
        "/cua-hang/create",     //GET
        "/cua-hang/store",      //POST
        "/cua-hang/edit",       //GET
        "/cua-hang/update",     //POST
        "/cua-hang/delete",
        "/cua-hang/search"})    //GET
public class CuaHangServlet extends HttpServlet {
    private final CuaHangService cuaHangService;

    public CuaHangServlet() {
        cuaHangService = new CuaHangServiceImple();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("create")) {
            this.create(req, resp);
        } else if (uri.contains("edit")) {
            this.edit(req, resp);
        } else if (uri.contains("delete")) {
            this.delete(req, resp);
        } else if (uri.contains("search")) {
            this.search(req, resp);
        } else {
            this.index(req, resp);
        }

    }

    protected void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("list", cuaHangService.findAllByObject());
        request.getRequestDispatcher("/views/cua-hang/show.jsp")
                .forward(request, response);
    }

    protected void create(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/cua-hang/create.jsp")
                .forward(req, resp);
    }

    protected void edit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        CuaHang cuaHang = null;
//        cuaHang = cuaHangService.findAllByObject()
//                .stream()
//                .filter(t -> t.getId() == id)
//                .findFirst()
//                .orElse(null);
//        req.setAttribute("cuaHang", cuaHang);
        req.getRequestDispatcher("/views/cua-hang/update.jsp").forward(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
//        CuaHang cuaHang = cuaHangService
//                .findAllByObject()
//                .stream()
//                .filter(t -> t.getId() == id)
//                .findFirst()
//                .orElse(null);
//        cuaHang.setTrangThai(false);
//        cuaHangService.delete(cuaHang);
        req.setAttribute("list", cuaHangService.findAllByObject());
        req.getRequestDispatcher("/views/cua-hang/show.jsp").forward(req, resp);
    }

    protected void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("ten");
        List<CuaHang> list = cuaHangService.findByName(name);
        if (list.isEmpty()) {
            req.setAttribute("searchName", name);
            req.setAttribute("thongBao", "Khong tim thay cua hang " + name);
            req.setAttribute("list", cuaHangService.findAllByObject());
        } else {
            req.setAttribute("list", list);
        }
        req.getRequestDispatcher("/views/cua-hang/show.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("store")) {
            this.store(req, resp);
        } else if (uri.contains("update")) {
            this.update(req, resp);
        }
    }

    protected void store(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = cuaHangService.findAllByObject().size();
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        String diaChi = req.getParameter("diaChi");
        String thanhPho = req.getParameter("thanhPho");
        String quocGia = req.getParameter("quocGia");
//        CuaHang cuaHang = new CuaHang(++id, ma, ten, diaChi, thanhPho, quocGia, true);
//        cuaHangService.save(cuaHang);
        req.setAttribute("list", cuaHangService.findAllByObject());
        req.getRequestDispatcher("/views/cua-hang/show.jsp").forward(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        String diaChi = req.getParameter("diaChi");
        String thanhPho = req.getParameter("thanhPho");
        String quocGia = req.getParameter("quocGia");
//        CuaHang cuaHang = new CuaHang(id, ma, ten, diaChi, thanhPho, quocGia, true);
//        cuaHangService.update(cuaHang);
        req.setAttribute("list", cuaHangService.findAllByObject());
        req.getRequestDispatcher("/views/cua-hang/show.jsp").forward(req, resp);
    }

}
