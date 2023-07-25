package model.repository.imple;

import model.entity.MauSac;
import model.repository.MauSacRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MauSacReposImple implements MauSacRepository {
    private final static List<MauSac> list = new ArrayList<>();

    static {
        list.add(new MauSac(1, "#FF0000", "Đỏ"));
        list.add(new MauSac(2, "#00FF00", "Xanh lá"));
        list.add(new MauSac(3, "#0000FF", "Xanh dương"));
        list.add(new MauSac(4, "#FFFF00", "Vàng"));
        list.add(new MauSac(5, "#FF00FF", "Hồng"));
        list.add(new MauSac(6, "#00FFFF", "Xanh ngọc"));
        list.add(new MauSac(7, "#FFFFFF", "Trắng"));
    }


    @Override
    public List<MauSac> findAllByObject() {
        return list.stream()
                .sorted((o1, o2) -> o2.getId() - o1.getId())
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(MauSac mauSac) {
        return list.add(mauSac);
    }

    @Override
    public void update(MauSac mauSac) {
        int id = mauSac.getId() - 1;
        list.set(id, mauSac);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public MauSac findById(Object o) {
        Integer id = Integer.parseInt((String) o);
        return list
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<MauSac> findByName(String name) {
        return null;
    }
}
