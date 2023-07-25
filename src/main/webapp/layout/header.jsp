<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Khắc Thịnh</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/StoreManager_war_exploded/chi-tiet-sp/home">Danh
                        sách sản phẩm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/StoreManager_war_exploded/cua-hang/index">Cửa
                        Hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/StoreManager_war_exploded/khach-hang/index">Khách
                        Hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href=/StoreManager_war_exploded/nhan-vien/index>Nhân Viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/StoreManager_war_exploded/chi-tiet-sp/index">Chi
                        Tiết Sản Phẩm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/StoreManager_war_exploded/gio-hang-ct/index">
                        <i class="bi bi-cart-check-fill">Giỏ Hàng</i></a>
                </li>
            </ul>
            <form class="d-flex" role="search" action="/StoreManager_war_exploded/chi-tiet-sp/home">
                <input class="form-control me-2" name="tenSanPham" required type="search" placeholder="Search"
                       aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/StoreManager_war_exploded/login-google&response_type=code
    &client_id=1008526930092-lq6dqlrkp245v4m9fanfmehq4b22tkdn.apps.googleusercontent.com&approval_prompt=force"
               class="btn btn-primary d-flex"><img
                    src="https://cdn1.iconfinder.com/data/icons/google-s-logo/150/Google_Icons-09-512.png" style="width: 25px"> Login Google</a>
        </div>
    </div>
</nav>
