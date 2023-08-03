package service.imple;

import model.entity.NSX;
import model.repository.NhaSanXuatRepository;
import model.repository.imple.NhaSanXuatReposImple;
import service.NhaSanXuatService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NSXServiceImple implements NhaSanXuatService {

    private final NhaSanXuatRepository nsxRepository;

    public NSXServiceImple() {
        nsxRepository = new NhaSanXuatReposImple();
    }

    @Override
    public List<NSX> findAllByObject() {
        return nsxRepository
                .findAllByObject()
                .stream()
                .sorted((o1, o2) -> o2.getMa().compareToIgnoreCase(o1.getMa()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(NSX nsx) {
        Optional<NSX> optional = Optional.ofNullable(nsx);
        if (optional.isPresent()) {
            nsxRepository.save(nsx);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(NSX mauSac) {
        nsxRepository.update(mauSac);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public NSX findById(Object o) {
        return nsxRepository.findById(o);
    }

    @Override
    public List<NSX> findByName(String name) {
        return nsxRepository.findByName(name);
    }
}
