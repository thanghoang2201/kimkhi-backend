package com.example.backend.dto.request;
import lombok.*;
import java.util.List;

@Getter
@Setter
public class DonHangRequest {
    private String tenKhachHang;
    private String soDienThoai;
    private String diaChi;

    private List<ChiTietDonHangRequest> chiTiet;

    @Getter
    @Setter
    public static class ChiTietDonHangRequest {
        private Long sanPhamId;
        private Integer soLuong;
    }
}
