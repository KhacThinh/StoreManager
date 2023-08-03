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
import java.util.UUID;

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
        } else {
            this.index(req, resp);
        }

    }

    protected void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("ten");
        if (searchName == null) {
            request.setAttribute("list", cuaHangService.findAllByObject());
            request.getRequestDispatcher("/views/cua-hang/show.jsp")
                    .forward(request, response);
        } else {
            request.setAttribute("searchName", searchName);
            List<CuaHang> list = cuaHangService.findByName(searchName);
            if (list.isEmpty()) {
                request.setAttribute("thongBao", "Không tìm thấy " + searchName);
                request.getRequestDispatcher("/views/cua-hang/show.jsp")
                        .forward(request, response);
            } else {
                request.setAttribute("list", list);
                request.getRequestDispatcher("/views/cua-hang/show.jsp")
                        .forward(request, response);
            }
        }
    }

    protected void create(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/cua-hang/create.jsp")
                .forward(req, resp);
    }

    protected void edit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        CuaHang cuaHang = cuaHangService.findById(id);
        req.setAttribute("cuaHang", cuaHang);
        req.getRequestDispatcher("/views/cua-hang/update.jsp").forward(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        CuaHang cuaHang = cuaHangService.findById(id);
        cuaHang.setTrangThai(false);
        cuaHangService.delete(cuaHang);
        req.setAttribute("list", cuaHangService.findAllByObject());
        req.getRequestDispatcher("/views/cua-hang/show.jsp").forward(req, resp);
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
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        String diaChi = req.getParameter("diaChi");
        String thanhPho = req.getParameter("thanhPho");
        String quocGia = req.getParameter("quocGia");
        StringBuilder stringBuilder = new StringBuilder();
        if (ma.equals("")) {
            stringBuilder.append("trỗng mã ");
        }
        if (ten.equals("")) {
            stringBuilder.append("trỗng tên ");
        }
        boolean checkMa = cuaHangService
                .findAllByObject()
                .stream()
                .anyMatch(sanPham -> sanPham.getMa().equalsIgnoreCase(ma));
        if (checkMa) {
            stringBuilder.append("trùng mã.");
        }
        if (stringBuilder.length() > 0) {
            CuaHang cuaHang = new CuaHang(null, ma, ten, diaChi, thanhPho, quocGia, true);
            req.setAttribute("cuaHang", cuaHang);
            req.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            req.setAttribute("list", cuaHangService.findAllByObject());
            req.getRequestDispatcher("/views/cua-hang/create.jsp")
                    .forward(req, resp);
        } else {
            CuaHang cuaHang = new CuaHang(null, ma, ten, diaChi, thanhPho, quocGia, true);
            cuaHangService.save(cuaHang);
            resp.sendRedirect("/StoreManager_war_exploded/cua-hang/index");
        }
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        String diaChi = req.getParameter("diaChi");
        String thanhPho = req.getParameter("thanhPho");
        String quocGia = req.getParameter("quocGia");
        StringBuilder stringBuilder = new StringBuilder();
        if (ma.equals("")) {
            stringBuilder.append("trỗng mã ");
        }
        if (ten.equals("")) {
            stringBuilder.append("trỗng tên ");
        }
        if (stringBuilder.length() > 0) {
            CuaHang cuaHang = cuaHangService.findById(id);
            req.setAttribute("cuaHang", cuaHang);
            req.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            req.getRequestDispatcher("/views/cua-hang/Update.jsp")
                    .forward(req, resp);
        } else {
            CuaHang cuaHang = new CuaHang(id, ma, ten, diaChi, thanhPho, quocGia, true);
            cuaHangService.update(cuaHang);
            resp.sendRedirect("/StoreManager_war_exploded/cua-hang/index");
        }
    }

}
