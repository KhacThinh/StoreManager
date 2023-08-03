package service.imple;

import model.entity.MauSac;
import model.repository.MauSacRepository;
import model.repository.imple.MauSacReposImple;
import service.MauSacService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MauSacServiceImple implements MauSacService {

    private final MauSacRepository mauSacRepository;

    public MauSacServiceImple() {
        mauSacRepository = new MauSacReposImple();
    }

    @Override
    public List<MauSac> findAllByObject() {
        return mauSacRepository.findAllByObject()
                .stream()
                .sorted((o1, o2) -> o2.getMa().compareToIgnoreCase(o1.getMa()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(MauSac mauSac) {
        Optional<MauSac> optional = Optional.ofNullable(mauSac);
        if (optional.isPresent()) {
            mauSacRepository.save(mauSac);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(MauSac mauSac) {
        mauSacRepository.update(mauSac);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public MauSac findById(Object o) {
        return mauSacRepository.findById(o);
    }

    @Override
    public List<MauSac> findByName(String name) {
        return null;
    }
}
