package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Account;
import com.example.demo.Repository.accountRepository;

@Service
public class AccountService {

	@Autowired
	private accountRepository accountRepository;

	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	public Account getAccount(Integer id) {
		return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not found"));
	}
	
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	public Account depositAmount(Integer id, double amount) {
		Account account = getAccount(id);
		System.out.println(account+"dddddddddddddddddddddddddddddddddddddddddddddd================================");
		double depositAmount = account.getBalance() + amount;
		account.setBalance(depositAmount);
		Account savedAccount = accountRepository.save(account);
		return savedAccount;
	}

	public Account withdrawAmount(Integer id, double amount) {
		Account account = getAccount(id);
		
		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufiicient Fund");
		}
		double depositAmount = account.getBalance() - amount;
		account.setBalance(depositAmount);
		Account savedAccount = accountRepository.save(account);
		return savedAccount;
	}

	public void deleteAccount(Integer id) {
		Account account=getAccount(id);
		accountRepository.delete(account);
	}

}
