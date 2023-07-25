package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang {
    private int id;
    private String ma;
    private String ho;
    private String tenDem;
    private String ten;
    private Date ngaySinh;
    private String sdt;
    private String diaChi;
    private String thanhPho;
    private String quocGia;
    private String matKhau;
    private boolean trangThai;

}
