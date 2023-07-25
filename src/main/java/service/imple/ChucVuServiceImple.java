package service.imple;

import model.entity.ChucVu;
import model.repository.ChucVuRepository;
import model.repository.imple.ChucVuReposImple;
import service.ChucVuService;

import java.util.List;
import java.util.Optional;

public class ChucVuServiceImple implements ChucVuService {

    private final ChucVuRepository chucVuRepositoryImple;

    public ChucVuServiceImple() {
        chucVuRepositoryImple = new ChucVuReposImple();
    }

    @Override
    public List<ChucVu> findAllByObject() {
        return chucVuRepositoryImple.findAllByObject();
    }

    @Override
    public boolean save(ChucVu chucVu) {
        Optional<ChucVu> optional = Optional.ofNullable(chucVu);
        if (optional.isPresent()) {
            chucVuRepositoryImple.save(chucVu);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(ChucVu chucVu) {
        chucVuRepositoryImple.update(chucVu);
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public ChucVu findById(Object o) {
        return chucVuRepositoryImple.findById(o);
    }

    @Override
    public List<ChucVu> findByName(String name) {
        return null;
    }
}
