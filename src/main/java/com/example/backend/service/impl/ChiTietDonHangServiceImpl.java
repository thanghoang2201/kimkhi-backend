package com.example.backend.service.impl;

import org.springframework.stereotype.Service;

import com.example.backend.dto.response.ChiTietDonHangResponse;
import com.example.backend.entity.ChiTietDonHangEntity;
import com.example.backend.repository.ChiTietDonHangRepository;
import com.example.backend.service.ChiTietDonHangService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChiTietDonHangServiceImpl implements ChiTietDonHangService {

    private final ChiTietDonHangRepository chiTietRepository;

    private ChiTietDonHangResponse mapToResponse(ChiTietDonHangEntity entity) {

        return ChiTietDonHangResponse.builder()
                .id(entity.getId())
                .sanPhamId(entity.getSanPham().getId())
                .tenSanPham(entity.getSanPham().getTenSanPham())
                .soLuong(entity.getSoLuong())
                .gia(entity.getGia())
                .thanhTien(entity.getGia().multiply(
                        new java.math.BigDecimal(entity.getSoLuong())))
                .build();
    }

    @Override
    public ChiTietDonHangResponse getById(Long id) {

        ChiTietDonHangEntity entity = chiTietRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết đơn hàng"));

        return mapToResponse(entity);
    }
}