<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản Lý Cửa Hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<div class="container">
    <h4>Email: ${email}</h4>
    <div class="row py-2">
        <div class="row md-3">
            <c:forEach items="${list}" var="chiTietSp">
                <div class="col-md-3">
                    <div class="card">
                        <img src="https://tcorder.vn/wp-content/uploads/2020/09/noi-chien-khong-dau-lock-lock.jpg"
                             class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${chiTietSp.idSP.ten}</h5>
                            <p class="card-title">Số Lượng: ${chiTietSp.soLuongTon}</p>
                            <fmt:formatNumber value="${chiTietSp.giaBan}" pattern="#,###"
                                              var="giaBan"></fmt:formatNumber>
                            <p class="card-title">Giá: ${giaBan}</p>
                            <fmt:formatNumber value="${chiTietSp.giaBan- (chiTietSp.giaBan *0.1)}" pattern="#,###"
                                              var="giaGiam"></fmt:formatNumber>
                            <p class="card-title">Giảm 10% chỉ còn: ${giaGiam}</p>
                            <p class="card-text">Mô Tả: ${chiTietSp.moTa}</p>
                            <fmt:formatNumber var="gia" pattern="###"
                                              value="${chiTietSp.giaBan- (chiTietSp.giaBan *0.1)}"></fmt:formatNumber>
                            <a href="/StoreManager_war_exploded/gio-hang-ct/create?idCTSP=${chiTietSp.id}&giaGiam=${gia}"
                               class="btn btn-primary">Add to cart</a>
                            <a href="#" class="btn btn-primary">Buy Now</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%--<nav aria-label="...">--%>
<%--    <ul class="pagination pagination-lg">--%>
<%--        <c:forEach var="i" begin="1" end="${endPage}">--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="/StoreManager_war_exploded/chi-tiet-sp/index?paing=${i}">${i}</a></li>--%>
<%--        </c:forEach>--%>
<%--    </ul>--%>
<%--</nav>--%>
<jsp:include page="/layout/footer.jsp"></jsp:include>
</body>
</html>
