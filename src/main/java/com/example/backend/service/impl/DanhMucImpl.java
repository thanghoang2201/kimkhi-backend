package com.example.backend.service.impl;

// import java.util.List;
// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.backend.dto.request.DanhMucRequest;
import com.example.backend.dto.response.DanhMucResponse;
import com.example.backend.entity.DanhMucEntity;
import com.example.backend.repository.DanhMucRepository;
import com.example.backend.service.DanhMucService;

@Service
public class DanhMucImpl implements DanhMucService {
    @Autowired
    private DanhMucRepository danhMucRepository;

    // ================= PHÂN TRANG =================
    @Override
    public Page<DanhMucResponse> getAllDanhMuc(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<DanhMucEntity> danhMucPage = danhMucRepository.findAll(pageable);

        return danhMucPage.map(dm -> DanhMucResponse.builder()
                .id(dm.getId())
                .tenDanhMuc(dm.getTenDanhMuc())
                .moTa(dm.getMoTa())
                .trangThai(dm.getTrangThai())
                .ngayTao(dm.getNgayTao())
                .build());
    }

    // ================= LẤY THEO TRẠNG THÁI =================
    // @Override
    // public List<DanhMucResponse> getDanhMucByTrangThai(Boolean trangThai) {

    //     if (trangThai == null) {
    //         throw new IllegalArgumentException("Trạng thái không được null");
    //     }

    //     List<DanhMucEntity> danhMucs =
    //             trangThai
    //                     ? danhMucRepository.findByTrangThaiTrue()
    //                     : danhMucRepository.findAll()
    //                     .stream()
    //                     .filter(dm -> !dm.getTrangThai())
    //                     .collect(Collectors.toList());

    //     return danhMucs.stream()
    //             .map(dm -> DanhMucResponse.builder()
    //                     .id(dm.getId())
    //                     .tenDanhMuc(dm.getTenDanhMuc())
    //                     .moTa(dm.getMoTa())
    //                     .trangThai(dm.getTrangThai())
    //                     .ngayTao(dm.getNgayTao())
    //                     .build())
    //             .collect(Collectors.toList());
    // }

    // ================= TẠO =================
    @Override
public DanhMucResponse createDanhMuc(DanhMucRequest request) {

    if (request.getTenDanhMuc() == null || request.getTenDanhMuc().isEmpty()) {
        throw new IllegalArgumentException("Tên danh mục không được để trống");
    }

    if (danhMucRepository.existsByTenDanhMuc(request.getTenDanhMuc())) {
        throw new IllegalArgumentException("Danh mục đã tồn tại");
    }

    DanhMucEntity entity = DanhMucEntity.builder()
            .tenDanhMuc(request.getTenDanhMuc())
            .moTa(request.getMoTa())
            .trangThai(true)
            .ngayTao(java.time.LocalDateTime.now()) // ✅ thêm dòng này
            .build();

    DanhMucEntity saved = danhMucRepository.save(entity);

    return DanhMucResponse.builder()
            .id(saved.getId())
            .tenDanhMuc(saved.getTenDanhMuc())
            .moTa(saved.getMoTa())
            .trangThai(saved.getTrangThai())
            .ngayTao(saved.getNgayTao())
            .build();
}

    // ================= SỬA =================
    @Override
    public DanhMucResponse editDanhMuc(Long id, DanhMucRequest request) {

        if (id == null) {
            throw new IllegalArgumentException("Không có id");
        }

        DanhMucEntity danhMuc = danhMucRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục"));

        danhMuc.setTenDanhMuc(request.getTenDanhMuc());
        danhMuc.setMoTa(request.getMoTa());
        danhMuc.setTrangThai(request.getTrangThai());

        DanhMucEntity saved = danhMucRepository.save(danhMuc);

        return DanhMucResponse.builder()
                .id(saved.getId())
                .tenDanhMuc(saved.getTenDanhMuc())
                .moTa(saved.getMoTa())
                .trangThai(saved.getTrangThai())
                .ngayTao(saved.getNgayTao())
                .build();
    }

    // ================= XÓA (SOFT DELETE) =================
    @Override
    public void deleteDanhMuc(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Không có id");
        }

        DanhMucEntity danhMuc = danhMucRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục"));

        if (!danhMuc.getTrangThai()) {
            throw new IllegalArgumentException("Danh mục đã bị xóa");
        }

        danhMuc.setTrangThai(false);
        danhMucRepository.save(danhMuc);
    }

    // ================= TÌM THEO ID =================
    @Override
public DanhMucResponse getDanhMucById(Long id) {

    if (id == null) {
        throw new IllegalArgumentException("Không có id");
    }

    DanhMucEntity danhMuc = danhMucRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục"));

    return DanhMucResponse.builder()
            .id(danhMuc.getId())
            .tenDanhMuc(danhMuc.getTenDanhMuc())
            .moTa(danhMuc.getMoTa())
            .trangThai(danhMuc.getTrangThai())
            .ngayTao(danhMuc.getNgayTao())
            .build();
}
}
