package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import model.entity.NSX;
import model.entity.SanPham;
import model.repository.NhaSanXuatRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NhaSanXuatReposImple implements NhaSanXuatRepository {
    private final Session Hsession;

    public NhaSanXuatReposImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }


    @Override
    public List<NSX> findAllByObject() {
        String hql = "SELECT nsx FROM NSX nsx";
        TypedQuery<NSX> query = Hsession.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public boolean save(NSX nsx) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.save(nsx);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public void update(NSX nsx) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.merge(nsx);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        }
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public NSX findById(Object o) {
        UUID id = UUID.fromString(o.toString());
        NSX nsx = Hsession.find(NSX.class, id);
        return nsx;
    }

    @Override
    public List<NSX> findByName(String name) {
        String hql = "SELECT sp FROM NSX sp WHERE sp.ten like :tenSP";
        TypedQuery<NSX> query = Hsession.createQuery(hql);
        query.setParameter("tenSP", "%" + name + "%");
        return query.getResultList();
    }
}
