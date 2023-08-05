package model.repository.imple;


import common.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import model.entity.CuaHang;
import model.entity.NhanVien;
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
        CuaHang cuaHang = Hsession.find(CuaHang.class, UUID.fromString(o.toString()));
        return cuaHang;
    }

    @Override
    public List<CuaHang> findByName(String name) {
        String hql = "SELECT ch FROM CuaHang ch WHERE ch.ten LIKE :name and ch.trangThai = true";
        TypedQuery<CuaHang> hangTypedQuery = Hsession.createQuery(hql, CuaHang.class);
        hangTypedQuery.setParameter("name", "%" + name + "%");
        return hangTypedQuery.getResultList();
    }

    @Override
    public List<CuaHang> findByPaing(int index) {
        int kichThuocDuLieu = 4;
        int start = (index - 1) * kichThuocDuLieu;
        String hql = "SELECT ch FROM CuaHang ch where ch.trangThai = true order by ch.ma desc";
        TypedQuery<CuaHang> query = Hsession.createQuery(hql);
        int end = Math.min(start + kichThuocDuLieu, query.getResultList().size());
        List<CuaHang> list = query.getResultList().subList(start, end);
        return list;
    }
}
