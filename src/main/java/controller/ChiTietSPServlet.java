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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet({
        "/chi-tiet-sp/index",
        "/chi-tiet-sp/create",
        "/chi-tiet-sp/insert",
        "/chi-tiet-sp/edit",
        "/chi-tiet-sp/update",
        "/chi-tiet-sp/delete",
        "/chi-tiet-sp/home"
})
public class ChiTietSPServlet extends HttpServlet {
    private final ChiTietSanPhamService chiTietSPService;
    private final MauSacService mauSacSPService;
    private final NhaSanXuatService nsxService;
    private final DongSanPhamService dongSPService;
    private final SanPhamService sanPhamService;

    public ChiTietSPServlet() {
        chiTietSPService = new ChiTietSPServiceImple();
        mauSacSPService = new MauSacServiceImple();
        nsxService = new NSXServiceImple();
        dongSPService = new DongSPServiceImple();
        sanPhamService = new SanPhamServiceImple();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("create")) {
            create(req, resp);
        } else if (uri.contains("edit")) {
            edit(req, resp);
        } else if (uri.contains("delete")) {
            delete(req, resp);
        } else if (uri.contains("index")) {
            index(req, resp);
        } else {
            home(req, resp);
        }
    }

    protected void index(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String searchName = req.getParameter("ten");
        if (searchName == null) {
            int count = (int) chiTietSPService.findAllByObject()
                    .stream()
                    .count();
            int endPage = count / 3;
            if (count % 3 != 0) {
                endPage++;
            }
            req.setAttribute("endPage", endPage);
            int index = req.getParameter("paing") == null ? 1 : Integer.parseInt(req.getParameter("paing"));
            req.setAttribute("list", chiTietSPService.findPaing(index));
        } else {
            req.setAttribute("searchName", searchName);
            List<ChiTietSP> list = chiTietSPService.findByName(searchName);
            if (list.isEmpty()) {
                req.setAttribute("thongBao", "Không tìm thấy " + searchName);
                req.getRequestDispatcher("/views/chi-tiet-sp/index.jsp")
                        .forward(req, resp);
            } else {
                req.setAttribute("list", list);
            }

        }
        req.getRequestDispatcher("/views/chi-tiet-sp/index.jsp")
                .forward(req, resp);
    }

    protected void home(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("tenSanPham");
        if (name != null) {
            req.setAttribute("list", chiTietSPService.findByName(name));
        } else {
            req.setAttribute("list", chiTietSPService.findAllByObject());
        }
        req.getRequestDispatcher("/views/home/index.jsp")
                .forward(req, resp);
    }

    private void constructerAll(HttpServletRequest req) {
        req.setAttribute("listSanPham", sanPhamService.findAllByObject());
        req.setAttribute("listNsx", nsxService.findAllByObject());
        req.setAttribute("listMauSac", mauSacSPService.findAllByObject());
        req.setAttribute("listDongSp", dongSPService.findAllByObject());
    }

    protected void create(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ChiTietSP> list = chiTietSPService.findAllByObject();
        req.setAttribute("list", list);
        this.constructerAll(req);
        req.getRequestDispatcher("/views/chi-tiet-sp/create.jsp")
                .forward(req, resp);

    }

    protected void edit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        ChiTietSP chiTietSP = chiTietSPService.findById(id);
        req.setAttribute("chiTietSP", chiTietSP);
        System.out.println(chiTietSP.toString());
        this.constructerAll(req);
        req.getRequestDispatcher("/views/chi-tiet-sp/edit.jsp")
                .forward(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("insert")) {
            this.insert(req, resp);
        } else if (uri.contains("update")) {
            this.update(req, resp);
        }

    }

    protected void insert(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int soLuongTon = Integer.parseInt(req.getParameter("soLuongTon"));
        int giaNhap = Integer.parseInt(req.getParameter("giaNhap"));
        int giaBan = Integer.parseInt(req.getParameter("giaBan"));
        String moTa = req.getParameter("moTa");
        SanPham sanPham = sanPhamService.findById(req.getParameter("idSP"));
        NSX nsx = nsxService.findById(req.getParameter("idNsx"));
        MauSac mauSac = mauSacSPService.findById(req.getParameter("idMauSac"));
        DongSP dongSP = dongSPService.findById(req.getParameter("idDongSP"));
        int namBH = Integer.parseInt(req.getParameter("namBH"));
        ChiTietSP chiTietSP = new ChiTietSP(null, sanPham, nsx, mauSac, dongSP, namBH, moTa, soLuongTon, giaNhap, giaBan);
        if (chiTietSP != null) {
            chiTietSPService.save(chiTietSP);
            resp.sendRedirect("/StoreManager_war_exploded/chi-tiet-sp/index");
        }
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        int soLuongTon = Integer.parseInt(req.getParameter("soLuongTon"));
        int giaNhap = Integer.parseInt(req.getParameter("giaNhap"));
        int giaBan = Integer.parseInt(req.getParameter("giaBan"));
        String moTa = req.getParameter("moTa");
        int namBH = Integer.parseInt(req.getParameter("namBH"));
        SanPham sanPham = sanPhamService.findById(req.getParameter("idSP"));
        NSX nsx = nsxService.findById(req.getParameter("idNsx"));
        MauSac mauSac = mauSacSPService.findById(req.getParameter("idMauSac"));
        DongSP dongSP = dongSPService.findById(req.getParameter("idDongSP"));
        ChiTietSP chiTietSP = new ChiTietSP(id, sanPham, nsx, mauSac, dongSP, namBH, moTa, soLuongTon, giaNhap, giaBan);
        if (chiTietSP != null) {
            chiTietSPService.update(chiTietSP);
            resp.sendRedirect("/StoreManager_war_exploded/chi-tiet-sp/index");
        } else {
            req.setAttribute("chiTietSP", chiTietSP);
            req.getRequestDispatcher("/views/chi-tiet-sp/edit.jsp")
                    .forward(req, resp);
        }
    }
}
