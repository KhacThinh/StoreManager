package service.imple;


import model.entity.NhanVien;
import model.repository.NhanVienRepository;
import model.repository.imple.NhanVienReposImple;
import service.NhanVienService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NhanVienServiceImple implements NhanVienService {

    private final NhanVienRepository nhanVienRepository;

    public NhanVienServiceImple() {
        nhanVienRepository = new NhanVienReposImple();
    }

    @Override
    public List<NhanVien> findAllByObject() {
        List<NhanVien> list = nhanVienRepository.findAllByObject();
        return list
                .stream()
                .filter(t -> t.isTrangThai() == true)
                .sorted((o1, o2) -> o2.getId() - o1.getId())
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(NhanVien nhanVien) {
        Optional<NhanVien> optional = Optional.ofNullable(nhanVien);
        if (optional.isPresent()) {
            nhanVienRepository.save(nhanVien);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(NhanVien nhanVien) {
        nhanVienRepository.update(nhanVien);
    }

    @Override
    public boolean delete(Object o) {
        NhanVien nhanVien = (NhanVien) o;
        if (nhanVien != null) {
            return nhanVienRepository.delete(nhanVien);
        }
        return false;
    }

    @Override
    public NhanVien findById(Object o) {
        return nhanVienRepository.findById(o);
    }

    @Override
    public List<NhanVien> findByName(String name) {
        return nhanVienRepository.findByName(name);
    }

    @Override
    public List<NhanVien> findByPaing(int index) {
        return nhanVienRepository.findByPaing(index);
    }
}
