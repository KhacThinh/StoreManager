package model.repository.imple;

import model.entity.*;
import model.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ChiTietSPRepoImple implements ChiTietSanPhamRepository {

    private final static List<ChiTietSP> list = new ArrayList<>();
    private final SanPhamRepository sanPhamRepository;
    private final NhaSanXuatRepository nhaSanXuatRepository;
    private final MauSacRepository mauSacRepository;
    private final DongSanPhamRepository dongSanPhamRepository;

    public ChiTietSPRepoImple() {
        sanPhamRepository = new SanPhamReposImple();
        nhaSanXuatRepository = new NhaSanXuatReposImple();
        mauSacRepository = new MauSacReposImple();
        dongSanPhamRepository = new DongSPReposImple();
        khoiTao();
    }

    public void khoiTao() {
        List<SanPham> sanPhamList = sanPhamRepository.findAllByObject();
        List<NSX> nsxList = nhaSanXuatRepository.findAllByObject();
        List<MauSac> mauSacList = mauSacRepository.findAllByObject();
        List<DongSP> dongSPList = dongSanPhamRepository.findAllByObject();
        list.add(new ChiTietSP(1, sanPhamList.get(1), nsxList.get(4), mauSacList.get(3), dongSPList.get(0), new Date(), "Sản Phẩm Xịn", 2000, 1000000, 1200000));
        list.add(new ChiTietSP(2, sanPhamList.get(2), nsxList.get(2), mauSacList.get(2), dongSPList.get(1), new Date(), "Sản Phẩm Đểu", 400, 170000, 200000));
        list.add(new ChiTietSP(3, sanPhamList.get(3), nsxList.get(1), mauSacList.get(3), dongSPList.get(1), new Date(), "Sản Phẩm Bình Thường", 5000, 400000, 500000));
        list.add(new ChiTietSP(4, sanPhamList.get(4), nsxList.get(6), mauSacList.get(4), dongSPList.get(2), new Date(), "Sản Phẩm Xịn", 100, 10000000, 13000000));
        list.add(new ChiTietSP(5, sanPhamList.get(5), nsxList.get(9), mauSacList.get(5), dongSPList.get(1), new Date(), "Sản Phẩm Xịn", 234, 999999, 1100000));
        list.add(new ChiTietSP(6, sanPhamList.get(6), nsxList.get(6), mauSacList.get(2), dongSPList.get(0), new Date(), "Sản Phẩm Xịn", 871, 197000, 200000));
        list.add(new ChiTietSP(7, sanPhamList.get(7), nsxList.get(3), mauSacList.get(6), dongSPList.get(2), new Date(), "Sản Phẩm Cao Cấp", 800, 2300000, 2500000));
        list.add(new ChiTietSP(8, sanPhamList.get(8), nsxList.get(7), mauSacList.get(1), dongSPList.get(0), new Date(), "Sản Phẩm Đẹp", 1500, 1500000, 1800000));
        list.add(new ChiTietSP(9, sanPhamList.get(9), nsxList.get(8), mauSacList.get(4), dongSPList.get(1), new Date(), "Sản Phẩm Tiện Lợi", 300, 380000, 420000));
        list.add(new ChiTietSP(10, sanPhamList.get(10), nsxList.get(5), mauSacList.get(2), dongSPList.get(2), new Date(), "Sản Phẩm Xịn", 650, 8000000, 9000000));
        list.add(new ChiTietSP(11, sanPhamList.get(11), nsxList.get(9), mauSacList.get(3), dongSPList.get(0), new Date(), "Sản Phẩm Đẳng Cấp", 900, 12000000, 14000000));
        list.add(new ChiTietSP(12, sanPhamList.get(12), nsxList.get(2), mauSacList.get(1), dongSPList.get(1), new Date(), "Sản Phẩm Cao Cấp", 700, 2900000, 3100000));
        list.add(new ChiTietSP(13, sanPhamList.get(13), nsxList.get(4), mauSacList.get(5), dongSPList.get(2), new Date(), "Sản Phẩm Xịn", 1200, 8500000, 9200000));
        list.add(new ChiTietSP(14, sanPhamList.get(14), nsxList.get(7), mauSacList.get(6), dongSPList.get(0), new Date(), "Sản Phẩm Đẹp", 200, 170000, 190000));
        list.add(new ChiTietSP(15, sanPhamList.get(15), nsxList.get(1), mauSacList.get(3), dongSPList.get(2), new Date(), "Sản Phẩm Tiện Lợi", 400, 400000, 450000));
        list.add(new ChiTietSP(16, sanPhamList.get(16), nsxList.get(8), mauSacList.get(2), dongSPList.get(0), new Date(), "Sản Phẩm Xịn", 800, 9500000, 10200000));
        list.add(new ChiTietSP(17, sanPhamList.get(3), nsxList.get(5), mauSacList.get(4), dongSPList.get(1), new Date(), "Sản Phẩm Bình Thường", 600, 320000, 370000));
        list.add(new ChiTietSP(18, sanPhamList.get(8), nsxList.get(1), mauSacList.get(5), dongSPList.get(0), new Date(), "Sản Phẩm Đẹp", 1000, 1300000, 1500000));
        list.add(new ChiTietSP(19, sanPhamList.get(12), nsxList.get(2), mauSacList.get(1), dongSPList.get(1), new Date(), "Sản Phẩm Cao Cấp", 750, 2800000, 3000000));
        list.add(new ChiTietSP(20, sanPhamList.get(7), nsxList.get(9), mauSacList.get(3), dongSPList.get(0), new Date(), "Sản Phẩm Đẳng Cấp", 950, 11000000, 13000000));
        list.add(new ChiTietSP(21, sanPhamList.get(5), nsxList.get(4), mauSacList.get(2), dongSPList.get(1), new Date(), "Sản Phẩm Xịn", 1800, 7500000, 8200000));
        list.add(new ChiTietSP(22, sanPhamList.get(9), nsxList.get(6), mauSacList.get(4), dongSPList.get(2), new Date(), "Sản Phẩm Tiện Lợi", 500, 410000, 460000));
        list.add(new ChiTietSP(23, sanPhamList.get(11), nsxList.get(7), mauSacList.get(6), dongSPList.get(0), new Date(), "Sản Phẩm Đẹp", 2300, 1600000, 1800000));
        list.add(new ChiTietSP(24, sanPhamList.get(10), nsxList.get(3), mauSacList.get(1), dongSPList.get(1), new Date(), "Sản Phẩm Cao Cấp", 950, 2700000, 2900000));
        list.add(new ChiTietSP(25, sanPhamList.get(6), nsxList.get(8), mauSacList.get(5), dongSPList.get(2), new Date(), "Sản Phẩm Xịn", 1400, 8800000, 9600000));
        list.add(new ChiTietSP(26, sanPhamList.get(2), nsxList.get(7), mauSacList.get(2), dongSPList.get(0), new Date(), "Sản Phẩm Đẹp", 300, 180000, 200000));
    }

    @Override
    public List<ChiTietSP> findAllByObject() {
        return list;
    }

    @Override
    public boolean save(ChiTietSP chiTietSP) {
        return list.add(chiTietSP);
    }

    @Override
    public void update(ChiTietSP chiTietSP) {
        for (int i = 0; i < list.size(); i++) {
            if (chiTietSP.getId() == list.get(i).getId()) {
                list.set(i, chiTietSP);
            }
        }
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public ChiTietSP findById(Object o) {
        return null;
    }

    @Override
    public List<ChiTietSP> findByName(String name) {
        return list.stream().filter(t -> t.getIdSP().getTen().toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChiTietSP> findPaing(int index) {
        int kichThuocTrang = 3;
        int batDau = (index - 1) * kichThuocTrang;
        int ketThuc = Math.min(batDau + kichThuocTrang, list.size());
        List<ChiTietSP> danhSachCon = list.subList(batDau, ketThuc);
        return danhSachCon;
    }

}
