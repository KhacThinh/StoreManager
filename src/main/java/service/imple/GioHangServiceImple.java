package service.imple;

import model.entity.GioHang;
import model.repository.imple.GioHangReposImple;
import service.GioHangService;

import java.util.List;

public class GioHangServiceImple implements GioHangService {
    private final GioHangReposImple gioHangRepository;

    public GioHangServiceImple() {
        gioHangRepository = new GioHangReposImple();
    }

    @Override
    public List<GioHang> findAllByObject() {
        return gioHangRepository.findAllByObject();
    }

    @Override
    public boolean save(GioHang gioHang) {
        return gioHangRepository.save(gioHang);
    }

    @Override
    public void update(GioHang gioHang) {
        gioHangRepository.update(gioHang);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public GioHang findById(Object o) {
        return null;
    }

    @Override
    public List<GioHang> findByName(String name) {
        return null;
    }
}
