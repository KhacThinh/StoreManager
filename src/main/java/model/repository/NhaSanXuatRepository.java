package model.repository;

import model.entity.NSX;

import java.util.List;

public interface NhaSanXuatRepository {

    public List<NSX> findAllByObject();

    public boolean save(NSX e);

    public void update(NSX e);

    public boolean delete(Object o);

    public NSX findById(Object o);

    public List<NSX> findByName(String name);
}
