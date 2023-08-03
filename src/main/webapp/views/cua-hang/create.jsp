<%--
  Created by IntelliJ IDEA.
  User: khact
  Date: 01/07/2023
  Time: 3:20 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
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
    <div class="row py-2">
        <form action="/StoreManager_war_exploded/cua-hang/store" method="POST">
            <div class="mb-3">
                <label class="form-label">Mã</label>
                <input type="text" name="ma" class="form-control" value="${cuaHang.ma}"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Tên Cửa Hàng</label>
                <input type="text" name="ten" class="form-control" value="${cuaHang.ten}"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Địa Chỉ</label>
                <input type="text" name="diaChi" class="form-control" value="${cuaHang.diaChi}" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Thành Phố</label>
                <input type="text" name="thanhPho" class="form-control" value="${cuaHang.thanhPho}" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Quốc Gia</label>
                <input type="text" name="quocGia" class="form-control" value="${cuaHang.quocGia}" required/>
            </div>
            <div class="mb-3">
                <span style="color: red">${messageError}</span>
            </div>
            <a href="/StoreManager_war_exploded/cua-hang/index" class="btn btn-secondary"><i
                    class="bi bi-backspace"></i> Hủy</a>
            <button type="submit" class="btn btn-primary">Create</button>
        </form>
    </div>
</div>
</body>
</html>
