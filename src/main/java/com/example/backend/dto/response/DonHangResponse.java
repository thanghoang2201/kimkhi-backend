package com.example.backend.dto.response;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class DonHangResponse {
    private Long id;
    private String tenKhachHang;
    private String soDienThoai;
    private String diaChi;
    private BigDecimal tongTien;
    private String trangThai;
    private LocalDateTime ngayTao;

    private List<ChiTietDonHangResponse> chiTiet;
}
