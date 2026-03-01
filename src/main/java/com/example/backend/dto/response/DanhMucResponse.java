package com.example.backend.dto.response;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DanhMucResponse {
    private Long id;
    private String tenDanhMuc;
    private String moTa;
    private Boolean trangThai;
    private LocalDateTime ngayTao;
}
