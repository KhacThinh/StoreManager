package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.ChiTietSP;
import model.entity.GioHang;
import model.entity.GioHangChiTiet;
import service.*;
import service.imple.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private static List<GioHangChiTiet> listGHCT = new ArrayList<>();

    public GioHangChiTietServlet() {
        chiTietSPService = new ChiTietSPServiceImple();
        mauSacSPService = new MauSacServiceImple();
        nsxService = new NSXServiceImple();
        dongSPService = new DongSPServiceImple();
        sanPhamService = new SanPhamServiceImple();
        gioHangService = new GioHangServiceImple();
        gioHangChiTietService = new GioHangChiTietServiceImple();
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
        List<ChiTietSP> list = chiTietSPService.findAllByObject();
        req.setAttribute("list", list);
        req.setAttribute("listGioHang", gioHangService.findAllByObject());
        req.setAttribute("listGHCT", listGHCT);
        req.setAttribute("listSanPham", sanPhamService.findAllByObject());
        req.getRequestDispatcher("/views/gio-hang-ct/index.jsp")
                .forward(req, resp);
    }

    protected void create(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idCTSP"));
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
        int id = Integer.parseInt(req.getParameter("productId"));
        int giaGiam = Integer.parseInt(req.getParameter("giaGiam"));
        int soLuongMua = Integer.parseInt(req.getParameter("soLuongMua"));
        int giaBan = soLuongMua * Integer.parseInt(req.getParameter("giaBan"));
        List<GioHang> list = gioHangService
                .findAllByObject()
                .stream()
                .sorted((o1, o2) -> (o2.getId() - o1.getId()))
                .collect(Collectors.toList());
        GioHang gioHang = list.get(0);
        ChiTietSP chiTietSP = chiTietSPService
                .findAllByObject()
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElse(null);
        GioHang gioHang1 = gioHangService.findAllByObject()
                .stream()
                .sorted((o1, o2) -> o2.getId() - o1.getId())
                .findFirst()
                .orElse(null);
        GioHang gioHang2 = gioHang1.builder()
                .id(gioHang1.getId() + 1)
                .ma("GH" + gioHang1.getId() + 1)
                .build();
        gioHangService.save(gioHang2);
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet(gioHang, chiTietSP, soLuongMua, giaBan, giaGiam);
        gioHangChiTietService.save(gioHangChiTiet);
        req.setAttribute("listGHCT", gioHangChiTietService.findAllByObject());
        req.getRequestDispatcher("/views/gio-hang-ct/index.jsp")
                .forward(req, resp);
    }
}
