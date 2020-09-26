/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pawnshop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.BasicConfigurator;

/**
 *
 */
public class Report {
    public void generateReport(String query, String filePath){
        BasicConfigurator.configure();
        
        try{
            InputStream input = new FileInputStream(new File(filePath));

            JRDesignQuery jrquery = new JRDesignQuery();
            jrquery.setText(query);

            JasperDesign jdesign = JRXmlLoader.load(input);

            jdesign.setQuery(jrquery);
            JasperReport jreport = JasperCompileManager.compileReport(jdesign);

            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, DBConnection.getConnection());
            File pdf = File.createTempFile("D\\aa", ".pdf");
            JasperExportManager.exportReportToPdfStream(jprint, new FileOutputStream(pdf));

            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setVisible(true);
        } catch(IOException | JRException e){
            e.printStackTrace();
        }
    }
}
