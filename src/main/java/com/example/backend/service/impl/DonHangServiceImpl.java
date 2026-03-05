package com.example.backend.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.backend.dto.request.DonHangRequest;
import com.example.backend.dto.response.DonHangResponse;
import com.example.backend.dto.response.ChiTietDonHangResponse;
import com.example.backend.entity.ChiTietDonHangEntity;
import com.example.backend.entity.DonHangEntity;
import com.example.backend.entity.SanPhamEntity;
import com.example.backend.repository.DonHangRepository;
import com.example.backend.repository.SanPhamRepository;
import com.example.backend.service.DonHangService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonHangServiceImpl implements DonHangService {

    private final DonHangRepository donHangRepository;
    private final SanPhamRepository sanPhamRepository;

    private DonHangResponse mapToResponse(DonHangEntity entity) {

        List<ChiTietDonHangResponse> chiTiet = entity.getChiTietDonHangs()
                .stream()
                .map(ct -> ChiTietDonHangResponse.builder()
                        .id(ct.getId())
                        .sanPhamId(ct.getSanPham().getId())
                        .tenSanPham(ct.getSanPham().getTenSanPham())
                        .soLuong(ct.getSoLuong())
                        .gia(ct.getGia())
                        .thanhTien(ct.getGia().multiply(BigDecimal.valueOf(ct.getSoLuong())))
                        .build())
                .collect(Collectors.toList());

        return DonHangResponse.builder()
                .id(entity.getId())
                .tenKhachHang(entity.getTenKhachHang())
                .soDienThoai(entity.getSoDienThoai())
                .diaChi(entity.getDiaChi())
                .tongTien(entity.getTongTien())
                .trangThai(entity.getTrangThai())
                .ngayTao(entity.getNgayTao())
                .chiTiet(chiTiet)
                .build();
    }

    @Override
    public DonHangResponse create(DonHangRequest request) {

        DonHangEntity donHang = DonHangEntity.builder()
                .tenKhachHang(request.getTenKhachHang())
                .soDienThoai(request.getSoDienThoai())
                .diaChi(request.getDiaChi())
                .ngayTao(LocalDateTime.now())
                .trangThai("CHO_XU_LY")
                .build();

        List<ChiTietDonHangEntity> chiTietList = request.getChiTiet()
                .stream()
                .map(item -> {

                    SanPhamEntity sanPham = sanPhamRepository.findById(item.getSanPhamId())
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

                    return ChiTietDonHangEntity.builder()
                            .donHang(donHang)
                            .sanPham(sanPham)
                            .soLuong(item.getSoLuong())
                            .gia(sanPham.getGia())
                            .build();
                })
                .collect(Collectors.toList());

        donHang.setChiTietDonHangs(chiTietList);

        BigDecimal tongTien = chiTietList.stream()
                .map(ct -> ct.getGia().multiply(BigDecimal.valueOf(ct.getSoLuong())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        donHang.setTongTien(tongTien);

        return mapToResponse(donHangRepository.save(donHang));
    }

    @Override
    public List<DonHangResponse> getAll() {

        return donHangRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DonHangResponse getById(Long id) {

        DonHangEntity entity = donHangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        return mapToResponse(entity);
    }

    @Override
    public DonHangResponse update(Long id, DonHangRequest request) {

        DonHangEntity entity = donHangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        entity.setTenKhachHang(request.getTenKhachHang());
        entity.setSoDienThoai(request.getSoDienThoai());
        entity.setDiaChi(request.getDiaChi());

        return mapToResponse(donHangRepository.save(entity));
    }

    @Override
    public void delete(Long id) {

        if (!donHangRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy đơn hàng");
        }

        donHangRepository.deleteById(id);
    }

    @Override
    public DonHangResponse updateTrangThai(Long id, String trangThai) {

        DonHangEntity entity = donHangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        entity.setTrangThai(trangThai);

        return mapToResponse(donHangRepository.save(entity));
    }
}