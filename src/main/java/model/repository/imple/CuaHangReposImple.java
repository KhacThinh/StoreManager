package model.repository.imple;


import model.entity.CuaHang;
import model.repository.CuaHangRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CuaHangReposImple implements CuaHangRepository {

    private final static List<CuaHang> list = new ArrayList<>();

    public CuaHangReposImple() {
    }

    static {
        list.add(new CuaHang(1, "CH01", "Thế Giới Di Động", "Ngõ 134 đường Cầu Diễn", "Hà Nội", "Việt Nam", true));
        list.add(new CuaHang(2, "CH02", "Điện Máy Xanh", "Số 10 Trần Duy Hưng", "Hà Nội", "Việt Nam", true));
        list.add(new CuaHang(3, "CH03", "Lotte Mart", "Tầng 3, TTTM Lotte Mart", "Hồ Chí Minh", "Việt Nam", true));
        list.add(new CuaHang(4, "CH04", "FPT Shop", "Tầng 1, Tòa nhà FPT Cầu Giấy", "Hà Nội", "Việt Nam", true));
        list.add(new CuaHang(5, "CH05", "Nguyễn Kim", "Số 8A Trần Hưng Đạo", "Hồ Chí Minh", "Việt Nam", true));
    }


    @Override
    public List<CuaHang> findAllByObject() {
        return list;
    }

    @Override
    public boolean save(CuaHang cuaHang) {
        return list.add(cuaHang);
    }

    @Override
    public void update(CuaHang cuaHang) {
        int id = cuaHang.getId() - 1;
        list.set(id, cuaHang);
    }

    @Override
    public boolean delete(Object o) {
        CuaHang cuaHang = (CuaHang) o;
        for (int i = 0; i <= list.size(); i++) {
            if (cuaHang.getId() == list.get(i).getId()) {
                list.set(i, cuaHang);
                break;
            }
        }
        return true;
    }

    @Override
    public CuaHang findById(Object o) {
        Integer id = Integer.parseInt((String) o);
        return list
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<CuaHang> findByName(String name) {
        List<CuaHang> cuaHangList = this.list
                .stream()
                .filter(t -> t.getTen().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        return cuaHangList;
    }
}
