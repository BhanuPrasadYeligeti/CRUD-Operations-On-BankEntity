package com.example.Bankss.Controller;

import com.example.Bankss.Entity.BankEntity;
import com.example.Bankss.Respository.BankRepo;
import com.example.Bankss.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    public BankService bankService;
    @Autowired
    public BankRepo bankRepo;
    @PostMapping("/add")
    public BankEntity add(@RequestBody BankEntity bankEntity)
    {

        return bankService.add(bankEntity);
    }
    @GetMapping("/show")
    public List<BankEntity> display()
    {

        return bankService.display();
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<BankEntity> showing(@PathVariable int id)
    {
        BankEntity bank=bankService.showing(id);
        if(bank!=null)
        {
            return ResponseEntity.ok(bank);
        }
        else{
            return  ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id)
    {
        bankService.delete(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<BankEntity> update(@PathVariable int id,@RequestBody BankEntity bankEntity)
    {
        BankEntity bank=bankService.update(id,bankEntity);
        if(bank!=null)
        {
            return ResponseEntity.ok(bank);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportStudentsToPdf() {
        List<BankEntity> bank = bankRepo.findAll();
        byte[] pdf = bankService.exportStudentsToPdf(bank);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
    @GetMapping("/maxSalary")
    public List<BankEntity> maxSalary() {
        List<BankEntity> amount = bankService.maxAmount();
        return amount;
    }
    @GetMapping("/min")
    public List<BankEntity> minsalary()
    {
        return bankService.minAmount();
    }
    @GetMapping("/order")
    public List<BankEntity> orderby(){
        //List<BankEntity> amount=  bankService.orderby();
        return  bankService.orderby();
    }
    @GetMapping("/orderbyasc")
    public List<BankEntity> orderbyasc()
    {
        return bankRepo.findAllByOrderByAmountAsc();
    }
    @GetMapping("factorial/{id}")
    public int factorial(@PathVariable(value= "id") int n)
    {
        return bankService.factorial(n);
    }
    @PostMapping("/value")
    public int facts(@RequestBody int n)
    {
        return bankService.facts(n);
    }
    @GetMapping("/getting/{id}")
    public List<BankEntity> data(@PathVariable(value = "id") int amount)
    {
        List<BankEntity> bankEntityList= bankRepo.findByAmount(amount);
        if(bankEntityList!=null)
        {
            return bankEntityList;
        }
        else {
            return null;
        }
    }
//    @GetMapping("/gettings/{id}")
//    public ResponseEntity<Object> datas(@PathVariable(value = "id") int amount)
//    {
//        BankEntity profit=bankRepo.findByAmount(amount);
//        if(profit!=null)
//        {
//            return ResponseEntity.ok(profit);
//        }
//        else {
//            return ResponseEntity.ok("NotFound jjjjhishuw");
//        }
//    }
    @GetMapping("/greater/{id}")
    public List<BankEntity> greatest(@PathVariable(value="id") int amount)
    {
        return bankRepo.findBanksWithAmountGreaterThan(amount);
    }
    @GetMapping("/bybankname/{id}")
    public List<BankEntity> bybankname(@PathVariable(value="id") String bankname)
    {
        return bankRepo.findAllByName(bankname);
    }
    @GetMapping("/particular/{id}")
    public List<String> byName(@PathVariable(value="id") String name)
    {
        return bankRepo.findALLBYName(name);
    }
    @GetMapping("/specific")
    public List<String> specific()
    {
        return bankRepo.findBYNames();
    }
    @GetMapping("/alldetails")
    public List<BankEntity> adddetails()
    {
        return bankRepo.allDetails();
    }
    @GetMapping("/groupby")
    public List<Object[]> groupby()
    {
        return bankRepo.groupby();
    }
}
