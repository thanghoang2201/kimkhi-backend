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

    private Boolean trangThai = true;

    private LocalDateTime ngayTao = LocalDateTime.now();

    @OneToMany(mappedBy = "danhMuc", cascade = CascadeType.ALL)
    private List<SanPhamEntity> sanPhams;
}
