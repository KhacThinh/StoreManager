package model.repository;

import model.entity.SanPham;

import java.util.List;

public interface SanPhamRepository {

    public List<SanPham> findAllByObject();

    public boolean save(SanPham e);

    public void update(SanPham e);

    public boolean delete(Object o);

    public SanPham findById(Object o);

    public List<SanPham> findByName(String name);
}
