package service;

import model.entity.DongSP;

import java.util.List;

public interface DongSanPhamService {

    public List<DongSP> findAllByObject();

    public boolean save(DongSP e);

    public void update(DongSP e);

    public boolean delete(Object o);

    public DongSP findById(Object o);

    public List<DongSP> findByName(String name);
}
