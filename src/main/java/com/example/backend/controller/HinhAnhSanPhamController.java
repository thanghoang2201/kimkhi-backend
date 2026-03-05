package com.example.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.request.HinhAnhRequest;
import com.example.backend.dto.response.HinhAnhResponse;
import com.example.backend.service.HinhAnhSanPhamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/hinh-anh")
@RequiredArgsConstructor
public class HinhAnhSanPhamController {
    private final HinhAnhSanPhamService hinhAnhService;

    @PostMapping
    public HinhAnhResponse create(@RequestBody HinhAnhRequest request) {
        return hinhAnhService.create(request);
    }

    @GetMapping
    public List<HinhAnhResponse> getAll() {
        return hinhAnhService.getAll();
    }

    @GetMapping("/{id}")
    public HinhAnhResponse getById(@PathVariable Long id) {
        return hinhAnhService.getById(id);
    }

    @GetMapping("/san-pham/{sanPhamId}")
    public HinhAnhResponse getBySanPhamId(@PathVariable Long sanPhamId) {
        return hinhAnhService.getBySanPhamId(sanPhamId);
    }

    @PutMapping("/{id}")
    public HinhAnhResponse update(@PathVariable Long id,
                                  @RequestBody HinhAnhRequest request) {
        return hinhAnhService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        hinhAnhService.delete(id);
        return "Xóa hình ảnh thành công";
    }
}
