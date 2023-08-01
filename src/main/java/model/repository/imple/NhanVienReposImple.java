package model.repository.imple;

import common.utils.HibernateUtil;
import model.entity.ChucVu;
import model.entity.CuaHang;
import model.entity.MauSac;
import model.entity.NhanVien;
import model.repository.ChucVuRepository;
import model.repository.CuaHangRepository;
import model.repository.NhanVienRepository;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NhanVienReposImple implements NhanVienRepository {

    private final Session Hsession;
    private final CuaHangRepository cuaHangRepository;
    private final ChucVuRepository chucVuRepository;


    public NhanVienReposImple() {
        cuaHangRepository = new CuaHangReposImple();
        chucVuRepository = new ChucVuReposImple();
        Hsession = HibernateUtil.getFACTORY().openSession();
    }


    @Override
    public List<NhanVien> findAllByObject() {
        return list;
    }

    @Override
    public boolean save(NhanVien nhanVien) {
        return list.add(nhanVien);
    }

    @Override
    public void update(NhanVien nhanVien) {
        int id = nhanVien.getId() - 1;
        list.set(id, nhanVien);
    }

    @Override
    public boolean delete(Object o) {
        NhanVien nhanVien = (NhanVien) o;
        for (int i = 0; i <= list.size(); i++) {
            if (nhanVien.getId() == list.get(i).getId()) {
                list.set(i, nhanVien);
                break;
            }
        }
        return true;
    }

    @Override
    public NhanVien findById(Object o) {
        return null;
    }

    @Override
    public List<NhanVien> findByName(String name) {
        List<NhanVien> cuaHangList = this.list
                .stream()
                .filter(t -> t.getTen().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        return cuaHangList;
    }

    @Override
    public List<NhanVien> findByPaing(int index) {
        int kichThuocDuLieu = 3;
        int start = (index - 1) * kichThuocDuLieu;
        int end = Math.min(start + 3, list.size());
        return list.subList(start, end);
    }
}
