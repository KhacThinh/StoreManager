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
        List<ChiTietSP> list = chiTietSPService.findAllByObject();
        ChiTietSP chiTietSP = null;
        int id = Integer.parseInt(req.getParameter("id"));
        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getId()) {
                chiTietSP = list.get(i);
                break;
            }
        }
        req.setAttribute("chiTietSP", chiTietSP);
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
        int id = (int) chiTietSPService.findAllByObject().stream().count();
        int soLuongTon = Integer.parseInt(req.getParameter("soLuongTon"));
        int giaNhap = Integer.parseInt(req.getParameter("giaNhap"));
        int giaBan = Integer.parseInt(req.getParameter("giaBan"));
        String moTa = req.getParameter("moTa");
        SanPham sanPham = sanPhamService.findById(req.getParameter("idSP"));
        NSX nsx = nsxService.findById(req.getParameter("idNsx"));
        MauSac mauSac = mauSacSPService.findById(req.getParameter("idMauSac"));
        DongSP dongSP = dongSPService.findById(req.getParameter("idDongSP"));
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date namBH = dateFormat.parse(req.getParameter("namBH"));
            ChiTietSP chiTietSP = new ChiTietSP(++id, sanPham, nsx, mauSac, dongSP, namBH, moTa, soLuongTon, giaNhap, giaBan);
            if (chiTietSP != null) {
                chiTietSPService.save(chiTietSP);
                resp.sendRedirect("/StoreManager_war_exploded/chi-tiet-sp/index");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int soLuongTon = Integer.parseInt(req.getParameter("soLuongTon"));
        int giaNhap = Integer.parseInt(req.getParameter("giaNhap"));
        int giaBan = Integer.parseInt(req.getParameter("giaBan"));
        String moTa = req.getParameter("moTa");
        String date = req.getParameter("namBH");
        SanPham sanPham = sanPhamService.findById(req.getParameter("idSP"));
        NSX nsx = nsxService.findById(req.getParameter("idNsx"));
        MauSac mauSac = mauSacSPService.findById(req.getParameter("idMauSac"));
        DongSP dongSP = dongSPService.findById(req.getParameter("idDongSP"));
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date namBH = dateFormat.parse(date);
            ChiTietSP chiTietSP = new ChiTietSP(id, sanPham, nsx, mauSac, dongSP, namBH, moTa, soLuongTon, giaNhap, giaBan);
            if (chiTietSP != null) {
                chiTietSPService.update(chiTietSP);
                resp.sendRedirect("/StoreManager_war_exploded/chi-tiet-sp/index");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
