package model.repository;

import model.entity.GioHang;

import java.util.List;

public interface GioHangRepository {

    public List<GioHang> findAllByObject();

    public boolean save(GioHang e);

    public void update(GioHang e);

    public boolean delete(Object o);

    public GioHang findById(Object o);

    public List<GioHang> findByName(String name);
}
