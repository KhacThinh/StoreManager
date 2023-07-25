package service.imple;

import model.entity.ChiTietSP;
import model.repository.ChiTietSanPhamRepository;
import model.repository.imple.ChiTietSPRepoImple;
import service.ChiTietSanPhamService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChiTietSPServiceImple implements ChiTietSanPhamService {

    private final ChiTietSanPhamRepository chiTietSPRepository;

    public ChiTietSPServiceImple() {
        chiTietSPRepository = new ChiTietSPRepoImple();
    }

    @Override
    public List<ChiTietSP> findAllByObject() {
        List<ChiTietSP> list = chiTietSPRepository.findAllByObject();
        return list
                .stream()
                .sorted((o1, o2) -> o2.getId() - o1.getId())
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(ChiTietSP chiTietSP) {
        Optional<ChiTietSP> optional = Optional.ofNullable(chiTietSP);
        if (!optional.isPresent()) {
            return false;
        } else {
            optional.get().setId(chiTietSPRepository.findAllByObject().size() + 1);
            return chiTietSPRepository.save(optional.get());
        }
    }

    @Override
    public void update(ChiTietSP chiTietSP) {
        chiTietSPRepository.update(chiTietSP);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public ChiTietSP findById(Object o) {
        return null;
    }

    @Override
    public List<ChiTietSP> findByName(String name) {
        return chiTietSPRepository.findByName(name);
    }

    @Override
    public List<ChiTietSP> findPaing(int index) {
        return chiTietSPRepository.findPaing(index);
    }
}
