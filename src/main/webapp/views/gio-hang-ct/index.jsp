<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Giỏ Hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

</head>
<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Mã SP</th>
        <th scope="col">Tên Sản Phẩm</th>
        <th scope="col">Số Lượng</th>
        <th scope="col">Giá</th>
        <th scope="col">Tổng Giá</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listGHCT}" var="ghct">
        <tr>
            <th>${ghct.idGioHang.id}</th>
            <th>${ghct.idChiTietSP.idSP.ma}</th>
            <th>${ghct.idChiTietSP.idSP.ten}</th>
            <td>${ghct.soLuong}</td>
            <td>${ghct.idChiTietSP.giaBan}</td>
            <td>${ghct.donGia}</td>
            <td><a href="#" class="btn btn-outline-warning"/>Mua</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
        integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
        crossorigin="anonymous"></script>
</body>
</html>
