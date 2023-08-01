package model.repository.imple;


import common.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import model.entity.CuaHang;
import model.repository.CuaHangRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CuaHangReposImple implements CuaHangRepository {

    private final Session Hsession;

    public CuaHangReposImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }


    @Override
    public List<CuaHang> findAllByObject() {
        String hql = "SELECT ch FROM CuaHang ch";
        return Hsession.createQuery(hql, CuaHang.class).getResultList();
    }

    @Override
    public boolean save(CuaHang cuaHang) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.persist(cuaHang);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public void update(CuaHang cuaHang) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.merge(cuaHang);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        }
    }

    @Override
    public boolean delete(Object o) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.save(o);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public CuaHang findById(Object o) {
        String hql = "SELECT ch FROM CuaHang ch WHERE ch.id = :id";
        TypedQuery<CuaHang> hangTypedQuery = Hsession.createQuery(hql, CuaHang.class);
        hangTypedQuery.setParameter("id", UUID.fromString(o.toString()));
        CuaHang cuaHang = hangTypedQuery.getSingleResult();
        return cuaHang;
    }

    @Override
    public List<CuaHang> findByName(String name) {
        String hql = "SELECT ch FROM CuaHang ch WHERE ch.ten LIKE '%?%'";
        TypedQuery<CuaHang> hangTypedQuery = Hsession.createQuery(hql, CuaHang.class);
        hangTypedQuery.setParameter(1, name);
        return hangTypedQuery.getResultList();
    }
}
