package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.request.SanPhamRequest;
import com.example.backend.dto.response.SanPhamResponse;
import com.example.backend.service.SanPhamService;

@RestController
@RequestMapping("/api/san-pham")
public class SanPhamController {
     @Autowired
    private SanPhamService sanPhamService;

    // ================= PHÂN TRANG =================
    @GetMapping
    public ResponseEntity<Page<SanPhamResponse>> getAllSanPham(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {

        return ResponseEntity.ok(sanPhamService.getAllSanPham(page, size));
    }

    // ================= LẤY TẤT CẢ =================
    @GetMapping("/all")
    public ResponseEntity<List<SanPhamResponse>> getAllSanPhamNoPaging() {

        return ResponseEntity.ok(sanPhamService.getAllSanPhamNoPaging());
    }

    // ================= TÌM THEO ID =================
    @GetMapping("/{id}")
    public ResponseEntity<SanPhamResponse> getSanPhamById(@PathVariable Long id) {

        return ResponseEntity.ok(sanPhamService.getSanPhamById(id));
    }

    // ================= TẠO =================
    @PostMapping
    public ResponseEntity<SanPhamResponse> createSanPham(
            @RequestBody SanPhamRequest request) {

        return ResponseEntity.ok(sanPhamService.createSanPham(request));
    }

    // ================= SỬA =================
    @PutMapping("/{id}")
    public ResponseEntity<SanPhamResponse> editSanPham(
            @PathVariable Long id,
            @RequestBody SanPhamRequest request) {

        return ResponseEntity.ok(sanPhamService.editSanPham(id, request));
    }

    // ================= XÓA MỀM =================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSanPham(@PathVariable Long id) {

        sanPhamService.deleteSanPham(id);
        return ResponseEntity.ok("Xóa sản phẩm thành công");
    }

    // ================= BẬT TẮT =================
    @PutMapping("/toggle/{id}")
    public ResponseEntity<SanPhamResponse> toggleTrangThai(@PathVariable Long id) {

        return ResponseEntity.ok(sanPhamService.toggleTrangThai(id));
    }
}
