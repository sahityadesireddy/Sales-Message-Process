package com.sd.smp;

import java.util.Map;

import com.sd.smp.io.MessageWriter;
import com.sd.smp.model.FinalReport;
import com.sd.smp.model.IntervalReport;
import com.sd.smp.model.Product;
import com.sd.smp.model.Report;

public class ReportProcessor {

    private Report intervalReport;
    private Report finalReport;

    // List products
    private Map<String, Product> listProducts;
    private MessageWriter messageWriter;

    public ReportProcessor(MessageWriter messageWriter) {
        this.messageWriter = messageWriter;
        initalizeReports();
    }

    private void initalizeReports() {
        intervalReport = new IntervalReport();
        intervalReport.setOutputMessageWriter(this.messageWriter);
        finalReport = new FinalReport();
        finalReport.setOutputMessageWriter(this.messageWriter);
    }

    public void processIntervalReports(int count) {
       intervalReport.process(listProducts, count);
    }

    public void processFinalReport(int maxCount) {
        finalReport.process(listProducts, maxCount);
    }

    public Report getIntervalReport() {
        return intervalReport;
    }

    public void setIntervalReport(Report intervalReport) {
        this.intervalReport = intervalReport;
    }

    public Map<String, Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(Map<String, Product> listProducts) {
        this.listProducts = listProducts;
    }

}
