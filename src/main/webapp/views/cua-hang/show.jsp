<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cua Hang</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<div class="container">
    <div class="row py-5">
        <h4>Danh sách cửa hàng</h4>
        <a href="/StoreManager_war_exploded/cua-hang/create" class="btn btn-primary"><i class="bi bi-person-add"></i>
            Thêm</a>
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <form class="d-flex" role="search" action="/StoreManager_war_exploded/cua-hang/index" method="get">
                    <input class="form-control me-2" name="ten" type="search" placeholder="Search Name"
                           aria-label="Search" value="${searchName}">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
            <span style="color: red">${thongBao}</span>
        </nav>
    </div>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Mã cửa hàng</th>
            <th scope="col">Tên cửa hàng</th>
            <th scope="col">Địa chỉ</th>
            <th scope="col">Thành phố</th>
            <th scope="col">Quốc gia</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cuaHang" items="${list}">
            <tr>
                <th scope="row">${cuaHang.id}</th>
                <td>${cuaHang.ma}</td>
                <td>${cuaHang.ten}</td>
                <td>${cuaHang.diaChi}</td>
                <td>${cuaHang.thanhPho}</td>
                <td>${cuaHang.quocGia}</td>
                <td><a href="/StoreManager_war_exploded/cua-hang/edit?id=${cuaHang.id}"
                       class="btn btn-outline-warning"><i class="bi bi-pencil"></i> Edit</a></td>
                <td><a href="/StoreManager_war_exploded/cua-hang/delete?id=${cuaHang.id}"
                       class="btn btn-outline-danger"><i class="bi bi-trash3-fill"></i> Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="http://localhost:8080/QuanLiCuaHang_war_exploded/" class="btn btn-primary"><i
            class="bi bi-house-down-fill"></i> Home</a>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
        integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
        crossorigin="anonymous"></script>
</body>
</html>
