package com.transaksi.upload.repository;


import com.transaksi.upload.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi,Integer> {
}
