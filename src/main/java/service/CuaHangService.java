package service;

import model.entity.CuaHang;

import java.util.List;

public interface CuaHangService {

    public List<CuaHang> findAllByObject();

    public boolean save(CuaHang e);

    public void update(CuaHang e);

    public boolean delete(Object o);

    public CuaHang findById(Object o);

    public List<CuaHang> findByName(String name);

    public List<CuaHang> findByPaing(int index);
}
