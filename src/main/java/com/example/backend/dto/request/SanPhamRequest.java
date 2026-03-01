package com.example.backend.dto.request;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
public class SanPhamRequest {
    private String tenSanPham;
    private Long danhMucId;
    private BigDecimal gia;
    private String donViTinh;
    private String moTa;
    private Integer soLuongTon;
    private Boolean trangThai;
    private String duongDanAnh;
}
