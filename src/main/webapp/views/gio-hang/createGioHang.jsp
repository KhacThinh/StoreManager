<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thông Tin Người Nhận</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
            integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h2>Thông tin Người Nhận</h2>
    <form action="/StoreManager_war_exploded/gio-hang/insert" method="post">
        <div class="form-group">
            <label for="tenNguoiNhan">Tên người nhận</label>
            <input type="text" class="form-control" id="tenNguoiNhan" name="tenNguoiNhan">
        </div>
        <div class="form-group">
            <label for="diaChi">Địa chỉ</label>
            <input type="text" class="form-control" id="diaChi" name="diaChi">
        </div>
        <div class="form-group">
            <label for="sdt">Số điện thoại</label>
            <input type="text" class="form-control" id="sdt" name="sdt">
        </div>
        <button type="submit" class="btn btn-primary">Lưu</button>
    </form>
</div>

</body>
</html>
