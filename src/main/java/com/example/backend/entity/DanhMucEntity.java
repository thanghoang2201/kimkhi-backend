package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "danh_muc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DanhMucEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_danh_muc", nullable = false)
    private String tenDanhMuc;

    private String moTa;

    @Builder.Default
    private Boolean trangThai = true;

    @Column(name = "ngay_tao", updatable = false)
    private LocalDateTime ngayTao;

    @OneToMany(mappedBy = "danhMuc", cascade = CascadeType.ALL)
    private List<SanPhamEntity> sanPhams;

    // 🔥 Tự động chạy trước khi insert vào DB
    @PrePersist
    public void prePersist() {
        if (this.trangThai == null) {
            this.trangThai = true;
        }
        this.ngayTao = LocalDateTime.now();
    }
}