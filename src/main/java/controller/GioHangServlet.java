package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.*;
import service.*;
import service.imple.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet({
        "/gio-hang/index",
        "/gio-hang/create",
        "/gio-hang/insert"
})
public class GioHangServlet extends HttpServlet {

    private final ChiTietSanPhamService chiTietSPService;
    private final MauSacService mauSacSPService;
    private final NhaSanXuatService nsxService;
    private final DongSanPhamService dongSPService;
    private final SanPhamService sanPhamService;
    private final GioHangService gioHangService;
    private final GioHangChiTietService gioHangChiTietService;
    private final NhanVienService nhanVienService;
    private final KhachHangService khachHangService;

    private static List<GioHangChiTiet> listGHCT = new ArrayList<>();

    public GioHangServlet() {
        chiTietSPService = new ChiTietSPServiceImple();
        mauSacSPService = new MauSacServiceImple();
        nsxService = new NSXServiceImple();
        dongSPService = new DongSPServiceImple();
        sanPhamService = new SanPhamServiceImple();
        gioHangService = new GioHangServiceImple();
        gioHangChiTietService = new GioHangChiTietServiceImple();
        nhanVienService = new NhanVienServiceImple();
        khachHangService = new KhachHangServiceImple();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("create")) {
//            create(req, resp);
        } else if (uri.contains("index")) {
//            index(req, resp);
        }
    }

    protected void index(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ChiTietSP> list = chiTietSPService.findAllByObject();
        req.setAttribute("list", list);
        req.setAttribute("listGioHang", gioHangService.findAllByObject());
        req.setAttribute("listGHCT", listGHCT);
        req.setAttribute("listSanPham", sanPhamService.findAllByObject());
        req.getRequestDispatcher("/views/gio-hang/index.jsp")
                .forward(req, resp);
    }

    protected void create(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int giaGiam = Integer.parseInt(req.getParameter("giaGiam"));
        if (gioHangService.findAllByObject().isEmpty()) {
            req.getRequestDispatcher("/views/gio-hang/createGioHang.jsp")
                    .forward(req, resp);
        }
        ChiTietSP chiTietSP = chiTietSPService
                .findAllByObject()
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElse(null);
        req.setAttribute("chiTietSP", chiTietSP);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("insert")) {
            insert(req, resp);
        }
    }

    protected void insert(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        KhachHang khachHang = khachHangService
//                .findAllByObject()
//                .stream()
//                .filter(t -> t.getId() == 2)
//                .findFirst().orElse(null);
//        int id = 1;
//        String maGioHang = "GH" + id;
//        String tenNguoiNhan = req.getParameter("tenNguoiNhan");
//        String diaChi = req.getParameter("diaChi");
//        String sdt = req.getParameter("sdt");
//        GioHang gioHang = new GioHang(id, khachHang, maGioHang, new Date(), new Date(), tenNguoiNhan, diaChi, sdt, true);
//        gioHangService.save(gioHang);
//        resp.sendRedirect("/StoreManager_war_exploded/");
    }
}
