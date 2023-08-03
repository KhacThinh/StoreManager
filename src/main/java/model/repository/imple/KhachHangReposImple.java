package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.entity.KhachHang;
import model.repository.KhachHangRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

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
        String hql = "SELECT ch FROM KhachHang ch WHERE ch.id = :id ";
        TypedQuery<KhachHang> hangTypedQuery = Hsession.createQuery(hql, KhachHang.class);
        hangTypedQuery.setParameter("id", UUID.fromString(o.toString()));
        return hangTypedQuery.getSingleResult();
    }

    @Override
    public List<KhachHang> findByName(String name) {
        String hql = "SELECT ch FROM KhachHang ch WHERE ch.ten LIKE :name or ch.tenDem LIKE :name or ch.ho LIKE :name and ch.trangThai = true";
        TypedQuery<KhachHang> hangTypedQuery = Hsession.createQuery(hql, KhachHang.class);
        hangTypedQuery.setParameter("name", "%" + name + "%");
        return hangTypedQuery.getResultList();
    }

    @Override
    public List<KhachHang> findByPaing(int index) {
        int kichThuocTrang = 3;
        int start = (index - 1) * kichThuocTrang;
        String hql = "SELECT kh FROM KhachHang kh WHERE kh.trangThai = true ORDER BY kh.ma DESC";
        TypedQuery<KhachHang> query = Hsession.createQuery(hql, KhachHang.class);
        int totalResults = query.getResultList().size();
        int end = Math.min(start + kichThuocTrang, totalResults);
        List<KhachHang> list = query.getResultList().subList(start, end);
        return list;
    }

    @Override
    public KhachHang findByMa(Object o) {
        String hql = "SELECT ch FROM KhachHang ch WHERE ch.ma = :ma";
        TypedQuery<KhachHang> hangTypedQuery = Hsession.createQuery(hql, KhachHang.class);
        hangTypedQuery.setParameter("ma", o.toString());
        KhachHang khachHang = hangTypedQuery.getSingleResult();
        return khachHang;
    }
}
