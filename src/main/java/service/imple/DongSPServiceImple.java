package service.imple;

import model.entity.DongSP;
import model.entity.NhanVien;
import model.repository.DongSanPhamRepository;
import model.repository.imple.DongSPReposImple;
import service.DongSanPhamService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DongSPServiceImple implements DongSanPhamService {

    private final DongSanPhamRepository dongSPRepository;

    public DongSPServiceImple() {
        dongSPRepository = new DongSPReposImple();
    }

    @Override
    public List<DongSP> findAllByObject() {
        List<DongSP> list = dongSPRepository.findAllByObject();
        return list
                .stream()
                .sorted((o1, o2) -> o2.getId() - o1.getId())
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(DongSP dongSP) {
        Optional<DongSP> optional = Optional.ofNullable(dongSP);
        if (optional.isPresent()) {
            dongSPRepository.save(dongSP);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(DongSP dongSP) {
        dongSPRepository.update(dongSP);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public DongSP findById(Object o) {
        return dongSPRepository.findById(o);
    }

    @Override
    public List<DongSP> findByName(String name) {
        return null;
    }
}
