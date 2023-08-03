package common.utils;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=FINALASS_FPOLYSHOP_FA22_SOF205__SOF2041");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "23062003");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(CuaHang.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(DongSP.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(NSX.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(ChiTietSP.class);
        conf.addAnnotatedClass(KhachHang.class);
//        conf.addAnnotatedClass(GioHang.class);
//        conf.addAnnotatedClass(GioHangChiTiet.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        Session hSession = getFACTORY().openSession();

        String idToFind = "0f34b393-005f-43c1-b234-3469f3d84681";
        UUID uuidToFind = UUID.fromString(idToFind);

        String hql = "SELECT ch FROM ChiTietSP ch";
        TypedQuery<ChiTietSP> hangTypedQuery = hSession.createQuery(hql, ChiTietSP.class);
        List<ChiTietSP> list = hangTypedQuery.getResultList();

        list.forEach( chiTietSP -> System.out.println(chiTietSP.getId()));

        Optional<ChiTietSP> chiTietSP = list.stream()
                .filter(chiTietSP1 -> chiTietSP1.getId().equals(uuidToFind))
                .findFirst();

        if (chiTietSP.isPresent()) {
            System.out.println(chiTietSP.get().toString());
        } else {
            System.out.println("Khong tim thay");
        }

    }
}
