package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.ChucVu;
import model.entity.CuaHang;
import model.entity.NhanVien;
import service.ChucVuService;
import service.CuaHangService;
import service.NhanVienService;
import service.imple.ChucVuServiceImple;
import service.imple.CuaHangServiceImple;
import service.imple.NhanVienServiceImple;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@WebServlet({
        "/nhan-vien/index",
        "/nhan-vien/create",
        "/nhan-vien/insert",
        "/nhan-vien/edit",
        "/nhan-vien/update",
        "/nhan-vien/delete",
        "/nhan-vien/search"
})
public class NhanVienServlet extends HttpServlet {
    private final ChucVuService chucVuService;
    private final CuaHangService cuaHangService;
    private final NhanVienService nhanVienService;


    public NhanVienServlet() {
        nhanVienService = new NhanVienServiceImple();
        cuaHangService = new CuaHangServiceImple();
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
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else if (uri.contains("search")) {
            this.search(request, response);
        } else {
            this.index(request, response);
        }
    }

    protected void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("ten");
        List<NhanVien> list = nhanVienService.findByName(name);
        if (list.isEmpty()) {
            req.setAttribute("searchName", name);
            req.setAttribute("thongBao", "Khong tim thay cua hang " + name);
            req.setAttribute("list", nhanVienService.findAllByObject());
        } else {
            req.setAttribute("list", list);
        }
        req.getRequestDispatcher("/views/nhan-vien/index.jsp")
                .forward(req, resp);
    }

    protected void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int count = (int) nhanVienService.findAllByObject()
                .stream()
                .filter(t -> t.isTrangThai() == true).count();
        int endPage = count / 3;
        if (count % 3 != 0) {
            endPage++;
        }
        request.setAttribute("endPage", endPage);
        int index = request.getParameter("paing") == null ? 1 : Integer.parseInt(request.getParameter("paing"));
        request.setAttribute("list", nhanVienService.findByPaing(index));
        request.getRequestDispatcher("/views/nhan-vien/index.jsp")
                .forward(request, response);

    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CuaHang> cuaHangList = cuaHangService.findAllByObject();
        List<ChucVu> chucVuList = chucVuService.findAllByObject();
        request.setAttribute("listCuaHang", cuaHangList);
        request.setAttribute("listChucVu", chucVuList);
        request.setAttribute("listGuiBC", nhanVienService.findAllByObject());
        request.getRequestDispatcher("/views/nhan-vien/create.jsp")
                .forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        NhanVien nhanVien = nhanVienService
//                .findAllByObject()
//                .stream()
//                .filter(t -> t.getId() == id)
//                .findFirst().orElse(null);
//        Optional<NhanVien> optional = Optional.ofNullable(nhanVien);
//        List<CuaHang> cuaHangList = cuaHangService.findAllByObject();
//        List<ChucVu> chucVuList = chucVuService.findAllByObject();
//        request.setAttribute("listCuaHang", cuaHangList);
//        request.setAttribute("listChucVu", chucVuList);
//        request.setAttribute("listGuiBC", nhanVienService.findAllByObject());
//        if (optional.isPresent()) {
//            request.setAttribute("nhanVien", optional.get());
//            request.getRequestDispatcher("/views/nhan-vien/edit.jsp")
//                    .forward(request, response);
//        }

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        NhanVien nv = nhanVienService
//                .findAllByObject()
//                .stream()
//                .filter(t -> t.getId() == id)
//                .findFirst().orElse(null);
//        nv.setTrangThai(false);
//        nhanVienService.delete(nv);
//        request.setAttribute("list", nhanVienService.findAllByObject());
//        request.getRequestDispatcher("/views/nhan-vien/index.jsp")
//                .forward(request, response);

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
        int id = (int) nhanVienService.findAllByObject().stream().count();
        String ma = request.getParameter("ma");
        String ho = request.getParameter("ho");
        String tenDem = request.getParameter("tenDem");
        String ten = request.getParameter("ten");
        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String matKhau = request.getParameter("matKhau");
        int idGuiBC = Integer.parseInt(request.getParameter("idGuiBC"));
        boolean gt = true;
        if (gioiTinh.equals("Nữ")) {
            gt = false;
        }
        CuaHang cuaHang = cuaHangService.findById(request.getParameter("idCH"));
        ChucVu chucVu = chucVuService.findById(request.getParameter("idCV"));

//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = dateFormat.parse(ngaySinh);
//            NhanVien nhanVien = new NhanVien(++id, ma, ho, tenDem, ten, gt, date, diaChi, sdt, matKhau, cuaHang, chucVu, idGuiBC, true);
//            if (nhanVien != null) {
//                nhanVienService.save(nhanVien);
//                response.sendRedirect("/StoreManager_war_exploded/nhan-vien/index");
//            } else {
//                request.setAttribute("loi", "Khong thanh cong");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ho = request.getParameter("ho");
        String tenDem = request.getParameter("tenDem");
        String ten = request.getParameter("ten");
        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String matKhau = request.getParameter("matKhau");
        int idGuiBC = Integer.parseInt(request.getParameter("idGuiBC"));
        boolean gt = true;
        if (gioiTinh.equals("Nữ")) {
            gt = false;
        }
        CuaHang cuaHang = cuaHangService.findById(request.getParameter("idCH"));
        ChucVu chucVu = chucVuService.findById(request.getParameter("idCV"));
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            Date date = dateFormat.parse(ngaySinh);
//            NhanVien nhanVien = new NhanVien(id, ma, ho, tenDem, ten, gt, date, diaChi, sdt, matKhau, cuaHang, chucVu, idGuiBC, true);
//            if (nhanVien != null) {
//                nhanVienService.update(nhanVien);
//                response.sendRedirect("/StoreManager_war_exploded/nhan-vien/index");
//            } else {
//                request.setAttribute("loi", "Khong thanh cong");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}
