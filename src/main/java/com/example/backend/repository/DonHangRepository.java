package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.DonHangEntity;

import java.util.List;
public interface DonHangRepository extends JpaRepository<DonHangEntity, Long> {
    List<DonHangEntity> findByTrangThai(String trangThai);

    List<DonHangEntity> findBySoDienThoai(String soDienThoai);

    List<DonHangEntity> findByTenKhachHangContainingIgnoreCase(String keyword);
}
