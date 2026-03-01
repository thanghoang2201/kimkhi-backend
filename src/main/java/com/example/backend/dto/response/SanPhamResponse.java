package com.example.backend.dto.response;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SanPhamResponse {
    private Long id;
    private String tenSanPham;
    private BigDecimal gia;
    private String donViTinh;
    private String moTa;
    private Integer soLuongTon;
    private Boolean trangThai;
    private LocalDateTime ngayTao;

    private Long danhMucId;
    private String tenDanhMuc;

    private String duongDanAnh;
}
