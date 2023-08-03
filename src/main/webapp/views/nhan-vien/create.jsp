<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Nhân Viên</title>
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
    <h1>Thêm Nhân Viên</h1>
    <div class="row py-2">
        <form action="/StoreManager_war_exploded/nhan-vien/insert" method="POST">
            <div class="md-3">
                <label class="form-label">Mã</label>
                <input type="text" name="ma" class="form-control"/>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Họ</label>
                    <input type="text" name="ho" class="form-control" required/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Tên đệm</label>
                    <input type="text" name="tenDem" class="form-control" required/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Tên</label>
                    <input type="text" name="ten" class="form-control" required/>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Ngày sinh</label>
                    <input type="date" name="ngaySinh" class="form-control" required/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Số điện thoại</label>
                    <input type="text" name="sdt" class="form-control" maxlength="10" placeholder="0..."/>
                </div>
            </div>

            <div class="input-group mb-3">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"
                           name="gioiTinh" value="Nam" id="inlineRadio1" checked>
                    <label class="form-check-label text-secondary" for="inlineRadio1">Nam</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"
                           name="gioiTinh" value="Nữ" id="inlineRadio2">
                    <label class="form-check-label text-secondary" for="inlineRadio2">Nữ</label>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Địa chỉ</label>
                    <input type="text" name="diaChi" class="form-control" required/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Mật khẩu</label>
                    <input type="password" name="matKhau" class="form-control" minlength="6" required/>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <select class="form-select" name="idCV" required>
                        <option selected disabled>Chọn Chức Vụ</option>
                        <c:forEach items="${listChucVu}" var="lCV">
                            <option value="${lCV.id}">${lCV.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-1">
                    <a href="/StoreManager_war_exploded/chuc-vu/index" class="btn btn-primary btn-lg"><i
                            class="bi bi-plus-circle"></i></a>
                </div>
                <div class="col-md-4">
                    <select class="form-select" name="idCH" required>
                        <option selected disabled>Chọn Cửa Hàng</option>
                        <c:forEach items="${listCuaHang}" var="lCH">
                            <option value="${lCH.id}">${lCH.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <label class="form-label">GuiBC</label>
                    <select class="form-select" name="idGuiBC">
                        <option selected disabled value="${null}">Chọn Gửi BC</option>
                        <c:forEach items="${listGuiBC}" var="lGuiBC">
                            <option value="${lGuiBC.ma}">${lGuiBC.ten}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row mb-3">
                <span style="color: red">${messageError}</span>
            </div>
            <a href="/StoreManager_war_exploded/nhan-vien/index" class="btn btn-secondary"><i
                    class="bi bi-backspace"></i> Hủy</a>
            <button type="submit" class="btn btn-primary">Create</button>
        </form>
    </div>
</div>
</body>
</html>
