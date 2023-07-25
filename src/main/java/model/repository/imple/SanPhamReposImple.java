package model.repository.imple;

import model.entity.SanPham;
import model.repository.SanPhamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SanPhamReposImple implements SanPhamRepository {
    private final static List<SanPham> list = new ArrayList<>();

    static {
        list.add(new SanPham(1, "SP001", "Nồi cơm điện"));
        list.add(new SanPham(2, "SP002", "Bàn ủi"));
        list.add(new SanPham(3, "SP003", "Quạt điện"));
        list.add(new SanPham(4, "SP004", "Đèn bàn"));
        list.add(new SanPham(5, "SP005", "Máy lọc không khí"));
        list.add(new SanPham(6, "SP006", "Ổ cắm điện"));
        list.add(new SanPham(7, "SP007", "Máy hút bụi"));
        list.add(new SanPham(8, "SP008", "Máy xay sinh tố"));
        list.add(new SanPham(9, "SP009", "Máy nướng bánh mì"));
        list.add(new SanPham(10, "SP010", "Máy nướng pizza"));
        list.add(new SanPham(11, "SP011", "Máy pha cà phê"));
        list.add(new SanPham(12, "SP012", "Máy xay thịt"));
        list.add(new SanPham(13, "SP013", "Máy ép trái cây"));
        list.add(new SanPham(14, "SP014", "Máy làm sữa chua"));
        list.add(new SanPham(15, "SP015", "Máy làm bánh quy"));
        list.add(new SanPham(16, "SP016", "Máy đánh trứng"));
        list.add(new SanPham(17, "SP017", "Máy trộn bột"));
    }


    @Override
    public List<SanPham> findAllByObject() {
        return list;
    }

    @Override
    public boolean save(SanPham sanPham) {
        return list.add(sanPham);
    }

    @Override
    public void update(SanPham sanPham) {
        int id = sanPham.getId() - 1;
        list.set(id, sanPham);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public SanPham findById(Object o) {
        Integer id = Integer.parseInt((String) o);
        return list
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<SanPham> findByName(String name) {
        return null;
    }
}
