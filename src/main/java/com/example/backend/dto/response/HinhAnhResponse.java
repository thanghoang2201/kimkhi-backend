package com.example.backend.dto.response;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class HinhAnhResponse {
    private Long id;
    private Long sanPhamId;
    private String duongDanAnh;
    private LocalDateTime ngayTao;
}
