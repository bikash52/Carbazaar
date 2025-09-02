package com.realProject.controller;

import com.realProject.service.BulkBrandExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/uploadBulkBrand")
public class BulkBrandExcelController {

    @Autowired
    private BulkBrandExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBrands(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid Excel file.");
        }

        excelService.saveBrandsFromExcel(file);
        return ResponseEntity.ok("Brands uploaded successfully!");
    }
}
