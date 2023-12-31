package service.imple;

import model.entity.CuaHang;
import model.entity.NhanVien;
import model.repository.CuaHangRepository;
import model.repository.imple.CuaHangReposImple;
import service.CuaHangService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CuaHangServiceImple implements CuaHangService {

    private final CuaHangRepository cuaHangRepository;

    public CuaHangServiceImple() {
        cuaHangRepository = new CuaHangReposImple();
    }

    @Override
    public List<CuaHang> findAllByObject() {
        return cuaHangRepository
                .findAllByObject()
                .stream()
                .filter(t -> t.isTrangThai() == true)
                .sorted((o1, o2) -> o2.getMa().compareToIgnoreCase(o1.getMa()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(CuaHang cuaHang) {
        Optional<CuaHang> optional = Optional.ofNullable(cuaHang);
        if (optional.isPresent()) {
            cuaHangRepository.save(cuaHang);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(CuaHang cuaHang) {
        cuaHangRepository.update(cuaHang);
    }

    @Override
    public boolean delete(Object o) {
        Optional<CuaHang> cuaHang = Optional.ofNullable((CuaHang) o);
        if (cuaHang.isPresent()) {
            cuaHangRepository.delete(cuaHang.get());
            return true;
        }
        return false;
    }

    @Override
    public CuaHang findById(Object o) {
        return cuaHangRepository.findById(o);
    }

    @Override
    public List<CuaHang> findByName(String name) {
        return cuaHangRepository.findByName(name);
    }

    @Override
    public List<CuaHang> findByPaing(int index) {
        return cuaHangRepository.findByPaing(index);
    }
}
