package com.example.backend.dto.response;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ChiTietDonHangResponse {
    private Long id;
    private Long sanPhamId;
    private String tenSanPham;
    private Integer soLuong;
    private BigDecimal gia;
    private BigDecimal thanhTien;
}
