package model.repository.imple;

import model.entity.KhachHang;
import model.repository.KhachHangRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class KhachHangReposImple implements KhachHangRepository {
    private final static List<KhachHang> list = new ArrayList<>();

    static {
        list.add(new KhachHang(1, "KH001", "Nguyen", "Van", "Hoang", new Date(), "0123456789", "123 ABC Street, Ho Chi Minh City", "Ho Chi Minh City", "Việt Nam", "123456", true));
        list.add(new KhachHang(2, "KH002", "Tran", "Thi", "Phuong", new Date(), "0987654321", "456 XYZ Street, Hanoi", "Hanoi", "Việt Nam", "654321", true));
        list.add(new KhachHang(3, "KH003", "Le", "Hoang", "Tuan", new Date(), "0456123789", "789 QWE Street, Da Nang", "Da Nang", "Philippines", "987654", true));
        list.add(new KhachHang(4, "KH004", "Pham", "Van", "Khanh", new Date(), "0789321654", "321 ASD Street, Can Tho", "Can Tho", "Việt Nam", "789123", true));
        list.add(new KhachHang(5, "KH005", "Hoang", "Thi", "Ngoc", new Date(), "0654789321", "654 ZXC Street, Vung Tau", "Vung Tau", "Campuchia", "456789", true));
    }


    @Override
    public List<KhachHang> findAllByObject() {
        return list;
    }

    @Override
    public boolean save(KhachHang khachHang) {
        return list.add(khachHang);
    }

    @Override
    public void update(KhachHang khachHang) {
        int id = khachHang.getId() - 1;
        list.set(id, khachHang);
    }

    @Override
    public boolean delete(Object o) {
        KhachHang khachHang = (KhachHang) o;
        for (int i = 0; i <= list.size(); i++) {
            if (khachHang.getId() == list.get(i).getId()) {
                list.set(i, khachHang);
                break;
            }
        }
        return true;
    }

    @Override
    public KhachHang findById(Object o) {
        Integer id = Integer.parseInt((String) o);
        return list
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<KhachHang> findByName(String name) {
        List<KhachHang> cuaHangList = this.list
                .stream()
                .filter(t -> t.getTen().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        return cuaHangList;
    }

    @Override
    public List<KhachHang> findByPaing(int index) {
        int kichThuocTrang = 3;
        int start = (index - 1) * 3;
        int end = Math.min(start + 3, list.size());
        return list.subList(start, end);
    }
}
