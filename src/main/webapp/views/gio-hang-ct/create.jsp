<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Giỏ Hàng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container center-content">
    <div class="card">
        <img src="https://tcorder.vn/wp-content/uploads/2020/09/noi-chien-khong-dau-lock-lock.jpg"
             class="card-img-top" alt="Product Image" style="height: 300px; width: 280px">
        <div class="card-body">
            <form action="/StoreManager_war_exploded/gio-hang-ct/insert"
                  method="POST">
                <h5 class="card-title">${chiTietSP.idSP.ten}</h5>
                <p class="card-title">Số Lượng: <strong>${chiTietSP.soLuongTon}</strong></p>
                <p class="card-text">Mô Tả: <strong>${chiTietSP.moTa}</strong></p>
                <p class="card-text">Nhà Sản Xuất: <strong>${chiTietSP.idNsx.ten}</strong></p>
                <p class="card-text">Màu: <strong>${chiTietSP.idMauSac.ten}</strong></p>
                <p class="card-text">Dòng Sản Phẩm: <strong>${chiTietSP.idDongSP.ten}</strong></p>
                <fmt:formatNumber var="giaBan" pattern="#,###" value="${chiTietSP.giaBan}"></fmt:formatNumber>
                <p class="card-text">Giá:
                    <del>${giaBan} Đồng</del>
                </p>
                <fmt:formatNumber var="giaGiam" pattern="#,###"
                                  value="${chiTietSP.giaBan - (chiTietSP.giaBan*0.01)}"></fmt:formatNumber>
                <p class="card-text">Giảm 10% chỉ còn: <strong>${giaGiam} Đồng</strong></p>
                <p class="card-text">Số lượng: <input type="number" id="quantity" name="soLuongMua" value="1" min="0"
                                                      max="${chiTietSP.soLuongTon}"></p>
                <input type="hidden" name="productId" value="${chiTietSP.id}">
                <input type="hidden" name="giaBan" value="${chiTietSP.giaBan}">
                <fmt:formatNumber var="giaGiam1" pattern="###"
                                  value="${chiTietSP.giaBan - (chiTietSP.giaBan*0.01)}"></fmt:formatNumber>
                <input type="hidden" name="giaGiam" value="${giaGiam1}">
                <input type="submit" value="Thêm vào giỏ hàng" class="btn btn-success">
            </form>
        </div>
    </div>
</div>

</body>
</html>
