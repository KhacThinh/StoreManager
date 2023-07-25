package model.repository.imple;

import model.entity.GioHang;
import model.repository.GioHangRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GioHangReposImple implements GioHangRepository {

    private static List<GioHang> list = new ArrayList<>();

    @Override
    public List<GioHang> findAllByObject() {
        return list;
    }

    @Override
    public boolean save(GioHang gioHang) {
        return list.add(gioHang);
    }

    @Override
    public void update(GioHang gioHang) {
//        int id = gioHang.getId();
//        for (int i = 0; i <= list.size(); i++) {
//            if (list.get(i).getId() == id) {
//                list.set(i, gioHang);
//                break;
//            }
//        }
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public GioHang findById(Object o) {
        return null;
    }

    @Override
    public List<GioHang> findByName(String name) {
        return null;
    }
}
