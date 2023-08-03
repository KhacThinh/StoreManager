package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.entity.*;
import model.repository.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        String idToFind = o.toString();
        UUID uuidToFind = UUID.fromString(idToFind);
        String hql = "SELECT ch FROM ChiTietSP ch";
        TypedQuery<ChiTietSP> hangTypedQuery = Hsession.createQuery(hql, ChiTietSP.class);
        List<ChiTietSP> list = hangTypedQuery.getResultList();
        Optional<ChiTietSP> chiTietSP = list.stream()
                .filter(chiTietSP1 -> chiTietSP1.getId().equals(uuidToFind))
                .findFirst();

        if (chiTietSP.isPresent()) {
            return chiTietSP.get();
        }
        return null;
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
        List<ChiTietSP> list = query.getResultList().subList(batDau, ketThuc);
        return list;
    }

}
