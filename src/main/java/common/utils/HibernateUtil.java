package common.utils;

import jakarta.persistence.TypedQuery;
import model.entity.CuaHang;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

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
        conf.addAnnotatedClass(CuaHang.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        Session hSession = getFACTORY().openSession();
        String ma = "CH005";
        String hql = "SELECT ch FROM CuaHang ch WHERE ch.ma = ?1";
        TypedQuery<CuaHang> query = hSession.createQuery(hql, CuaHang.class);
        query.setParameter(1, ma);
        CuaHang cv = query.getSingleResult();
        System.out.println(cv.getTen());

//        List<NhanVien> listNv = cv.getListNv();
//        NhanVien nv = listNv.get(0);
//        System.out.println(nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen());
    }
}
