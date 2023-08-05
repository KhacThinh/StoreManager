package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.KhachHang;
import service.KhachHangService;
import service.imple.KhachHangServiceImple;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet({
        "/khach-hang/index",
        "/khach-hang/create",
        "/khach-hang/insert",
        "/khach-hang/edit",
        "/khach-hang/update",
        "/khach-hang/delete"
})
public class KhachHangServlet extends HttpServlet {

    private final KhachHangService khachHangService;


    public KhachHangServlet() {
        khachHangService = new KhachHangServiceImple();
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
            int abc = (int) khachHangService.findAllByObject().stream().count();
            int endPage = abc / 3;
            if (abc % 3 != 0) {
                endPage++;
            }
            request.setAttribute("endPage", endPage);
            int index = request.getParameter("paing") == null ? 1 : Integer.parseInt(request.getParameter("paing"));
            request.setAttribute("list", khachHangService.findByPaing(index));
            request.getRequestDispatcher("/views/khach-hang/index.jsp")
                    .forward(request, response);
        } else {
            List<KhachHang> list = khachHangService.findByName(name);
            if (list.isEmpty()) {
                request.setAttribute("searchName", name);
                request.setAttribute("thongBao", "Không tìm thấy khách hàng " + name);
            } else {
                request.setAttribute("list", list);
            }
            request.getRequestDispatcher("/views/khach-hang/index.jsp")
                    .forward(request, response);
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/khach-hang/create.jsp")
                .forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ma = request.getParameter("ma");
        KhachHang khachHang = khachHangService.findByMa(ma);
        if (khachHang != null) {
            request.setAttribute("kh", khachHang);
            request.getRequestDispatcher("/views/khach-hang/edit.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("/StoreManager_war_exploded/khach-hang/index");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ma = request.getParameter("ma");
        KhachHang khachHang = khachHangService.findByMa(ma);
        if (khachHang != null) {
            khachHang.setTrangThai(false);
            khachHangService.delete(khachHang);
            response.sendRedirect("/StoreManager_war_exploded/khach-hang/index");
        }
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
        int id = khachHangService.findAllByObject().size();
        String ma = request.getParameter("ma");
        String ho = request.getParameter("ho");
        String tenDem = request.getParameter("tenDem");
        String ten = request.getParameter("ten");
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        String matKhau = request.getParameter("matKhau");
        StringBuilder stringBuilder = new StringBuilder();
        if (ma.equals("")) {
            stringBuilder.append("trỗng mã ");
        }
        if (ten.equals("")) {
            stringBuilder.append("trỗng tên ");
        }
        boolean checkMa = khachHangService.findAllByObject()
                .stream()
                .anyMatch(khachHang -> khachHang.getMa().equalsIgnoreCase(ma));
        if (checkMa) {
            stringBuilder.append("trùng mã.");
        }
        if (stringBuilder.length() > 0) {
            KhachHang khachHang = new KhachHang(null, ma, ho, tenDem, ten, null, sdt, diaChi, thanhPho, quocGia, matKhau, true);
            request.setAttribute("khachHang", khachHang);
            request.setAttribute("thongBao", "Không được " + stringBuilder.toString());
            request.getRequestDispatcher("/views/khach-hang/create.jsp")
                    .forward(request, response);
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(ngaySinh);
                KhachHang khachHang = new KhachHang(null, ma, ho, tenDem, ten, date, sdt, diaChi, thanhPho, quocGia, matKhau, true);
                if (khachHang != null) {
                    khachHangService.save(khachHang);
                    response.sendRedirect("/StoreManager_war_exploded/khach-hang/index");
                } else {
                    request.setAttribute("loi", "Khong thanh cong");
                }
            } catch (ParseException e) {
                e.printStackTrace();
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
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        String matKhau = request.getParameter("matKhau");
        StringBuilder stringBuilder = new StringBuilder();
        if (ma.equals("")) {
            stringBuilder.append("trỗng mã ");
        }
        if (ten.equals("")) {
            stringBuilder.append("trỗng tên ");
        }
        if (stringBuilder.length() > 0) {
            KhachHang khachHang = new KhachHang(id, ma, ho, tenDem, ten, null, sdt, diaChi, thanhPho, quocGia, matKhau, true);
            request.setAttribute("khachHang", khachHang);
            request.setAttribute("thongBao", "Không được " + stringBuilder.toString());
            request.getRequestDispatcher("/views/khach-hang/create.jsp")
                    .forward(request, response);
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(ngaySinh);
                KhachHang khachHang = new KhachHang(id, ma, ho, tenDem, ten, date, sdt, diaChi, thanhPho, quocGia, matKhau, true);
                if (khachHang != null) {
                    khachHangService.update(khachHang);
                    response.sendRedirect("/StoreManager_war_exploded/khach-hang/index");
                } else {
                    request.setAttribute("loi", "Khong thanh cong");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
