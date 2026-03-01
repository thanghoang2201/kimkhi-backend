package com.example.backend.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "chi_tiet_don_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietDonHangEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer soLuong;

    private BigDecimal gia;

    @ManyToOne
    @JoinColumn(name = "id_don_hang")
    @JsonIgnore
    private DonHangEntity donHang;

    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    private SanPhamEntity sanPham;
}
