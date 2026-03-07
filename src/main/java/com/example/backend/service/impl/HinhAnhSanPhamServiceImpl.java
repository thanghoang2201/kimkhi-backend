package com.example.backend.service.impl;

import com.example.backend.dto.request.HinhAnhRequest;
import com.example.backend.dto.response.HinhAnhResponse;
import com.example.backend.entity.HinhAnhSanPhamEntity;
import com.example.backend.entity.SanPhamEntity;
import com.example.backend.repository.HinhAnhSanPhamRepository;
import com.example.backend.repository.SanPhamRepository;
import com.example.backend.service.HinhAnhSanPhamService;
import com.example.backend.service.ImageUploadService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class HinhAnhSanPhamServiceImpl implements HinhAnhSanPhamService {

    private final HinhAnhSanPhamRepository repository;
    private final SanPhamRepository sanPhamRepository;
    private final ImageUploadService imageUploadService;

    public HinhAnhResponse upload(Long sanPhamId, MultipartFile file) {

        SanPhamEntity sanPham = sanPhamRepository.findById(sanPhamId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        String imageUrl = imageUploadService.uploadImage(file);

        HinhAnhSanPhamEntity entity = HinhAnhSanPhamEntity.builder()
                .sanPham(sanPham)
                .duongDanAnh(imageUrl)
                .build();

        repository.save(entity);

        return HinhAnhResponse.builder()
                .id(entity.getId())
                .sanPhamId(sanPhamId)
                .duongDanAnh(imageUrl)
                .ngayTao(entity.getNgayTao())
                .build();
    }

}