<%--
  Created by IntelliJ IDEA.
  User: khact
  Date: 05/07/2023
  Time: 11:18 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm Chức Vụ</title>
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
        <form action="/StoreManager_war_exploded/chuc-vu/update?id=${chucVu.id}" method="POST">
            <div class="mb-3">
                <label class="form-label">Id</label>
                <div class="form-control">${chucVu.id}</div>
            </div>
            <div class="mb-3">
                <label class="form-label">Mã</label>
                <input type="text" name="ma" class="form-control" value="${chucVu.ma}" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Chức Vụ</label>
                <input type="text" name="ten" class="form-control" value="${chucVu.ten}" required/>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</div>
</body>
</html>
