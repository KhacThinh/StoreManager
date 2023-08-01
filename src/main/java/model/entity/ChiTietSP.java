package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ChiTietSP")
public class ChiTietSP {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "IdSP")
    private SanPham idSP;

    @ManyToOne()
    @JoinColumn(name = "IdNsx")
    private NSX idNsx;

    @ManyToOne()
    @JoinColumn(name = "IdMauSac")
    private MauSac idMauSac;

    @ManyToOne()
    @JoinColumn(name = "IdDongSP")
    private DongSP idDongSP;

    @Column(name = "NamBH")
    private Date namBH;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "SoLuongTon")
    private int soLuongTon;

    @Column(name = "GiaNhap")
    private int giaNhap;

    @Column(name = "GiaBan")
    private int giaBan;

}
