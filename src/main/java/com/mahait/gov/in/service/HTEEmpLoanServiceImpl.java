package com.mahait.gov.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.repository.HTEEmpLoanRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HTEEmpLoanServiceImpl implements HTEEmpLoanService {
	@Autowired
	HTEEmpLoanRepo hTEEmpLoanRepo;

}
