package com.example.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.HinhAnhSanPhamEntity;

import java.util.Optional;
public interface HinhAnhSanPhamRepository extends JpaRepository<HinhAnhSanPhamEntity, Long>{
    Optional<HinhAnhSanPhamEntity> findBySanPhamId(Long sanPhamId);

    void deleteBySanPhamId(Long sanPhamId);
}
