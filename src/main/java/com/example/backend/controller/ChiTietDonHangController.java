package com.example.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.example.backend.dto.response.ChiTietDonHangResponse;
import com.example.backend.service.ChiTietDonHangService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chi-tiet-don-hang")
@RequiredArgsConstructor
public class ChiTietDonHangController {

    private final ChiTietDonHangService chiTietService;

    @GetMapping("/{id}")
    public ChiTietDonHangResponse getById(@PathVariable Long id) {
        return chiTietService.getById(id);
    }
}