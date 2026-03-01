package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "san_pham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPhamEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenSanPham;

    private BigDecimal gia;

    private String donViTinh;

    private String moTa;

    private Integer soLuongTon = 0;

    private Boolean trangThai = true;

    private LocalDateTime ngayTao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    @JsonIgnore
    private DanhMucEntity danhMuc;

    @OneToOne(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private HinhAnhSanPhamEntity hinhAnh;
}
