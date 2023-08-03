package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import model.entity.GioHang;
import model.entity.MauSac;
import model.repository.GioHangRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GioHangReposImple implements GioHangRepository {

    private static Session Hsession;

    public GioHangReposImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }

    @Override
    public List<GioHang> findAllByObject() {
        String hql = "SELECT gh FROM GioHang gh";
        TypedQuery<GioHang> query = Hsession.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public boolean save(GioHang gioHang) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.persist(gioHang);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public void update(GioHang gioHang) {
//        int id = gioHang.getId();
//        for (int i = 0; i <= list.size(); i++) {
//            if (list.get(i).getId() == id) {
//                list.set(i, gioHang);
//                break;
//            }
//        }
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public GioHang findById(Object o) {
        GioHang hang = Hsession.find(GioHang.class, UUID.fromString(o.toString()));
        return hang;
    }

    @Override
    public List<GioHang> findByName(String name) {
        return null;
    }
}
