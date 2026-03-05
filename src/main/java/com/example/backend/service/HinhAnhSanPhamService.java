package com.example.backend.service;

import java.util.List;

import com.example.backend.dto.request.HinhAnhRequest;
import com.example.backend.dto.response.HinhAnhResponse;

public interface HinhAnhSanPhamService {
    HinhAnhResponse create(HinhAnhRequest request);

    List<HinhAnhResponse> getAll();

    HinhAnhResponse getById(Long id);

    HinhAnhResponse getBySanPhamId(Long sanPhamId);

    HinhAnhResponse update(Long id, HinhAnhRequest request);

    void delete(Long id);
}
