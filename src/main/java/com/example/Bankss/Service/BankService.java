package com.example.Bankss.Service;

import com.example.Bankss.Entity.BankEntity;
import com.example.Bankss.Respository.BankRepo;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.element.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.Document;


@Service
public class BankService {
    @Autowired
    public BankRepo bankRepo;
    public BankEntity add(BankEntity bankEntity)
    {
        return bankRepo.save(bankEntity);
    }
    public List<BankEntity> display()
    {
        return  bankRepo.findAll();
    }
    public BankEntity showing(int id)
    {
        return bankRepo.findById(id).orElse(null);
    }
    public void delete(int id)
    {
        bankRepo.deleteById(id);
    }
    public BankEntity update(int id,BankEntity bankEntity)
    {
        BankEntity bank=bankRepo.findById(id).orElse(null);
        if(bank!=null)
        {
            bank.setName(bankEntity.getName());
            bank.setAmount(bankEntity.getAmount());
        }
        return bankRepo.save(bank);
    }

//    public byte[] exportStudentsToPdf(List<BankEntity> bankEntities) {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        PdfWriter writer = new PdfWriter(out);
//        PdfDocument pdfDoc = new PdfDocument(writer);
//        Document document = new Document(pdfDoc);
//
//        // Title
//        document.add(new Paragraph("Bank Report")
//                .setBold()
//                .setFontSize(16)
//                .setTextAlignment(TextAlignment.CENTER)
//                .setMarginBottom(20));
//
//        // Table with 3 columns (ID, Name, Amount)
//        Table table = new Table(3);
//        table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
//        table.addHeaderCell(new Cell().add(new Paragraph("Name").setBold()));
//        table.addHeaderCell(new Cell().add(new Paragraph("Amount").setBold()));
//
//        // Data rows
//        for (BankEntity bank : bankEntities) {
//            table.addCell(String.valueOf(bank.getId()));
//            table.addCell(bank.getName());
//            table.addCell(String.valueOf(bank.getAmount()));
//        }
//
//        document.add(table);
//        document.close();
//
//        return out.toByteArray();
//    }
    public List<BankEntity> maxAmount(){
      List<BankEntity> amount=  bankRepo.findTopByOrderByAmountDesc();
        return amount;


    }
    public List<BankEntity> minAmount(){
        List<BankEntity> amount=  bankRepo.findTopByOrderByAmountAsc();
        return bankRepo.findTopByOrderByAmountAsc();
    }
    public List<BankEntity> orderby(){
        List<BankEntity> amount=  bankRepo.findAllByOrderByAmountDesc();
        return amount;
    }
     public int factorial(int n)
     {
         int fact=1;
         for(int i=1;i<=n;i++)
         {
             fact=fact*i;
         }
         return fact;
     }
     public int facts(int n)
     {
         int fact=1;
         for(int i=1;i<=n;i++)
         {
             fact=fact*i;
         }
         return fact;
     }
    public byte[] exportStudentsToPdf(List<BankEntity> bankEntities) {/*pdf with water mark*/
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        try {
            // Load the image
            ImageData watermarkImage = ImageDataFactory.create("C:\\Bankss\\src\\main\\resources\\releaseowl.watermark.png"); // <- Change to your actual path
            Image watermark = new Image(watermarkImage);
            watermark.setFixedPosition(200, 400); // Adjust position as needed
            watermark.setOpacity(0.2f); // Faint watermark

            int numberOfPages = pdfDoc.getNumberOfPages();
            PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
            canvas.addImageAt(watermarkImage, 200, 400, false);

            // Title
            document.add(new Paragraph("Bank Report")
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20));

            // Table with 3 columns
            Table table = new Table(3);
            table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Name").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Amount").setBold()));

            // Data rows
            for (BankEntity bank : bankEntities) {
                table.addCell(String.valueOf(bank.getId()));
                table.addCell(bank.getName());
                table.addCell(String.valueOf(bank.getAmount()));
            }

            document.add(table);
        } catch (IOException e) {
            e.printStackTrace(); // Handle properly in production
        }

        document.close();
        return out.toByteArray();
    }

}
