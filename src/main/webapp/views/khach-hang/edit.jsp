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
    <div class="row py-2">
        <h1>Sửa Khách Hàng</h1>
        <form action="/StoreManager_war_exploded/khach-hang/update?id=${kh.id}" method="POST">
            <div class="mb-3">
                <label class="form-label">ID</label>
                <div class="form-control">${kh.id}</div>
            </div>
            <div class="mb-3">
                <label class="form-label">Mã</label>
                <input type="text" name="ma" class="form-control" value="${kh.ma}"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Nguyễn</label>
                <input type="text" name="ho" class="form-control" value="${kh.ho}"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Tên Đệm</label>
                <input type="text" name="tenDem" class="form-control" value="${kh.tenDem}"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Tên</label>
                <input type="text" name="ten" class="form-control" value="${kh.ten}"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Ngày Sinh</label>
                <fmt:formatDate value="${kh.ngaySinh}" pattern="dd-MM-yyyy" var="ngaySinh"></fmt:formatDate>
                <input type="text" name="ngaySinh" class="form-control" value="${ngaySinh}" placeholder="dd-MM-yyyy"/>
            </div>
            <div class="mb-3">
                <label class="form-label">PhoneNumber</label>
                <input type="text" name="sdt" class="form-control" value="${kh.sdt}"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Địa Chỉ</label>
                <input type="text" name="diaChi" class="form-control" value="${kh.diaChi}"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Thành Phố</label>
                <input type="text" name="thanhPho" class="form-control" value="${kh.thanhPho}"/>
            </div>
            <div class="mb-3">
                <select class="form-select" name="quocGia">
                    <option disabled>Chọn quốc gia</option>
                    <option value="Brunei" ${kh.quocGia == 'Brunei' ? 'selected' : ''}>Brunei</option>
                    <option value="Campuchia" ${kh.quocGia == 'Campuchia' ? 'selected' : ''}>Campuchia</option>
                    <option value="Indonesia" ${kh.quocGia == 'Indonesia' ? 'selected' : ''}>Indonesia</option>
                    <option value="Lào" ${kh.quocGia == 'Lào' ? 'selected' : ''}>Lào</option>
                    <option value="Malaysia" ${kh.quocGia == 'Malaysia' ? 'selected' : ''}>Malaysia</option>
                    <option value="Myanmar" ${kh.quocGia == 'Myanmar' ? 'selected' : ''}>Myanmar</option>
                    <option value="Philippines" ${kh.quocGia == 'Philippines' ? 'selected' : ''}>Philippines</option>
                    <option value="Singapore" ${kh.quocGia == 'Singapore' ? 'selected' : ''}>Singapore</option>
                    <option value="Thái Lan" ${kh.quocGia == 'Thái Lan' ? 'selected' : ''}>Thái Lan</option>
                    <option value="Việt Nam" ${kh.quocGia == 'Việt Nam' ? 'selected' : ''}>Việt Nam</option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật Khẩu</label>
                <input type="text" name="matKhau" class="form-control" value="${kh.matKhau}"/>
            </div>

            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</div>
</body>
</html>
