package model.repository.imple;

import model.entity.ChucVu;
import model.repository.ChucVuRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChucVuReposImple implements ChucVuRepository {

    private final static List<ChucVu> list = new ArrayList<>();

    public ChucVuReposImple() {
    }

    static {
        list.add(new ChucVu(1, "CV1", "Nhân Viên"));
        list.add(new ChucVu(2, "CV2", "Trưởng Phòng"));
        list.add(new ChucVu(3, "CV3", "Kế Toán"));
    }


    @Override
    public List<ChucVu> findAllByObject() {
        return list.stream()
                .sorted((o1, o2) -> o2.getId() - o1.getId())
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(ChucVu chucVu) {
        return list.add(chucVu);
    }

    @Override
    public void update(ChucVu chucVu) {
        int id = chucVu.getId() - 1;
        list.set(id, chucVu);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public ChucVu findById(Object o) {
        Integer id = Integer.parseInt((String) o);
        return list
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ChucVu> findByName(String name) {
        return null;
    }
}
