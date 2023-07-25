package service;

import model.entity.KhachHang;

import java.util.List;

public interface KhachHangService {

    public List<KhachHang> findAllByObject();

    public boolean save(KhachHang e);

    public void update(KhachHang e);

    public boolean delete(Object o);

    public KhachHang findById(Object o);

    public List<KhachHang> findByName(String name);

    public List<KhachHang> findByPaing(int index);
}
