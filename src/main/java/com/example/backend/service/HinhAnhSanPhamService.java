package com.example.backend.service;



import org.springframework.web.multipart.MultipartFile;

import com.example.backend.dto.response.HinhAnhResponse;

public interface HinhAnhSanPhamService {

    public HinhAnhResponse upload(Long sanPhamId, MultipartFile file);

}