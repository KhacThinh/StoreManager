<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: khact
  Date: 06/07/2023
  Time: 10:48 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bảng Nhân Viên</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<div class="container">
    <div class="row py-5">
        <h5>Danh sách Nhân Viên</h5>
        <a href="/StoreManager_war_exploded/nhan-vien/create" class="btn btn-primary"><i
                class="bi bi-person-add"></i>
            Thêm</a>
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <form class="d-flex" role="search" action="/StoreManager_war_exploded/nhan-vien/search" method="get">
                    <input class="form-control me-2" name="ten" type="search" placeholder="Search Name"
                           aria-label="Search" value="${searchName}">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
            <span style="color: red">${thongBao}</span>
        </nav>
    </div>
</div>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>Mã</th>
        <th>Họ và tên</th>
        <th>Giới tính</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Số điện thoại</th>
        <th>Mật khẩu</th>
        <th>ID Cửa hàng</th>
        <th>ID Chức vụ</th>
        <th>ID Gửi BC</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="nhanVien" items="${list}">
        <tr>
            <td>${nhanVien.id}</td>
            <td>${nhanVien.ma}</td>
            <td>${nhanVien.ho} ${nhanVien.tenDem} ${nhanVien.ten}</td>
            <td>${nhanVien.gioiTinh == true ? 'Nam' : 'Nữ'}</td>
            <fmt:formatDate value="${nhanVien.ngaySinh}" var="ngaySinh" pattern="dd/MM/yyyy"></fmt:formatDate>
            <td>${ngaySinh}</td>
            <td> ${nhanVien.diaChi}</td>
            <td>${nhanVien.sdt}</td>
            <td>${nhanVien.matKhau}</td>
            <td>${nhanVien.idCH.ten}</td>
            <td>${nhanVien.idCV.ten}</td>
            <td>${nhanVien.idGuiBC}</td>
            <td><a href="/StoreManager_war_exploded/nhan-vien/edit?id=${nhanVien.id}"
                   class="btn btn-outline-warning"><i class="bi bi-pencil"></i> Edit</a></td>
            <td><a href="/StoreManager_war_exploded/nhan-vien/delete?id=${nhanVien.id}"
                   class="btn btn-outline-danger"><i class="bi bi-trash3-fill"></i> Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<nav aria-label="...">
    <ul class="pagination pagination-lg">
        <c:forEach var="i" begin="1" end="${endPage}">
            <li class="page-item">
                <a class="page-link" href="/StoreManager_war_exploded/nhan-vien/index?paing=${i}">${i}</a></li>
        </c:forEach>
    </ul>
</nav>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
        integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
        crossorigin="anonymous"></script>
</body>
</html>
