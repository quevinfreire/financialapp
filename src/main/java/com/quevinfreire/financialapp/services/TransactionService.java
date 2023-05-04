package com.quevinfreire.financialapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.quevinfreire.financialapp.entities.Transaction;
import com.quevinfreire.financialapp.repositories.TransactionRepository;
import com.quevinfreire.financialapp.services.exceptions.DatabaseException;
import com.quevinfreire.financialapp.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Transaction insert(Transaction obj) {
		return transactionRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			transactionRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	@Transactional
	public Transaction update(long id, Transaction obj) {
		
		try {
			Transaction entity = transactionRepository.getReferenceById(id);
			updateData(entity, obj);
			
			return transactionRepository.save(entity);
			 
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(obj);
		}
	}

	private void updateData(Transaction entity, Transaction obj) {
		entity.setDescription(obj.getDescription());
		entity.setType(obj.getType());
		entity.setAmount(obj.getAmount());
		entity.setMoment(obj.getMoment());
		entity.setDueDate(obj.getDueDate());
	}
	
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}
}
