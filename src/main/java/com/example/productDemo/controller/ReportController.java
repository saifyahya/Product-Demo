package com.example.productDemo.controller;


import com.example.productDemo.service.serviceimpl.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ReportController {

    @Autowired
    private ReportService reportService;


    @GetMapping("/report")
    public ResponseEntity getNutritionReport() throws JRException, SQLException, FileNotFoundException {
        ByteArrayOutputStream reportStream=reportService.generateReport();
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity(reportStream.toByteArray(),httpHeaders, HttpStatus.OK);
    }
}
