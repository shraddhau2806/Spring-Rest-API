package com.example.demo.repository;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	public List<Payment> findByDescription(String qryString);
	public List<Payment> findByAmountGreaterThan(double  qryAmount);

	@Query(value= "update payments set amount=:newAmount where txnId=:id", nativeQuery = true)
	@Modifying
	@Transactional
	public int updateAmount(@Param("newAmount")double amtToUpdate, @Param("id") int id);
}
