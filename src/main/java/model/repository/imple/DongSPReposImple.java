package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import model.entity.CuaHang;
import model.entity.DongSP;
import model.repository.DongSanPhamRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class DongSPReposImple implements DongSanPhamRepository {
    private final Session Hsession;

    public DongSPReposImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }


    @Override
    public List<DongSP> findAllByObject() {
        String hql = "SELECT dongsp FROM DongSP dongsp";
        return Hsession.createQuery(hql, DongSP.class).getResultList();
    }

    @Override
    public boolean save(DongSP dongSP) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.persist(dongSP);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public void update(DongSP dongSP) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.persist(dongSP);
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
    public DongSP findById(Object o) {
        String hql = "SELECT dongSP FROM DongSP dongSP WHERE dongSP.id = :id";
        TypedQuery<DongSP> hangTypedQuery = Hsession.createQuery(hql, DongSP.class);
        hangTypedQuery.setParameter("id", UUID.fromString(o.toString()));
        return hangTypedQuery.getSingleResult();
    }

    @Override
    public List<DongSP> findByName(String name) {
        String hql = "SELECT ch FROM DongSP ch WHERE ch.ten LIKE '%?%'";
        TypedQuery<DongSP> hangTypedQuery = Hsession.createQuery(hql, DongSP.class);
        hangTypedQuery.setParameter(1, name);
        return hangTypedQuery.getResultList();
    }
}
