package com.example.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.ChiTietDonHangEntity;

import java.util.List;

public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHangEntity, Long>{
    List<ChiTietDonHangEntity> findByDonHangId(Long donHangId);

    void deleteByDonHangId(Long donHangId);
}
