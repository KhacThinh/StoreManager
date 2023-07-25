package model.repository;

import model.entity.ChucVu;

import java.util.List;

public interface ChucVuRepository {

    public List<ChucVu> findAllByObject();

    public boolean save(ChucVu e);

    public void update(ChucVu e);

    public boolean delete(Object o);

    public ChucVu findById(Object o);

    public List<ChucVu> findByName(String name);
}
