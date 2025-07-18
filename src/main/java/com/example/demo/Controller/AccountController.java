package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Account;
import com.example.demo.Services.AccountService;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/addaccount")
	public ResponseEntity<Account> addAcount(@RequestBody Account account) {
		return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
	}

	@GetMapping("/getAccount/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable Integer id) {
		return ResponseEntity.ok(accountService.getAccount(id));
	}

	@GetMapping("/getAllAccount")
	public ResponseEntity<List<Account>> getAllAccount() {
		return ResponseEntity.ok(accountService.getAllAccounts());
	}

	@DeleteMapping("/deleteAccount/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Succesfully");
	}

	@PutMapping("/deposit/{id}")
	public ResponseEntity<Account> depositAmmount(@PathVariable Integer id,
			@RequestBody Map<String, Double> requestAmtMap) {
		double depositAmt = requestAmtMap.get("amount");
		return ResponseEntity.ok(accountService.depositAmount(id, depositAmt));
	}

	@PutMapping("/withdraw/{id}")
	public ResponseEntity<Account> withdrawAmmount(@PathVariable Integer id,
			@RequestBody Map<String, Double> requestAmtMap) {
		double depositAmt = requestAmtMap.get("amount");
		return ResponseEntity.ok(accountService.withdrawAmount(id, depositAmt));
	}

}
