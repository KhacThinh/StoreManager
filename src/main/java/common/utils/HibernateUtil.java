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
//        String hql = "SELECT ch FROM NhanVien ch";
//        TypedQuery<NhanVien> hangTypedQuery = hSession.createQuery(hql, NhanVien.class);
////        hangTypedQuery.setParameter("id", "KH0003");
//        List<NhanVien> sanPham =  hangTypedQuery.getResultList();
//        if(sanPham.isEmpty()){
//            System.out.println("Khong tim thấy");
//        }else{
//            sanPham.forEach(System.out::println);
//        }


        String hql = "SELECT ch FROM NhanVien ch where ch.id = :ma";
        TypedQuery<NhanVien> hangTypedQuery = hSession.createQuery(hql, NhanVien.class);
        hangTypedQuery.setParameter("ma", "C7E31E21-6A41-5440-8F35-7005390C7DC5");
        NhanVien sanPham = hangTypedQuery.getSingleResult();

//        UUID id = UUID.fromString("A9F78E85-8054-4DF9-A9E3-CF70426B20AD");
//        NhanVien sanPham = hSession.find(NhanVien.class, id);
        if (sanPham == null) {
            System.out.println("Khong tim thấy");
        } else {
            System.out.println(sanPham.toString());
        }

    }
}
