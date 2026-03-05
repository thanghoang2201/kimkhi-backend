package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "don_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonHangEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenKhachHang;

    private String soDienThoai;

    private String diaChi;

    private BigDecimal tongTien;

    private String trangThai = "CHO_XU_LY";
    @Builder.Default
    private LocalDateTime ngayTao = LocalDateTime.now();

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private List<ChiTietDonHangEntity> chiTietDonHangs;
}
