package model.repository.imple;

import common.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import model.entity.ChucVu;
import model.repository.ChucVuRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class ChucVuReposImple implements ChucVuRepository {

    private final Session Hsession;

    public ChucVuReposImple() {
        Hsession = HibernateUtil.getFACTORY().openSession();
    }


    @Override
    public List<ChucVu> findAllByObject() {
        String hql = "SELECT cv FROM ChucVu cv";
        return Hsession.createQuery(hql, ChucVu.class).getResultList();
    }

    @Override
    public boolean save(ChucVu chucVu) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.persist(chucVu);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public void update(ChucVu chucVu) {
        Transaction transaction = Hsession.getTransaction();
        transaction.begin();
        try {
            Hsession.merge(chucVu);
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
    public ChucVu findById(Object o) {
        String hql = "SELECT cv FROM ChucVu cv WHERE cv.id = :id";
        return Hsession.createQuery(hql, ChucVu.class)
                .setParameter("id", UUID.fromString(o.toString()))
                .getSingleResult();
    }

    @Override
    public List<ChucVu> findByName(String name) {
        String hql = "SELECT cv FROM ChucVu cv WHERE cv.ten LIKE :name";
        TypedQuery<ChucVu> chucVuTypedQuery = Hsession.createQuery(hql, ChucVu.class);
        chucVuTypedQuery.setParameter(name, "%" + name + "%");
        List<ChucVu> list = chucVuTypedQuery.getResultList();
        return list;
    }
}
