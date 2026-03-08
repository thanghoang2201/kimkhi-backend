package com.example.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.backend.dto.request.SanPhamRequest;
import com.example.backend.dto.response.SanPhamResponse;
import com.example.backend.entity.DanhMucEntity;
import com.example.backend.entity.SanPhamEntity;
import com.example.backend.repository.DanhMucRepository;
import com.example.backend.repository.SanPhamRepository;
import com.example.backend.service.SanPhamService;

@Service
public class SanPhamImpl implements SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private DanhMucRepository danhMucRepository;

    // ================= PHÂN TRANG =================
    @Override
    public Page<SanPhamResponse> getAllSanPham(Integer page, Integer size) {

        Page<SanPhamEntity> sanPhamPage =
                sanPhamRepository.findAll(PageRequest.of(page, size));

        return sanPhamPage.map(this::convertToResponse);
    }

    // ================= LẤY TẤT CẢ =================
    @Override
    public List<SanPhamResponse> getAllSanPhamNoPaging() {

        return sanPhamRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // ================= TÌM THEO ID =================
    @Override
    public SanPhamResponse getSanPhamById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Không có id");
        }

        SanPhamEntity entity = sanPhamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));

        return convertToResponse(entity);
    }

    // ================= TẠO =================
    @Override
    public SanPhamResponse createSanPham(SanPhamRequest request) {

        if (request.getTenSanPham() == null || request.getTenSanPham().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống");
        }

        DanhMucEntity danhMuc = danhMucRepository.findById(request.getDanhMucId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục"));

        SanPhamEntity entity = SanPhamEntity.builder()
                .tenSanPham(request.getTenSanPham())
                .gia(request.getGia())
                .donViTinh(request.getDonViTinh())
                .moTa(request.getMoTa())
                .soLuongTon(request.getSoLuongTon())
                .trangThai(true)
                .danhMuc(danhMuc)
                .build();

        SanPhamEntity saved = sanPhamRepository.save(entity);

        return convertToResponse(saved);
    }

    // ================= SỬA =================
    @Override
    public SanPhamResponse editSanPham(Long id, SanPhamRequest request) {

        SanPhamEntity entity = sanPhamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));

        DanhMucEntity danhMuc = danhMucRepository.findById(request.getDanhMucId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục"));

        entity.setTenSanPham(request.getTenSanPham());
        entity.setGia(request.getGia());
        entity.setDonViTinh(request.getDonViTinh());
        entity.setMoTa(request.getMoTa());
        entity.setSoLuongTon(request.getSoLuongTon());
        entity.setDanhMuc(danhMuc);

        SanPhamEntity saved = sanPhamRepository.save(entity);

        return convertToResponse(saved);
    }

    // ================= XÓA MỀM =================
    @Override
    public void deleteSanPham(Long id) {

        SanPhamEntity entity = sanPhamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));

        entity.setTrangThai(false);

        sanPhamRepository.save(entity);
    }

    // ================= BẬT TẮT =================
    @Override
    public SanPhamResponse toggleTrangThai(Long id) {

        SanPhamEntity entity = sanPhamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));

        entity.setTrangThai(!entity.getTrangThai());

        SanPhamEntity saved = sanPhamRepository.save(entity);

        return convertToResponse(saved);
    }

    // ================= CONVERT =================
    private SanPhamResponse convertToResponse(SanPhamEntity entity) {

    return SanPhamResponse.builder()
            .id(entity.getId())
            .tenSanPham(entity.getTenSanPham())
            .gia(entity.getGia())
            .donViTinh(entity.getDonViTinh())
            .moTa(entity.getMoTa())
            .soLuongTon(entity.getSoLuongTon())
            .trangThai(entity.getTrangThai())
            .ngayTao(entity.getNgayTao())
            .danhMucId(entity.getDanhMuc().getId())
            .tenDanhMuc(entity.getDanhMuc().getTenDanhMuc())
            .duongDanAnh(
                    entity.getHinhAnh() != null
                            ? entity.getHinhAnh().getDuongDanAnh()
                            : null
            )
            .build();
}
}
