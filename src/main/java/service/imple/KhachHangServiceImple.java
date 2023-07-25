package service.imple;

import model.entity.KhachHang;
import model.repository.KhachHangRepository;
import model.repository.imple.KhachHangReposImple;
import service.KhachHangService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class KhachHangServiceImple implements KhachHangService {

    private final KhachHangRepository khachHangRepository;

    public KhachHangServiceImple() {
        khachHangRepository = new KhachHangReposImple();
    }

    @Override
    public List<KhachHang> findAllByObject() {
        List<KhachHang> list = khachHangRepository.findAllByObject();
        return list
                .stream()
                .filter(t -> t.isTrangThai() == true)
                .sorted((o1, o2) -> o2.getId() - o1.getId())
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(KhachHang khachHang) {
        Optional<KhachHang> optional = Optional.ofNullable(khachHang);
        if (optional.isPresent()) {
            khachHangRepository.save(khachHang);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(KhachHang khachHang) {
        khachHangRepository.update(khachHang);
    }

    @Override
    public boolean delete(Object o) {
        Optional<KhachHang> khachHang = Optional.ofNullable((KhachHang) o);
        if (khachHang.isPresent()) {
            return khachHangRepository.delete(o);
        }
        return false;
    }

    @Override
    public KhachHang findById(Object o) {
        return null;
    }

    @Override
    public List<KhachHang> findByName(String name) {
        return khachHangRepository.findByName(name);
    }

    @Override
    public List<KhachHang> findByPaing(int index) {
        return khachHangRepository.findByPaing(index);
    }
}
