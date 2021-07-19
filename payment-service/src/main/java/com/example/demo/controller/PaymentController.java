package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class PaymentController {

	@Value("${server.port}")
	private String portNumber;

	@Autowired
	private PaymentService service;
	
/*	@GetMapping(path = "/payments")
	public List<Payment> getAll(){
		return this.service.getAll();
	}
	*/
	
	@GetMapping(path = "/payments")
	public String  getAll(){
		List<Payment> list = this.service.getAll();
		return list.toString()+ ":" +this.portNumber;
	}
	
	@Operation(description ="This method fetches the Payment Details by transaction Id", 
			parameters = @Parameter(example = "101 or 102"))
	@GetMapping(path="/payments/{id}")
	public Payment getById(@PathVariable("id") int id) {
		return this.service.getById(id);
	}
	
	@PostMapping(path ="/payments")
	public ResponseEntity<Payment> add(@RequestBody Payment entity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.add(entity));
	}
	
	@PutMapping(path="/payments")
	public Payment update(@RequestBody Payment entity) {
		return this.service.add(entity);
	}
	
	@DeleteMapping(path="/payments")
	public Payment remove(@RequestBody Payment entity) {
		return this.service.remove(entity);
	}
	@GetMapping(path="/paymenys/srch/desc/{description}")
	public List<Payment> getByDescription(@PathVariable("description") String descripton){
		return this.service.getByDescription(descripton);
		
		
	}
	@GetMapping(path="/payments/srch/{amount}")
	public List<Payment> getByAmountGreaterThan(@PathVariable("amount") double amount){
		return this.service.getByAmountGreaterThan(amount);
		
	}
	@GetMapping(path="/payments/srch/{id}/{amount}")
	public ResponseEntity<String> updateAmount(@PathVariable("id") int id, @PathVariable("amount") double amount){
		
		int rowsUpdated = this.service.updateAmount(id, amount);
		return ResponseEntity.ok().body(rowsUpdated + "Rows Updated");
	}
	
}
