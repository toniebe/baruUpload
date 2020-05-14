package com.transaksi.upload.repository;

import com.transaksi.upload.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBFileRepository extends JpaRepository<DBFile, String> {
}
