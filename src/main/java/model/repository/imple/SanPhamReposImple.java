package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import model.entity.SanPham;
import model.repository.SanPhamRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.UUID;

public class SanPhamReposImple implements SanPhamRepository {
    private final Session Hsession;

    public SanPhamReposImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }


    @Override
    public List<SanPham> findAllByObject() {
        String hql = "SELECT sp FROM SanPham sp";
        TypedQuery<SanPham> query = Hsession.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public boolean save(SanPham sanPham) {
        Hsession.getTransaction().begin();
        try {
            Hsession.persist(sanPham);
            Hsession.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Hsession.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public void update(SanPham sanPham) {
        Hsession.getTransaction().begin();
        try {
            Hsession.merge(sanPham);
            Hsession.getTransaction().commit();
        } catch (Exception ex) {
            Hsession.getTransaction().rollback();
        }
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public SanPham findById(Object o) {
        UUID id = UUID.fromString(o.toString());
        SanPham sanPham = Hsession.find(SanPham.class, id);
        return sanPham;
    }

    @Override
    public List<SanPham> findByName(String name) {
        String hql = "SELECT sp FROM SanPham sp WHERE sp.ten like :tenSP";
        TypedQuery<SanPham> query = Hsession.createQuery(hql);
        query.setParameter("tenSP", "%" + name + "%");
        return query.getResultList();
    }
}
