package com.example.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.backend.dto.request.HinhAnhRequest;
import com.example.backend.dto.response.HinhAnhResponse;
import com.example.backend.entity.HinhAnhSanPhamEntity;
import com.example.backend.entity.SanPhamEntity;
import com.example.backend.repository.HinhAnhSanPhamRepository;
import com.example.backend.repository.SanPhamRepository;
import com.example.backend.service.HinhAnhSanPhamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HinhAnhSanPhamServiceImpl implements HinhAnhSanPhamService {
    private final HinhAnhSanPhamRepository hinhAnhRepository;
    private final SanPhamRepository sanPhamRepository;

    private HinhAnhResponse mapToResponse(HinhAnhSanPhamEntity entity) {
        return HinhAnhResponse.builder()
                .id(entity.getId())
                .sanPhamId(entity.getSanPham().getId())
                .duongDanAnh(entity.getDuongDanAnh())
                .ngayTao(entity.getNgayTao())
                .build();
    }

    @Override
    public HinhAnhResponse create(HinhAnhRequest request) {

        SanPhamEntity sanPham = sanPhamRepository.findById(request.getSanPhamId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        HinhAnhSanPhamEntity entity = HinhAnhSanPhamEntity.builder()
                .duongDanAnh(request.getDuongDanAnh())
                .sanPham(sanPham)
                .build();

        return mapToResponse(hinhAnhRepository.save(entity));
    }

    @Override
    public List<HinhAnhResponse> getAll() {
        return hinhAnhRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HinhAnhResponse getById(Long id) {

        HinhAnhSanPhamEntity entity = hinhAnhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hình ảnh"));

        return mapToResponse(entity);
    }

    @Override
    public HinhAnhResponse getBySanPhamId(Long sanPhamId) {

        HinhAnhSanPhamEntity entity = hinhAnhRepository.findBySanPhamId(sanPhamId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hình ảnh"));

        return mapToResponse(entity);
    }

    @Override
    public HinhAnhResponse update(Long id, HinhAnhRequest request) {

        HinhAnhSanPhamEntity entity = hinhAnhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hình ảnh"));

        SanPhamEntity sanPham = sanPhamRepository.findById(request.getSanPhamId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        entity.setDuongDanAnh(request.getDuongDanAnh());
        entity.setSanPham(sanPham);

        return mapToResponse(hinhAnhRepository.save(entity));
    }

    @Override
    public void delete(Long id) {

        if (!hinhAnhRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy hình ảnh");
        }

        hinhAnhRepository.deleteById(id);
    }
}
