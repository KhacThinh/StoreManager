package model.repository.imple;

import model.entity.ChucVu;
import model.entity.CuaHang;
import model.entity.MauSac;
import model.entity.NhanVien;
import model.repository.ChucVuRepository;
import model.repository.CuaHangRepository;
import model.repository.NhanVienRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NhanVienReposImple implements NhanVienRepository {

    private final static List<NhanVien> list = new ArrayList<>();
    private final CuaHangRepository cuaHangRepository;
    private final ChucVuRepository chucVuRepository;


    public NhanVienReposImple() {
        cuaHangRepository = new CuaHangReposImple();
        chucVuRepository = new ChucVuReposImple();
        khoiTao();
    }

    public void khoiTao() {
        List<CuaHang> cuaHangList = cuaHangRepository.findAllByObject();
        List<ChucVu> chucVuList = chucVuRepository.findAllByObject();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            list.add(new NhanVien(1, "NV001", "Nguyen", "Van", "Hoang", true, dateFormat.parse("01/01/1990"), "123 ABC Street", "0123456789", "123456", cuaHangList.get(1), chucVuList.get(0), 1, true));
            list.add(new NhanVien(2, "NV002", "Tran", "Thi", "Phuong", false, dateFormat.parse("02/02/1991"), "456 XYZ Street", "0987654321", "654321", cuaHangList.get(2), chucVuList.get(0), 1, true));
            list.add(new NhanVien(3, "NV003", "Le", "Hoang", "Tuan", true, dateFormat.parse("03/03/1992"), "789 QWE Street", "0456123789", "987654", cuaHangList.get(3), chucVuList.get(2), 2, true));
            list.add(new NhanVien(4, "NV004", "Pham", "Van", "Khanh", true, dateFormat.parse("04/04/1993"), "321 ASD Street", "0789321654", "789123", cuaHangList.get(2), chucVuList.get(1), 2, true));
            list.add(new NhanVien(5, "NV005", "Hoang", "Thi", "Ngoc", false, dateFormat.parse("05/05/1994"), "654 ZXC Street", "0654789321", "456789", cuaHangList.get(1), chucVuList.get(2), 1, true));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
