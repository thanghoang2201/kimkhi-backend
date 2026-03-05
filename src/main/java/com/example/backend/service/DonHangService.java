package com.example.backend.service;

import java.util.List;

import com.example.backend.dto.request.DonHangRequest;
import com.example.backend.dto.response.DonHangResponse;

public interface DonHangService {

    DonHangResponse create(DonHangRequest request);

    List<DonHangResponse> getAll();

    DonHangResponse getById(Long id);

    DonHangResponse update(Long id, DonHangRequest request);

    void deleteDonHang(Long id);

    DonHangResponse updateTrangThai(Long id, String trangThai);
}