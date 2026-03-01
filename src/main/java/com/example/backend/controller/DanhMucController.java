package com.example.backend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.dto.request.DanhMucRequest;
import com.example.backend.dto.response.DanhMucResponse;
import com.example.backend.service.DanhMucService;

@RestController
@RequestMapping("/api/danh-muc")
public class DanhMucController {
    @Autowired
    private DanhMucService danhMucService;

    // ================= PHÂN TRANG =================
    @GetMapping
    public ResponseEntity<Page<DanhMucResponse>> getAllDanhMuc(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {

        return ResponseEntity.ok(danhMucService.getAllDanhMuc(page, size));
    }

    // ================= LẤY TẤT CẢ =================
    @GetMapping("/all")
    public ResponseEntity<List<DanhMucResponse>> getAllDanhMucNoPaging() {
        return ResponseEntity.ok(
                danhMucService.getAllDanhMuc(0, Integer.MAX_VALUE).getContent());
    }

    // ================= TÌM THEO ID =================
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucResponse> getDanhMucById(@PathVariable Long id) {
        return ResponseEntity.ok(danhMucService.getDanhMucById(id));
    }

    // ================= TÌM THEO TRẠNG THÁI =================
    // @GetMapping("/trangThai/{trangThai}")
    // public ResponseEntity<List<DanhMucResponse>> getDanhMucByTrangThai(
    //         @PathVariable Boolean trangThai) {

    //     return ResponseEntity.ok(danhMucService.getDanhMucByTrangThai(trangThai));
    // }

    // ================= TẠO =================
    @PostMapping
    public ResponseEntity<DanhMucResponse> createDanhMuc(
            @RequestBody DanhMucRequest request) {

        return ResponseEntity.ok(danhMucService.createDanhMuc(request));
    }

    // ================= SỬA =================
    @PutMapping("/{id}")
    public ResponseEntity<DanhMucResponse> editDanhMuc(
            @PathVariable Long id,
            @RequestBody DanhMucRequest request) {

        return ResponseEntity.ok(danhMucService.editDanhMuc(id, request));
    }

    // ================= XÓA =================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDanhMuc(@PathVariable Long id) {

        danhMucService.deleteDanhMuc(id);
        return ResponseEntity.ok("Xóa danh mục thành công");
    }
}
