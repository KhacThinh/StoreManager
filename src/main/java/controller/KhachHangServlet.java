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

@WebServlet({
        "/khach-hang/index",
        "/khach-hang/create",
        "/khach-hang/insert",
        "/khach-hang/edit",
        "/khach-hang/update",
        "/khach-hang/delete",
        "/khach-hang/search"
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
//        if (uri.contains("create")) {
//            this.create(request, response);
//        } else if (uri.contains("edit")) {
//            this.edit(request, response);
//        } else if (uri.contains("delete")) {
//            this.delete(request, response);
//        } else if (uri.contains("search")) {
//            this.search(request, response);
//        } else {
//            this.index(request, response);
//        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int abc = (int) khachHangService
                .findAllByObject()
                .stream()
                .filter(t -> t.isTrangThai() == true).count();
        int endPage = abc / 3;
        if (abc % 3 != 0) {
            endPage++;
        }
        request.setAttribute("endPage", endPage);
        int index = request.getParameter("paing") == null ? 1 : Integer.parseInt(request.getParameter("paing"));
        request.setAttribute("list", khachHangService.findByPaing(index));
        request.getRequestDispatcher("/views/khach-hang/index.jsp")
                .forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/khach-hang/create.jsp")
                .forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        KhachHang khachHang = khachHangService
//                .findAllByObject()
//                .stream()
//                .filter(khachHang1 -> khachHang1.getId() == id)
//                .findFirst().orElse(null);
//        if (khachHang != null) {
//            request.setAttribute("kh", khachHang);
//            request.getRequestDispatcher("/views/khach-hang/edit.jsp")
//                    .forward(request, response);
//        } else {
//            System.out.println("rong");
//        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        KhachHang khachHang = khachHangService
//                .findAllByObject()
//                .stream()
//                .filter(t -> t.getId() == id)
//                .findFirst().orElse(null);
//        khachHang.setTrangThai(false);
//        khachHangService.delete(khachHang);
//        request.setAttribute("list", khachHangService.findAllByObject());
//        request.getRequestDispatcher("/views/khach-hang/index.jsp")
//                .forward(request, response);
    }

    protected void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("ten");
        List<KhachHang> list = khachHangService.findByName(name);
        if (list.isEmpty()) {
            req.setAttribute("searchName", name);
            req.setAttribute("thongBao", "Khong tim thay cua hang " + name);
            req.setAttribute("list", khachHangService.findAllByObject());
        } else {
            req.setAttribute("list", list);
        }
        req.getRequestDispatcher("/views/nhan-vien/index.jsp")
                .forward(req, resp);
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
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = dateFormat.parse(ngaySinh);
//            KhachHang khachHang = new KhachHang(++id, ma, ho, tenDem, ten, date, sdt, diaChi, thanhPho, quocGia, matKhau, true);
//            if (khachHang != null) {
//                khachHangService.save(khachHang);
//                request.setAttribute("list", khachHangService.findAllByObject());
//                request.getRequestDispatcher("/views/khach-hang/index.jsp")
//                        .forward(request, response);
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
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        String matKhau = request.getParameter("matKhau");
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            Date date = dateFormat.parse(ngaySinh);
//            KhachHang khachHang = new KhachHang(id, ma, ho, tenDem, ten, date, sdt, diaChi, thanhPho, quocGia, matKhau, true);
//            if (khachHang != null) {
//                khachHangService.update(khachHang);
//                request.setAttribute("list", khachHangService.findAllByObject());
//                request.getRequestDispatcher("/views/khach-hang/index.jsp")
//                        .forward(request, response);
//            } else {
//                request.setAttribute("loi", "Khong thanh cong");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}
