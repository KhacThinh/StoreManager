package model.repository;

import model.entity.MauSac;

import java.util.List;

public interface MauSacRepository {

    public List<MauSac> findAllByObject();

    public boolean save(MauSac e);

    public void update(MauSac e);

    public boolean delete(Object o);

    public MauSac findById(Object o);

    public List<MauSac> findByName(String name);
}
