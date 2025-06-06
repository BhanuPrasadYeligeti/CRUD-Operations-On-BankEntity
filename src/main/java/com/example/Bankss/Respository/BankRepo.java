package com.example.Bankss.Respository;

import com.example.Bankss.Entity.BankEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepo extends JpaRepository<BankEntity,Integer> {
    List<BankEntity> findTopByOrderByAmountDesc();//first max
    List<BankEntity> findTopByOrderByAmountAsc();//first min
    List<BankEntity> findAllByOrderByAmountDesc();//orderby desceding order
    List<BankEntity> findAllByOrderByAmountAsc();//orderby ascending order
    List<BankEntity>  findByAmount(int amount);//uisng list
    List<BankEntity> findAllByName(String name);
    @Query("SELECT b FROM BankEntity b WHERE b.amount > :amount")
    List<BankEntity> findBanksWithAmountGreaterThan(@Param("amount") int amount);
    @Query("select b.name FROM BankEntity b WHERE b.name=:name")
    List<String> findALLBYName(@Param("name") String name);
    @Query(value="select name from bank_entity" ,nativeQuery=true)
    List<String> findBYNames();
    @Query(value="select * from bank_entity",nativeQuery=true)
    List<BankEntity> allDetails();
    @Query(value="select name,sum(amount) from bank_entity group by name ",nativeQuery = true)
    List<Object[]> groupby();

}
