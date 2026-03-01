package com.example.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.DanhMucEntity;

import java.util.List;
public interface DanhMucRepository extends JpaRepository<DanhMucEntity, Long> {
    List<DanhMucEntity> findByTrangThaiTrue();

    boolean existsByTenDanhMuc(String tenDanhMuc);
}
