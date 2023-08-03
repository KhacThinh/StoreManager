package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import model.entity.CuaHang;
import model.entity.MauSac;
import model.repository.MauSacRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MauSacReposImple implements MauSacRepository {
    private final Session Hsession;

    public MauSacReposImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }


    @Override
    public List<MauSac> findAllByObject() {
        String hql = "SELECT ch FROM MauSac ch";
        return Hsession.createQuery(hql, MauSac.class).getResultList();
    }

    @Override
    public boolean save(MauSac mauSac) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.persist(mauSac);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public void update(MauSac mauSac) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.merge(mauSac);
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
    public MauSac findById(Object o) {
        String hql = "SELECT ch FROM MauSac ch WHERE ch.id = :id";
        TypedQuery<MauSac> hangTypedQuery = Hsession.createQuery(hql, MauSac.class);
        hangTypedQuery.setParameter("id", UUID.fromString(o.toString()));
        return hangTypedQuery.getSingleResult();
    }

    @Override
    public List<MauSac> findByName(String name) {
        String hql = "SELECT ch FROM MauSac ch WHERE ch.ten LIKE :name";
        TypedQuery<MauSac> hangTypedQuery = Hsession.createQuery(hql, MauSac.class);
        hangTypedQuery.setParameter("name", "%" + name + "%");
        return hangTypedQuery.getResultList();
    }
}
