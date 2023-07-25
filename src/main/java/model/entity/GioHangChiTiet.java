package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTiet {
    private GioHang idGioHang;
    private ChiTietSP idChiTietSP;
    private int soLuong;
    private int donGia;
    private int donGiaKhiGiam;
}
