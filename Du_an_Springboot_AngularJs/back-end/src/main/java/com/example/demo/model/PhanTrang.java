package com.example.demo.model;

import com.example.demo.entity.ChiTietSP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhanTrang {
    private Integer trangHienTai;
    private Integer tongSoTrang;
    private List<ChiTietSP> chiTietSPList;
}
