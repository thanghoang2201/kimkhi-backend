package com.example.backend.dto.request;
import lombok.*;

@Getter
@Setter
public class DanhMucRequest {
    private String tenDanhMuc;
    private String moTa;
    private Boolean trangThai;
}
