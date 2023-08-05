package common.utils;


import jakarta.persistence.TypedQuery;
import model.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.*;

import org.hibernate.service.ServiceRegistry;
import service.KhachHangService;
import service.NhanVienService;
import service.imple.KhachHangServiceImple;
import service.imple.NhanVienServiceImple;

public class HibernateUtil {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=FINALASS_FPOLYSHOP_FA22_SOF205__SOF2041");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
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
        conf.addAnnotatedClass(GioHangChiTiet.class);
        conf.addAnnotatedClass(GioHang.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        Session hSession = getFACTORY().openSession();
        KhachHangService khachHangService = new KhachHangServiceImple();
        NhanVienService nhanVienService = new NhanVienServiceImple();

//        String idToFind = "0f34b393-005f-43c1-b234-3469f3d84681";
//        UUID uuidToFind = UUID.fromString(idToFind);
        KhachHang khachHang = khachHangService
                .findAllByObject()
                .stream()
                .filter(t -> t.getId().equals(UUID.fromString("bfa23683-32e2-d24e-b0c5-ad573fec96f9")))
                .findFirst().orElse(null);
        NhanVien nhanVien = nhanVienService.findByMa("NV0003");

        GioHang gioHang = new GioHang(null, khachHang, nhanVien,"GH0002", new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()), "Khắc Thịnh", "123 Ngõ Cầu Diễn", "0934339795",true);
        Transaction transaction = hSession.getTransaction();
        transaction.begin();
        try{
            hSession.persist(gioHang);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }



//        try{
//            String hql = "SELECT ch FROM GioHangChiTiet ch";
//            TypedQuery<GioHangChiTiet> hangTypedQuery = hSession.createQuery(hql, GioHangChiTiet.class);
////            List<GioHangChiTiet> list = ;
//
//            if(hangTypedQuery.getResultList().isEmpty()){
//                System.out.println("Khong tim thay");
//            }else{
//                hangTypedQuery.getResultList().forEach(System.out::println);
//            }
//        }catch (Exception ex){
////            ex.printStackTrace();
//        }

//        String hqls = "SELECT ch FROM NhanVien ch";
//        TypedQuery<NhanVien> hangTypedQuery1 = hSession.createQuery(hqls, NhanVien.class);
//        List<NhanVien> list1 = hangTypedQuery1.getResultList();
//        list1.forEach(System.out::println);

//        Optional<ChiTietSP> chiTietSP = list.stream()
//                .filter(chiTietSP1 -> chiTietSP1.getId().equals(uuidToFind))
//                .findFirst();

//        if (chiTietSP.isPresent()) {
//            System.out.println(chiTietSP.get().toString());
//        } else {
//            System.out.println("Khong tim thay");
//        }

    }
}
