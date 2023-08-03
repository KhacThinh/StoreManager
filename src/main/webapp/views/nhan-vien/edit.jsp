<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Khách Hàng</title>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
            integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
            crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Sửa Nhân Viên</h1>
    <div class="row py-2">
        <form action="/StoreManager_war_exploded/nhan-vien/update?id=${nhanVien.id}" method="POST">
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Id</label>
                    <div class="form-control">${nhanVien.id}</div>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Id</label>
                    <div class="form-control">${nhanVien.ma}</div>
                    <input type="hidden" name="ma" value="${nhanVien.ma}">
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Họ</label>
                    <input type="text" name="ho" class="form-control" value="${nhanVien.ho}"/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Tên đệm</label>
                    <input type="text" name="tenDem" class="form-control" value="${nhanVien.tenDem}"/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Tên</label>
                    <input type="text" name="ten" class="form-control" value="${nhanVien.ten}"/>
                </div>
            </div>
            <div class="input-group mb-3">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"
                           name="gioiTinh" value="Nam" id="inlineRadio1" ${nhanVien.gioiTinh == true ? 'checked' : ''}>
                    <label class="form-check-label text-secondary" for="inlineRadio1">Nam</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"
                           name="gioiTinh" value="Nữ" id="inlineRadio2" ${nhanVien.gioiTinh == false ? 'checked' : ''}>
                    <label class="form-check-label text-secondary" for="inlineRadio2">Nữ</label>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <fmt:formatDate value="${nhanVien.ngaySinh}" pattern="dd-MM-yyyy" var="birthday"></fmt:formatDate>
                    <label class="form-label">Ngày sinh</label>
                    <input type="text" name="ngaySinh" class="form-control" value="${birthday}"
                           placeholder="dd-MM-yyyy"/>

                </div>
                <div class="col-md-6">
                    <label class="form-label">Số điện thoại</label>
                    <input type="text" name="sdt" class="form-control" maxlength="10" placeholder="0..."
                           value="${nhanVien.sdt}"/>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Địa chỉ</label>
                    <input type="text" name="diaChi" class="form-control" value="${nhanVien.diaChi}"/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Mật khẩu</label>
                    <input type="text" name="matKhau" class="form-control" minlength="6"
                           value="${nhanVien.matKhau}"/>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Chức Vụ</label>
                    <select class="form-select" name="idCV">
                        <option disabled>Chọn Chức Vụ</option>
                        <c:forEach items="${listChucVu}" var="lCV">
                            <option value="${lCV.id}" ${lCV.id == nhanVien.idCV.id ? 'selected' : ''}>${lCV.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Cửa Hàng</label>
                    <select class="form-select" name="idCH">
                        <option disabled>Chọn Cửa Hàng</option>
                        <c:forEach items="${listCuaHang}" var="lCH">
                            <option value="${lCH.id}" ${lCH.id == nhanVien.idCH.id ? 'selected' : ''} >${lCH.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="form-label">GuiBC</label>
                    <select class="form-select" name="idGuiBC">
                        <option disabled ${nhanVien.idGuiBC == null ? 'selected' : ''}>Chọn Gửi BC</option>
                        <c:forEach items="${listGuiBC}" var="lGuiBC">
                            <option value="${lGuiBC.ma}" ${lGuiBC.id == nhanVien.idGuiBC ? 'selected' : ''}>${lGuiBC.ten}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Sửa</button>
        </form>
    </div>
</div>
</body>
</html>
