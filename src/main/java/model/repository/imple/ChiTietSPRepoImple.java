package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.entity.*;
import model.repository.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ChiTietSPRepoImple implements ChiTietSanPhamRepository {

    private final Session Hsession;

    public ChiTietSPRepoImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }


    @Override
    public List<ChiTietSP> findAllByObject() {
        String hql = "SELECT ctsp FROM ChiTietSP ctsp";
        TypedQuery<ChiTietSP> chiTietSPTypedQuery = Hsession.createQuery(hql);
        return chiTietSPTypedQuery.getResultList();
    }

    @Override
    public boolean save(ChiTietSP chiTietSP) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.persist(chiTietSP);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public void update(ChiTietSP chiTietSP) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.merge(chiTietSP);
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
    public ChiTietSP findById(Object o) {
        String hql = "SELECT ch FROM ChiTietSP ch WHERE ch.id = :id";
        TypedQuery<ChiTietSP> hangTypedQuery = Hsession.createQuery(hql, ChiTietSP.class);
        hangTypedQuery.setParameter("id", UUID.fromString(o.toString()));
        ChiTietSP chiTietSP = hangTypedQuery.getSingleResult();
        return chiTietSP;
    }

    @Override
    public List<ChiTietSP> findByName(String name) {
        String hql = "SELECT ctsp FROM ChiTietSP ctsp JOIN SanPham sp ON ctsp.idSP = sp.id WHERE sp.ten like :name";
        TypedQuery<ChiTietSP> chiTietSPTypedQuery = Hsession.createQuery(hql);
        chiTietSPTypedQuery.setParameter("name", "%" + name + "%");
        return chiTietSPTypedQuery.getResultList();
    }

    @Override
    public List<ChiTietSP> findPaing(int index) {
        int kichThuocTrang = 3;
        int batDau = (index - 1) * kichThuocTrang;
        String hql = "SELECT ctsp FROM ChiTietSP ctsp";
        Query query = Hsession.createQuery(hql, ChiTietSP.class);
        int ketThuc = Math.min(batDau + kichThuocTrang, query.getResultList().size());
        query.setFirstResult(batDau);
        query.setMaxResults(ketThuc);
        List<ChiTietSP> list = query.getResultList();
        return list;
    }

}
