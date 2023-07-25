package service.imple;

import model.entity.SanPham;
import model.repository.SanPhamRepository;
import model.repository.imple.SanPhamReposImple;
import service.SanPhamService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SanPhamServiceImple implements SanPhamService {

    private final SanPhamRepository sanPhamRepository;

    public SanPhamServiceImple() {
        sanPhamRepository = new SanPhamReposImple();
    }

    @Override
    public List<SanPham> findAllByObject() {
        List<SanPham> list = sanPhamRepository.findAllByObject();
        return list
                .stream()
                .sorted((o1, o2) -> o2.getId() - o1.getId())
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(SanPham sanPham) {
        Optional<SanPham> optional = Optional.ofNullable(sanPham);
        if (optional.isPresent()) {
            sanPhamRepository.save(sanPham);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(SanPham sanPham) {
        sanPhamRepository.update(sanPham);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public SanPham findById(Object o) {
        return sanPhamRepository.findById(o);
    }

    @Override
    public List<SanPham> findByName(String name) {
        return null;
    }
}
