package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import model.entity.GioHangChiTiet;
import model.repository.GioHangChiTietRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GioHangChiTietRepositoryImple implements GioHangChiTietRepository {

    private final Session Hsession;

    public GioHangChiTietRepositoryImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }

    @Override
    public List<GioHangChiTiet> findAllByObject() {
        String hql = "SELECT ghct FROM GioHangChiTiet ghct";
        TypedQuery<GioHangChiTiet> query = Hsession.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public boolean save(GioHangChiTiet e) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.persist(e);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }


    @Override
    public void update(GioHangChiTiet e) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.merge(e);
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
    public GioHangChiTiet findById(Object o) {
        return null;
    }

    @Override
    public List<GioHangChiTiet> findByName(String name) {
        return null;
    }
}
