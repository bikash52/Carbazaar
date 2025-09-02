package com.realProject.service;

import com.realProject.entity.Car.Brand;
import com.realProject.repository.CarRelatedRepo.BrandRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BulkBrandExcelService {
    @Autowired
    private BrandRepository brandRepository;

    public void saveBrandsFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);  // Assuming data is in the first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            List<Brand> brands = new ArrayList<>();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Brand brand = new Brand();

                // Read "name" column (assuming it's in the first column)
                Cell nameCell = row.getCell(0);
                if (nameCell != null) {
                    brand.setName(nameCell.getStringCellValue());
                }

                brands.add(brand);
            }

            brandRepository.saveAll(brands);
            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException("Error processing Excel file: " + e.getMessage());
        }
    }
}
