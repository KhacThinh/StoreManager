package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.entity.CuaHang;
import model.entity.KhachHang;
import model.repository.KhachHangRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class KhachHangReposImple implements KhachHangRepository {
    private final Session Hsession;

    public KhachHangReposImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }


    @Override
    public List<KhachHang> findAllByObject() {
        String hql = "SELECT ch FROM KhachHang ch";
        return Hsession.createQuery(hql, KhachHang.class).getResultList();
    }

    @Override
    public boolean save(KhachHang khachHang) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.persist(khachHang);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public void update(KhachHang khachHang) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.merge(khachHang);
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
            Hsession.save(o.getClass());
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public KhachHang findById(Object o) {
        String hql = "SELECT ch FROM KhachHang ch WHERE ch.id = :id";
        TypedQuery<KhachHang> hangTypedQuery = Hsession.createQuery(hql, KhachHang.class);
        hangTypedQuery.setParameter("id", UUID.fromString(o.toString()));
        return hangTypedQuery.getSingleResult();
    }

    @Override
    public List<KhachHang> findByName(String name) {
        String hql = "SELECT ch FROM KhachHang ch WHERE ch.ten LIKE '%?%'";
        TypedQuery<KhachHang> hangTypedQuery = Hsession.createQuery(hql, KhachHang.class);
        hangTypedQuery.setParameter(1, name);
        return hangTypedQuery.getResultList();
    }

    @Override
    public List<KhachHang> findByPaing(int index) {
        int kichThuocTrang = 3;
        int start = (index - 1) * kichThuocTrang;
        String hql = "SELECT kh FROM KhachHang kh";
        Query query = Hsession.createQuery(hql, KhachHang.class);
        int end = Math.min(start + 3, query.getResultList().size());
        query.setFirstResult(start);
        query.setMaxResults(end);
        List<KhachHang> list = query.getResultList();
        return list;
    }
}
