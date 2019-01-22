package com.cg.lab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.lab.dao.LabDao;
import com.cg.lab.dao.LabDaoImpl;
import com.cg.lab.exceptions.LABException;
import com.cg.lab.model.LABModel;

public class LabServiceImpl implements LabService {
	static Logger logger = Logger.getLogger(LabServiceImpl.class);
	LabDao labDao = new LabDaoImpl();
	/**
	 * method : validating the details entered by the user
	 * argument : labModel3 object contains the price range of mobile in double data type
	 * return : list is the return type as the query fetches mobile's within the price range from the database
	 * author : Capgemini
	 * Date : 18-01-2019
	 * */
	@Override
	public int insertDetails(LABModel labModel) throws LABException {
		logger.info("inside validation");
		List<String> list = new ArrayList<>();
		int num = 0;
		if (!validateName(labModel.getName())) {
			logger.debug("INVALID NAME: " + labModel.getName());
			list.add("Name should start with capital letter and have a maximum of 20 characters");
			logger.debug("ERROR THROWN " + list.toString());
		}
		if (!validateEmail(labModel.getMail())) {
			logger.debug("INVALID EMAIL: " + labModel.getMail());
			list.add("Enter a valid email");
			logger.debug("ERROR THROWN: " + list + "");
		}
		if (!validatePhone(labModel.getPhone())) {
			logger.debug("INVALID EMAIL: " + labModel.getPhone());
			list.add("Enter a 10 digit phone number");
			logger.debug(": " + list + "");
		}

		if (!list.isEmpty()) {
			logger.debug("Checking if Error is present: " + list);
		} else {
			logger.debug("Passes onto DAO Layer: " + labModel);
			num = labDao.insertDetails(labModel);
		}
		return num;
	}

	private boolean validatePhone(String phone) {
		logger.debug("Phone Regex");
		String phoneRegex = "[6|7|8|9]{1}[0-9]{9}$";
		boolean result = Pattern.matches(phoneRegex, phone);
		logger.debug("Phone Regex Validation: " + result);
		return result;
	}

	private boolean validateEmail(String mail) {
		logger.debug("Email Regex");
		String emailRegex = "[A-Za-z.0-9]*@[A-za-z]*\\.[A-za-z]*";
		boolean result = Pattern.matches(emailRegex, mail);
		logger.debug("Email Regex Validation: " + result);
		return result;
	}

	private boolean validateName(String name) {
		logger.debug("Name Regex");
		String nameRegex = "[A-Z]{1}[a-zA-Z\\s]{4,19}$";
		boolean result = Pattern.matches(nameRegex, name);
		logger.debug("Name Regex Validation: " + result);
		return result;
	}

	@Override
	public List<LABModel> getMobiles() throws LABException {
		logger.debug("Enters into DAO layer for getMobiles");
		return labDao.getMobiles();
	}

	@Override
	public int deleteDetails(int idForDelete) throws LABException {
		logger.debug("Enters into DAO layer for deleteDetails");
		return labDao.deleteDetails(idForDelete);
	}

	@Override
	public List<LABModel> serachDetails(LABModel labModel3) throws LABException {
		logger.debug("Enters into DAO layer for serachDetails");
		return labDao.serachDetails(labModel3);
	}

}
