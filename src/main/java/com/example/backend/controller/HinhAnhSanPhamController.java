package com.example.backend.controller;

import com.example.backend.dto.response.HinhAnhResponse;
import com.example.backend.service.impl.HinhAnhSanPhamServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/hinh-anh")
@RequiredArgsConstructor
public class HinhAnhSanPhamController {

    private final HinhAnhSanPhamServiceImpl service;

    @PostMapping("/upload/{sanPhamId}")
    public HinhAnhResponse uploadImage(
            @PathVariable Long sanPhamId,
            @RequestParam("file") MultipartFile file) {

        return service.upload(sanPhamId, file);
    }
}