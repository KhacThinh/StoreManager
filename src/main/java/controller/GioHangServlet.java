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
import java.util.UUID;

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
        String id = req.getParameter("id");
        int giaGiam = Integer.parseInt(req.getParameter("giaGiam"));
        if (gioHangService.findAllByObject().isEmpty()) {
            req.getRequestDispatcher("/views/gio-hang/createGioHang.jsp")
                    .forward(req, resp);
        }
        ChiTietSP chiTietSP = chiTietSPService
                .findAllByObject()
                .stream()
                .filter(t -> t.getId().equals(UUID.fromString(id)))
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
        KhachHang khachHang = khachHangService
                .findAllByObject()
                .stream()
                .filter(t -> t.getId().equals(UUID.fromString("bfa23683-32e2-d24e-b0c5-ad573fec96f9")))
                .findFirst().orElse(null);
        NhanVien nhanVien = nhanVienService
                .findAllByObject()
                .stream()
                .filter(t -> t.getId().equals(UUID.fromString("211ee3c7-416a-4054-8f35-7005390c7dc")))
                .findFirst().orElse(null);
        String maGioHang = "GH" + String.format("%04d", gioHangService.findAllByObject().size());
        String tenNguoiNhan = req.getParameter("tenNguoiNhan");
        String diaChi = req.getParameter("diaChi");
        String sdt = req.getParameter("sdt");
        GioHang gioHang = new GioHang(null, khachHang, nhanVien, maGioHang, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), tenNguoiNhan, diaChi, sdt, true);
        gioHangService.save(gioHang);
        resp.sendRedirect("/StoreManager_war_exploded/");
    }
}
