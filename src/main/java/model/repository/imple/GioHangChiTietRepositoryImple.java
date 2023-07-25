package model.repository.imple;

import model.entity.GioHangChiTiet;
import model.repository.GioHangChiTietRepository;

import java.util.ArrayList;
import java.util.List;

public class GioHangChiTietRepositoryImple implements GioHangChiTietRepository {

    private static final List<GioHangChiTiet> list = new ArrayList<>();

    @Override
    public List<GioHangChiTiet> findAllByObject() {
        return list;
    }

    @Override
    public boolean save(GioHangChiTiet e) {
        return list.add(e);
    }


    @Override
    public void update(GioHangChiTiet e) {

    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public GioHangChiTiet findById(Object o) {
        return null;
    }

    @Override
    public List<GioHangChiTiet> findByName(String name) {
        return null;
    }
}
