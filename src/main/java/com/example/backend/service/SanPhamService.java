package com.example.backend.service;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.backend.dto.request.SanPhamRequest;
import com.example.backend.dto.response.SanPhamResponse;
public interface SanPhamService {
    Page<SanPhamResponse> getAllSanPham(Integer page, Integer size);

    List<SanPhamResponse> getAllSanPhamNoPaging();

    SanPhamResponse getSanPhamById(Long id);

    SanPhamResponse createSanPham(SanPhamRequest request);

    SanPhamResponse editSanPham(Long id, SanPhamRequest request);

    void deleteSanPham(Long id);

    SanPhamResponse toggleTrangThai(Long id);
}
