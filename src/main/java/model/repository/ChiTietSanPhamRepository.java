package model.repository;

import model.entity.ChiTietSP;

import java.util.List;

public interface ChiTietSanPhamRepository {

    public List<ChiTietSP> findAllByObject();

    public boolean save(ChiTietSP e);

    public void update(ChiTietSP e);

    public boolean delete(Object o);

    public ChiTietSP findById(Object o);

    public List<ChiTietSP> findByName(String name);

    public List<ChiTietSP> findPaing(int index);
}
