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
import java.util.*;
import java.util.stream.Collectors;

@WebServlet({
        "/gio-hang-ct/index",
        "/gio-hang-ct/create",
        "/gio-hang-ct/insert"
})
public class GioHangChiTietServlet extends HttpServlet {

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

    public GioHangChiTietServlet() {
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
            create(req, resp);
        } else if (uri.contains("index")) {
            index(req, resp);
        }
    }

    protected void index(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        List<ChiTietSP> list = chiTietSPService.findAllByObject();
//        req.setAttribute("list", list);
//        req.setAttribute("listGioHang", gioHangService.findAllByObject());
//        req.setAttribute("listGHCT", listGHCT);
//        req.setAttribute("listSanPham", sanPhamService.findAllByObject());
//        req.getRequestDispatcher("/views/gio-hang-ct/index.jsp")
//                .forward(req, resp);

        req.setAttribute("listGHCT", gioHangChiTietService.findAllByObject());
        req.getRequestDispatcher("/views/gio-hang-ct/index.jsp")
                .forward(req, resp);
    }

    protected void create(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("idCTSP"));
        int giaGiam = Integer.parseInt(req.getParameter("giaGiam"));
        if (gioHangService.findAllByObject().isEmpty()) {
            req.getRequestDispatcher("/views/gio-hang/createGioHang.jsp")
                    .forward(req, resp);
        }
        ChiTietSP chiTietSP = chiTietSPService
                .findAllByObject()
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst().orElse(null);
        req.setAttribute("chiTietSP", chiTietSP);
        req.getRequestDispatcher("/views/gio-hang-ct/create.jsp")
                .forward(req, resp);
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
        UUID id = UUID.fromString(req.getParameter("productId"));
        int giaGiam = Integer.parseInt(req.getParameter("giaGiam"));
        int soLuongMua = Integer.parseInt(req.getParameter("soLuongMua"));
        int giaBan = soLuongMua * Integer.parseInt(req.getParameter("giaBan"));
        ChiTietSP chiTietSP = chiTietSPService
                .findAllByObject()
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst().orElse(null);
        KhachHang khachHang = khachHangService
                .findAllByObject()
                .stream()
                .filter(t -> t.getId().equals(UUID.fromString("bfa23683-32e2-d24e-b0c5-ad573fec96f9")))
                .findFirst().orElse(null);
        NhanVien nhanVien = nhanVienService.findByMa("NV0003");
        List<GioHang> list = gioHangService.findAllByObject();

        int idGH = gioHangService.findAllByObject().size();
        if (soLuongMua > 0 && soLuongMua <= chiTietSP.getSoLuongTon()) {
            GioHang gioHang = new GioHang(null, khachHang, nhanVien, "GH" + String.format("%04d", ++idGH), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "Khắc Thịnh", "123 Ngõ Cầu Diễn", "0934339795", true);
            gioHangService.save(gioHang);
            List<GioHangChiTiet> gioHangChiTiet = gioHangChiTietService.findAllByObject();
            for (GioHangChiTiet gioHangChiTiet1 : gioHangChiTiet) {
                if (!chiTietSP.getId().equals(gioHangChiTiet1.getIdChiTietSP())) {
                    GioHangChiTiet gioHangChiTiet2 = new GioHangChiTiet(gioHang, chiTietSP, soLuongMua, giaBan, giaGiam);
                    gioHangChiTietService.save(gioHangChiTiet2);
                } else {
                    GioHangChiTiet gioHangChiTiet2 = new GioHangChiTiet(gioHang, chiTietSP, soLuongMua, giaBan, giaGiam);
                    gioHangChiTietService.update(gioHangChiTiet2);
                }
            }
            req.setAttribute("listGHCT", gioHangChiTietService.findAllByObject());
            req.getRequestDispatcher("/views/gio-hang-ct/index.jsp")
                    .forward(req, resp);
        } else {
            req.setAttribute("chiTietSP", chiTietSP);
            req.getRequestDispatcher("/views/gio-hang-ct/create.jsp")
                    .forward(req, resp);
        }

    }
}
