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
import java.util.UUID;

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
    private final NhanVienService nhanVienService;
    private final ChucVuService chucVuService;
    private final CuaHangService cuaHangService;


    public NhanVienServlet() {
        nhanVienService = new NhanVienServiceImple();
        chucVuService = new ChucVuServiceImple();
        cuaHangService = new CuaHangServiceImple();
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
        } else {
            this.index(request, response);
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("ten");
        if (name == null) {
            int count = (int) nhanVienService.findAllByObject().size();
            int endPage = count / 3;
            if (count % 3 != 0) {
                endPage++;
            }
            request.setAttribute("endPage", endPage);
            int index = request.getParameter("paing") == null ? 1 : Integer.parseInt(request.getParameter("paing"));
            request.setAttribute("list", nhanVienService.findByPaing(index));
        } else {
            List<NhanVien> list = nhanVienService.findByName(name);
            request.setAttribute("searchName", name);
            if (list.isEmpty()) {
                request.setAttribute("thongBao", "Khong tim thay cua hang " + name);
            } else {
                request.setAttribute("list", list);
            }
        }
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
        String ma = request.getParameter("ma");
        NhanVien nhanVien = nhanVienService.findByMa(ma);
        Optional<NhanVien> optional = Optional.ofNullable(nhanVien);
        List<CuaHang> cuaHangList = cuaHangService.findAllByObject();
        List<ChucVu> chucVuList = chucVuService.findAllByObject();
        request.setAttribute("listCuaHang", cuaHangList);
        request.setAttribute("listChucVu", chucVuList);
        request.setAttribute("listGuiBC", nhanVienService.findAllByObject());
        if (optional.isPresent()) {
            request.setAttribute("nhanVien", optional.get());
            request.getRequestDispatcher("/views/nhan-vien/edit.jsp")
                    .forward(request, response);
        }

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ma = request.getParameter("ma");
        NhanVien nhanVien = nhanVienService.findByMa(ma);
        if (nhanVien != null) {
            nhanVien.setTrangThai(false);
            nhanVienService.delete(nhanVien);
        }
        response.sendRedirect("/StoreManager_war_exploded/nhan-vien/index");
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
        String ma = request.getParameter("ma");
        String ho = request.getParameter("ho");
        String tenDem = request.getParameter("tenDem");
        String ten = request.getParameter("ten");
        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String matKhau = request.getParameter("matKhau");
        boolean gt = true;
        if (gioiTinh.equals("Nữ")) {
            gt = false;
        }
        CuaHang cuaHang = cuaHangService.findById(request.getParameter("idCH"));
        ChucVu chucVu = chucVuService.findById(request.getParameter("idCV"));
        Optional<NhanVien> idGuiBC = Optional.ofNullable(nhanVienService.findByMa(request.getParameter("idGuiBC")));
        StringBuilder stringBuilder = new StringBuilder();
        if (ma.equals("")) {
            stringBuilder.append("trỗng mã ");
        }
        boolean checkMa = nhanVienService.findAllByObject()
                .stream()
                .anyMatch(nhanVien -> nhanVien.getMa().equalsIgnoreCase(ma));
        if (checkMa) {
            stringBuilder.append("trùng mã ");
        }
        NhanVien nhanVien = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(ngaySinh);
            nhanVien = new NhanVien(null, ma, ho, tenDem, ten, gt, date, diaChi, sdt, matKhau, cuaHang, chucVu, idGuiBC.orElse(null), true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (stringBuilder.length() > 0) {
            request.setAttribute("messageError", "Không được để " + stringBuilder.toString());
            request.setAttribute("list", nhanVien);
            request.getRequestDispatcher("/views/nhan-vien/create.jsp")
                    .forward(request, response);
        } else {
            if (nhanVien != null) {
                nhanVienService.save(nhanVien);
                response.sendRedirect("/StoreManager_war_exploded/nhan-vien/index");
            } else {
                request.setAttribute("loi", "Khong thanh cong");
            }

        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ho = request.getParameter("ho");
        String tenDem = request.getParameter("tenDem");
        String ten = request.getParameter("ten");
        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String matKhau = request.getParameter("matKhau");
        boolean gt = true;
        if (gioiTinh.equals("Nữ")) {
            gt = false;
        }
        Optional<NhanVien> idGuiBC = Optional.ofNullable(nhanVienService.findByMa(request.getParameter("idGuiBC")));
        CuaHang cuaHang = cuaHangService.findById(request.getParameter("idCH"));
        ChucVu chucVu = chucVuService.findById(request.getParameter("idCV"));
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = dateFormat.parse(ngaySinh);
            NhanVien nhanVien = new NhanVien(id, ma, ho, tenDem, ten, gt, date, diaChi, sdt, matKhau, cuaHang, chucVu, idGuiBC.orElse(null), true);
            if (nhanVien != null) {
                nhanVienService.update(nhanVien);
                response.sendRedirect("/StoreManager_war_exploded/nhan-vien/index");
            } else {
                request.setAttribute("loi", "Khong thanh cong");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
