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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<h1>Thêm Khách Hàng</h1>
<div class="container">
    <div class="row py-2">
        <form action="/StoreManager_war_exploded/khach-hang/insert" method="POST">
            <div class="mb-3">
                <label class="form-label">Mã</label>
                <input type="text" name="ma" class="form-control" required/>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Họ</label>
                    <input type="text" name="ho" class="form-control" required/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Tên Đệm</label>
                    <input type="text" name="tenDem" class="form-control" required/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Tên</label>
                    <input type="text" name="ten" class="form-control" required/>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Ngày Sinh</label>
                    <input type="date" name="ngaySinh" class="form-control" required/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">PhoneNumber</label>
                    <input type="text" name="sdt" class="form-control" min="0" maxlength="10" required/>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Địa Chỉ</label>
                    <input type="text" name="diaChi" class="form-control" required/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Thành Phố</label>
                    <input type="text" name="thanhPho" class="form-control" required/>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Quốc Gia</label>
                    <select class="form-select" name="quocGia" required>
                        <option selected disabled>Chọn quốc gia</option>
                        <option value="Brunei">Brunei</option>
                        <option value="Campuchia">Campuchia</option>
                        <option value="Indonesia">Indonesia</option>
                        <option value="Lào">Lào</option>
                        <option value="Malaysia">Malaysia</option>
                        <option value="Myanmar">Myanmar</option>
                        <option value="Philippines">Philippines</option>
                        <option value="Singapore">Singapore</option>
                        <option value="Thái Lan">Thái Lan</option>
                        <option value="Việt Nam">Việt Nam</option>
                    </select>
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật Khẩu</label>
                <input type="password" name="matKhau" class="form-control" minlength="6" required/>
            </div>
            <a href="/StoreManager_war_exploded/khach-hang/index" class="btn btn-secondary"><i
                    class="bi bi-backspace"></i> Hủy</a>
            <button type="submit" class="btn btn-primary">Create</button>
        </form>
    </div>
</div>
</body>
</html>
