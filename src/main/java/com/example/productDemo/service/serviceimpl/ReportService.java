package com.example.productDemo.service.serviceimpl;

import com.example.productDemo.repository.ProductRepository;
import com.example.productDemo.service.IProductService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReportService {
    private DataSource dataSource;
    private ProductRepository productRepository;

    public ByteArrayOutputStream generateReport() throws JRException, SQLException, FileNotFoundException {
        String filePath = "src/main/resources/reports/products.jrxml";

        JasperReport report = JasperCompileManager.compileReport(filePath);

        Connection connection = dataSource.getConnection();
        Map<String, Object> map = new HashMap<>();
        String logoPath="src/main/resources/images/store_logo.png";
        map.put("logo",logoPath);
        map.put("totalProducts",(int)getAllProductsCount());
        JasperPrint print = JasperFillManager.fillReport(report, map, connection);
        JasperExportManager.exportReportToPdfFile(print,"src/main/resources/reports/products.pdf");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCompressed(true);
        exporter.setConfiguration(configuration);
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.exportReport();

        if (connection != null) {
            connection.close();
        }

        return byteArrayOutputStream;
    }

    private long getAllProductsCount(){
        long totalOrdersCount =  productRepository.count();
        System.out.println(totalOrdersCount);
        return totalOrdersCount;
    }
}
