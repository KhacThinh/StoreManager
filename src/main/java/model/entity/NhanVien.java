package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhanVien {
    private int id;
    private String ma;
    private String ho;
    private String tenDem;
    private String ten;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String sdt;
    private String matKhau;
    private CuaHang idCH;
    private ChucVu idCV;
    private int idGuiBC;
    private boolean trangThai;

}
