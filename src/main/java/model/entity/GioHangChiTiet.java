package model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTiet {
    @ManyToOne()
    @JoinColumn(name = "IdGioHang")
    private GioHang idGioHang;

    @ManyToOne()
    @JoinColumn(name = "IdChiTietSP")
    private ChiTietSP idChiTietSP;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "DonGia")
    private int donGia;

    @Column(name = "DonGiaKhiGiam")
    private int donGiaKhiGiam;
}
