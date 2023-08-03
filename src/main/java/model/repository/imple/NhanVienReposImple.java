package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import model.entity.*;
import model.repository.NhanVienRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.UUID;

public class NhanVienReposImple implements NhanVienRepository {
    private final Session Hsession;

    public NhanVienReposImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }


    @Override
    public List<NhanVien> findAllByObject() {
        String hql = "SELECT nv FROM NhanVien nv";
        TypedQuery<NhanVien> query = Hsession.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public boolean save(NhanVien nhanVien) {
        Hsession.getTransaction().begin();
        try {
            Hsession.persist(nhanVien);
            Hsession.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Hsession.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public void update(NhanVien nhanVien) {
        Hsession.getTransaction().begin();
        try {
            Hsession.merge(nhanVien);
            Hsession.getTransaction().commit();
        } catch (Exception ex) {
            Hsession.getTransaction().rollback();
        }
    }

    @Override
    public boolean delete(Object o) {
        Hsession.getTransaction().begin();
        try {
            Hsession.save((NhanVien) o);
            Hsession.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Hsession.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public NhanVien findById(Object o) {
        UUID id = UUID.fromString(o.toString());
        NhanVien nhanVien = Hsession.find(NhanVien.class, id);
        return nhanVien;
    }

    @Override
    public List<NhanVien> findByName(String name) {
        String hql = "SELECT nv FROM NhanVien nv WHERE nv.ten like :tenNV";
        TypedQuery<NhanVien> query = Hsession.createQuery(hql);
        query.setParameter("tenNV", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<NhanVien> findByPaing(int index) {
        int kichThuocDuLieu = 3;
        int start = (index - 1) * kichThuocDuLieu;
        String hql = "SELECT nv FROM NhanVien nv where nv.trangThai = true order by nv.ma desc";
        TypedQuery<NhanVien> query = Hsession.createQuery(hql);
        int end = Math.min(start + 3, query.getResultList().size());
        List<NhanVien> list = query.getResultList().subList(start, end);
        return list;
    }

    @Override
    public NhanVien findByMa(Object o) {
        String hql = "SELECT nv FROM NhanVien nv where nv.ma = :ma";
        TypedQuery<NhanVien> hangTypedQuery = Hsession.createQuery(hql, NhanVien.class);
        hangTypedQuery.setParameter("ma", o.toString());
        NhanVien nhanVien = hangTypedQuery.getSingleResult();
        return nhanVien;
    }
}
