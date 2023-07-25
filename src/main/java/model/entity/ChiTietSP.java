package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietSP {
    private int id;
    private SanPham idSP;
    private NSX idNsx;
    private MauSac idMauSac;
    private DongSP idDongSP;
    private Date namBH;
    private String moTa;
    private int soLuongTon;
    private int giaNhap;
    private int giaBan;

}
