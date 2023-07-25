package model.repository;

import model.entity.NhanVien;

import java.util.List;

public interface NhanVienRepository {

    public List<NhanVien> findAllByObject();

    public boolean save(NhanVien e);

    public void update(NhanVien e);

    public boolean delete(Object o);

    public NhanVien findById(Object o);

    public List<NhanVien> findByName(String name);

    public List<NhanVien> findByPaing(int index);
}
