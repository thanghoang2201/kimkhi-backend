package com.example.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.SanPhamEntity;

import java.util.List;
public interface SanPhamRepository extends JpaRepository<SanPhamEntity, Long>  {
    List<SanPhamEntity> findByTrangThaiTrue();

    List<SanPhamEntity> findByDanhMucId(Long danhMucId);

    List<SanPhamEntity> findByTenSanPhamContainingIgnoreCase(String keyword);

    List<SanPhamEntity> findByTrangThaiTrueAndDanhMucId(Long danhMucId);

    List<SanPhamEntity> findByTrangThaiTrueAndTenSanPhamContainingIgnoreCase(String keyword);
}
