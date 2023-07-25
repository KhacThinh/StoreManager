package service.imple;

import model.entity.GioHangChiTiet;
import model.repository.GioHangChiTietRepository;
import model.repository.imple.GioHangChiTietRepositoryImple;
import service.GioHangChiTietService;

import java.util.List;

public class GioHangChiTietServiceImple implements GioHangChiTietService {

    private GioHangChiTietRepository gioHangChiTietRepository;

    public GioHangChiTietServiceImple() {
        gioHangChiTietRepository = new GioHangChiTietRepositoryImple();
    }

    @Override
    public List<GioHangChiTiet> findAllByObject() {
        return gioHangChiTietRepository.findAllByObject();
    }

    @Override
    public boolean save(GioHangChiTiet e) {
        return gioHangChiTietRepository.save(e);
    }

    @Override
    public void update(GioHangChiTiet e) {

    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public GioHangChiTiet findById(Object o) {
        return null;
    }

}
