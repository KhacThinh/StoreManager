<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: khact
  Date: 17/07/2023
  Time: 2:36 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sản Phẩm</title>
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
    <h1>Sửa Chi Tiet San Pham</h1>
    <div class="row py-2">
        <form action="/StoreManager_war_exploded/chi-tiet-sp/update?id=${chiTietSP.id}" method="POST">
            <div class="row md-3 mb-3">
                <label class="form-label">Id</label>
                <div class="form-control">${chiTietSP.id}</div>
            </div>
            <div class="row md-3">
                <div class="col-md-5">
                    <select class="form-select" aria-label="Default select example" name="idSP" required>
                        <option selected>Chọn Tên SP</option>
                        <c:forEach items="${listSanPham}" var="sp">
                            <option value="${sp.id}" ${sp.id == chiTietSP.idSP.id ? 'selected' : ''}>${sp.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-1">
                    <a href="/StoreManager_war_exploded/san-pham/index" class="btn btn-primary btn-lg"><i
                            class="bi bi-plus-circle"></i></a>
                </div>
                <div class="col-md-5">
                    <select class="form-select" aria-label="Default select example" name="idNsx" required>
                        <option selected>Chọn Nhà Sản Xuất</option>
                        <c:forEach items="${listNsx}" var="nsx">
                            <option value="${nsx.id}" ${nsx.id == chiTietSP.idNsx.id ? 'selected' : ''}>${nsx.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-1">
                    <a href="/StoreManager_war_exploded/nsx/index" class="btn btn-primary btn-lg"><i
                            class="bi bi-plus-circle"></i></a>
                </div>
            </div>
            <div class="row mb-3 my-3">
                <div class="col-md-5">
                    <select class="form-select" aria-label="Default select example" name="idMauSac" required>
                        <option selected>Chọn Màu Sắc</option>
                        <c:forEach items="${listMauSac}" var="ms">
                            <option value="${ms.id}" ${ms.id == chiTietSP.idMauSac.id ? 'selected' : ''}>${ms.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-1">
                    <a href="/StoreManager_war_exploded/mau-sac/index" class="btn btn-primary btn-lg"><i
                            class="bi bi-plus-circle"></i></a>
                </div>
                <div class="col-md-5">
                    <select class="form-select" aria-label="Default select example" name="idDongSP" required>
                        <option selected>Chọn Dòng Sản Phẩm</option>
                        <c:forEach items="${listDongSp}" var="dongSP">
                            <option value="${dongSP.id}" ${dongSP.id == chiTietSP.idDongSP.id ? 'selected' : ''}>${dongSP.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-1">
                    <a href="/StoreManager_war_exploded/dong-sp/index" class="btn btn-primary btn-lg"><i
                            class="bi bi-plus-circle"></i></a>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Số Lượng Tồn Kho</label>
                    <input type="number" name="soLuongTon" class="form-control" min="0" value="${chiTietSP.soLuongTon}" required/>
                </div>
                <div class="col-md-4">
                    <fmt:formatNumber pattern="###" value="${chiTietSP.giaNhap}" var="giaNhap"></fmt:formatNumber>
                    <label class="form-label">Giá Nhập</label>
                    <input type="number" name="giaNhap" class="form-control" minlength="6" min="0"
                           value="${giaNhap}" required/>
                </div>
                <div class="col-md-4">
                    <fmt:formatNumber pattern="###" value="${chiTietSP.giaBan}" var="giaBan"></fmt:formatNumber>
                    <label class="form-label">Giá Bán</label>
                    <input type="number" name="giaBan" class="form-control" minlength="6" min="0"
                           value="${giaBan}" required/>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Năm Bảo Hành</label>
                    <fmt:formatDate value="${chiTietSP.namBH}" pattern="dd-MM-yyyy" var="namBH"></fmt:formatDate>
                    <input type="text" name="namBH" class="form-control" value="${namBH}" placeholder="dd-MM-yyyy" required/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Mô Tả</label>
                    <textarea class="form-control" name="moTa" id="exampleFormControlTextarea1"
                              rows="3">${chiTietSP.moTa}</textarea>
                </div>
            </div>
            <a href="/StoreManager_war_exploded/chi-tiet-sp/index" class="btn btn-secondary"><i
                    class="bi bi-backspace"></i> Hủy</a>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</div>
</body>
</html>
