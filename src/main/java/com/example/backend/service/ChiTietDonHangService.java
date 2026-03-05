package com.example.backend.service;

import com.example.backend.dto.response.ChiTietDonHangResponse;

public interface ChiTietDonHangService {

    ChiTietDonHangResponse getById(Long id);

}