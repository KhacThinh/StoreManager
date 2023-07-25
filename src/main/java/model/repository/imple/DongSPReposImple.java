package model.repository.imple;

import model.entity.DongSP;
import model.repository.DongSanPhamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DongSPReposImple implements DongSanPhamRepository {
    private final static List<DongSP> list = new ArrayList<>();

    static {
        list.add(new DongSP(1, "DSP001", "Giá rẻ"));
        list.add(new DongSP(2, "DSP002", "Bình Thường"));
        list.add(new DongSP(3, "DSP003", "Cao Cấp"));
    }


    @Override
    public List<DongSP> findAllByObject() {
        return list;
//                .stream()
//                .sorted((o1, o2) -> o2.getId() - o1.getId())
//                .collect(Collectors.toList());
    }

    @Override
    public boolean save(DongSP dongSP) {
        return list.add(dongSP);
    }

    @Override
    public void update(DongSP dongSP) {
        int id = dongSP.getId() - 1;
        list.set(id, dongSP);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public DongSP findById(Object o) {
        Integer id = Integer.parseInt((String) o);
        return list
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<DongSP> findByName(String name) {
        return null;
    }
}
