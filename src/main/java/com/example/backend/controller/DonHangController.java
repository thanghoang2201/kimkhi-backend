package com.example.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.backend.dto.request.DonHangRequest;
import com.example.backend.dto.response.DonHangResponse;
import com.example.backend.service.DonHangService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/don-hang")
@RequiredArgsConstructor
public class DonHangController {

    private final DonHangService donHangService;

    @PostMapping
    public DonHangResponse create(@RequestBody DonHangRequest request) {
        return donHangService.create(request);
    }

    @GetMapping
    public List<DonHangResponse> getAll() {
        return donHangService.getAll();
    }

    @GetMapping("/{id}")
    public DonHangResponse getById(@PathVariable Long id) {
        return donHangService.getById(id);
    }

    @PutMapping("/{id}")
    public DonHangResponse update(@PathVariable Long id,
                                  @RequestBody DonHangRequest request) {
        return donHangService.update(id, request);
    }

    @DeleteMapping("/{id}")
public String deleteDonHang(@PathVariable Long id) {

    donHangService.deleteDonHang(id);

    return "Đã hủy đơn hàng thành công";
}

    @PutMapping("/{id}/trang-thai")
public DonHangResponse updateTrangThai(
        @PathVariable Long id,
        @RequestBody Map<String, String> request) {

    return donHangService.updateTrangThai(id, request.get("trangThai"));
}
}