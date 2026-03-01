package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hinh_anh_san_pham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HinhAnhSanPhamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String duongDanAnh;

    private LocalDateTime ngayTao = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "id_san_pham")
    private SanPhamEntity sanPham;
}
