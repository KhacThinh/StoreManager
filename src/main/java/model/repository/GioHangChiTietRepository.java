package model.repository;

import model.entity.GioHangChiTiet;

import java.util.List;

public interface GioHangChiTietRepository {
    public List<GioHangChiTiet> findAllByObject();

    public boolean save(GioHangChiTiet e);

    public void update(GioHangChiTiet e);

    public boolean delete(Object o);

    public GioHangChiTiet findById(Object o);

    public List<GioHangChiTiet> findByName(String name);
}
