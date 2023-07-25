package model.repository.imple;

import model.entity.NSX;
import model.repository.NhaSanXuatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NhaSanXuatReposImple implements NhaSanXuatRepository {
    private final static List<NSX> list = new ArrayList<>();

    static {
        list.add(new NSX(1, "NSX001", "Công ty Đồ gia dụng Hà Nội"));
        list.add(new NSX(2, "NSX002", "Công ty Đồ gia dụng Hồ Chí Minh"));
        list.add(new NSX(3, "NSX003", "Công ty Đồ gia dụng Đà Nẵng"));
        list.add(new NSX(4, "NSX004", "Công ty Đồ gia dụng Huế"));
        list.add(new NSX(5, "NSX005", "Công ty Đồ gia dụng Hải Phòng"));
        list.add(new NSX(6, "NSX006", "Công ty Đồ gia dụng Nha Trang"));
        list.add(new NSX(7, "NSX007", "Công ty Đồ gia dụng Cần Thơ"));
        list.add(new NSX(8, "NSX008", "Công ty Đồ gia dụng Đà Lạt"));
        list.add(new NSX(9, "NSX009", "Công ty Đồ gia dụng Vũng Tàu"));
        list.add(new NSX(10, "NSX010", "Công ty Đồ gia dụng Quy Nhơn"));
    }


    @Override
    public List<NSX> findAllByObject() {
        return list;
//        .stream()
//                .sorted((o1, o2) -> o2.getId() - o1.getId())
//                .collect(Collectors.toList());
    }

    @Override
    public boolean save(NSX nsx) {
        return list.add(nsx);
    }

    @Override
    public void update(NSX nsx) {
        int id = nsx.getId() - 1;
        list.set(id, nsx);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public NSX findById(Object o) {
        Integer id = Integer.parseInt((String) o);
        return list
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<NSX> findByName(String name) {
        return null;
    }
}
