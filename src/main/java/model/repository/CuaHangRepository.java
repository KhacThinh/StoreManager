package model.repository;

import model.entity.CuaHang;
import model.entity.NhanVien;

import java.util.List;

public interface CuaHangRepository {

    public List<CuaHang> findAllByObject();

    public boolean save(CuaHang e);

    public void update(CuaHang e);

    public boolean delete(Object o);

    public CuaHang findById(Object o);

    public List<CuaHang> findByName(String name);

    public List<CuaHang> findByPaing(int index);
}
