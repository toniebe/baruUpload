package com.transaksi.upload.repository;


import com.transaksi.upload.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi,Integer> {
    List<Transaksi> findByUsername(String username);
}
