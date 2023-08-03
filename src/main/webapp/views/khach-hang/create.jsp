<%--
  Created by IntelliJ IDEA.
  User: khact
  Date: 06/07/2023
  Time: 2:48 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Khách Hàng</title>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
            integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
            crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<div class="container">
    <h1>Thêm Khách Hàng</h1>
    <div class="row py-2">
        <form action="/StoreManager_war_exploded/khach-hang/insert" method="POST">
            <div class="mb-3">
                <label class="form-label">Mã</label>
                <input type="text" name="ma" class="form-control" value="${khachHang.ma}"/>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Họ</label>
                    <input type="text" name="ho" class="form-control" required value="${khachHang.ho}"/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Tên Đệm</label>
                    <input type="text" name="tenDem" class="form-control" required value="${khachHang.tenDem}"/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Tên</label>
                    <input type="text" name="ten" class="form-control" value="${khachHang.ten}"/>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Ngày Sinh</label>
                    <input type="date" name="ngaySinh" class="form-control" required/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">PhoneNumber</label>
                    <input type="text" name="sdt" class="form-control" min="0" maxlength="10" required
                           value="${khachHang.sdt}"/>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Địa Chỉ</label>
                    <input type="text" name="diaChi" class="form-control" required value="${khachHang.diaChi}"/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Thành Phố</label>
                    <input type="text" name="thanhPho" class="form-control" required value="${khachHang.thanhPho}"/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Quốc Gia</label>
                    <select class="form-select" name="quocGia">
                        <option disabled ${kh.quocGia == null ? 'selected' : ''}>Chọn quốc gia</option>
                        <option value="Brunei" ${kh.quocGia == 'Brunei' ? 'selected' : ''}>Brunei</option>
                        <option value="Campuchia" ${kh.quocGia == 'Campuchia' ? 'selected' : ''}>Campuchia</option>
                        <option value="Indonesia" ${kh.quocGia == 'Indonesia' ? 'selected' : ''}>Indonesia</option>
                        <option value="Lào" ${kh.quocGia == 'Lào' ? 'selected' : ''}>Lào</option>
                        <option value="Malaysia" ${kh.quocGia == 'Malaysia' ? 'selected' : ''}>Malaysia</option>
                        <option value="Myanmar" ${kh.quocGia == 'Myanmar' ? 'selected' : ''}>Myanmar</option>
                        <option value="Philippines" ${kh.quocGia == 'Philippines' ? 'selected' : ''}>Philippines
                        </option>
                        <option value="Singapore" ${kh.quocGia == 'Singapore' ? 'selected' : ''}>Singapore</option>
                        <option value="Thái Lan" ${kh.quocGia == 'Thái Lan' ? 'selected' : ''}>Thái Lan</option>
                        <option value="Việt Nam" ${kh.quocGia == 'Việt Nam' ? 'selected' : ''}>Việt Nam</option>
                    </select>
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật Khẩu</label>
                <input type="password" name="matKhau" class="form-control" minlength="6" required
                       value="${khachHang.matKhau}"/>
            </div>
            <div class="mb-3">
                <span style="color: red">${thongBao}</span>
            </div>
            <a href="/StoreManager_war_exploded/khach-hang/index" class="btn btn-secondary"><i
                    class="bi bi-backspace"></i> Hủy</a>
            <button type="submit" class="btn btn-primary">Create</button>
        </form>
    </div>
</div>
</body>
</html>
