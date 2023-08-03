package model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "NSX")
public class NSX {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;
    @OneToMany(mappedBy = "idNsx")
    private List<ChiTietSP> chiTietSPS;

    public void addChiTietSanPham(ChiTietSP chiTietSP) {
        chiTietSPS.add(chiTietSP);
    }

    public NSX(UUID id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }

    public NSX() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<ChiTietSP> getChiTietSPS() {
        return chiTietSPS;
    }

    public void setChiTietSPS(List<ChiTietSP> chiTietSPS) {
        this.chiTietSPS = chiTietSPS;
    }

    @Override
    public String toString() {
        return "NSX{" +
                "id=" + id +
                ", ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                '}';
    }
}
