package com.example.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.backend.dto.request.DanhMucRequest;
import com.example.backend.dto.response.DanhMucResponse;

public interface DanhMucService {
    // Phân trang
    Page<DanhMucResponse> getAllDanhMuc(Integer page, Integer size);

    // Lấy theo trạng thái
    // List<DanhMucResponse> getDanhMucByTrangThai(Boolean trangThai);

    // Tạo mới
    DanhMucResponse createDanhMuc(DanhMucRequest request);

    // Sửa
    DanhMucResponse editDanhMuc(Long id, DanhMucRequest request);

    // Xóa (soft delete)
    void deleteDanhMuc(Long id);
    //Tìm theo id
    DanhMucResponse getDanhMucById(Long id);

    public DanhMucResponse toggleTrangThai(Long id);
}
