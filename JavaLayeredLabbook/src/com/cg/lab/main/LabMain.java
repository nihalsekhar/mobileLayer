package com.cg.lab.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.lab.exceptions.LABException;
import com.cg.lab.model.LABModel;
import com.cg.lab.service.LabService;
import com.cg.lab.service.LabServiceImpl;

public class LabMain {
	static Logger logger = Logger.getLogger(LabMain.class);

	public static void main(String[] args) {

		PropertyConfigurator.configure("resources/log4j.properties");
		int choices = 0;
		logger.info("Enters the LabMain MAIN method");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Jessica Mobile Park Pvt Ltd");
		System.out.println("1. Generate Bill");
		System.out.println("2. View Mobile Quantity");
		System.out.println("3. Remove Mobile based on Mobile ID");
		System.out.println("4. Search for Mobile based on prices");
		System.out.println("Choose any one of the following at a time: ");
		try {
			choices = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Please enter only DIGITS(1-4)");
		}
		logger.info("Finished getting choice" + choices);

		scanner.nextLine();
		int result;
		Integer id = 0;
		switch (choices) {
		case 1:
			logger.info("Inside Switch case 1");
			System.out.println("Enter the Customer Name: ");
			String name = scanner.nextLine();

			System.out.println("Enter the Mail-ID: ");
			String mail = scanner.nextLine();

			System.out.println("Enter the Mobile Number: ");
			String phone = scanner.nextLine();

			System.out.println("Enter the Mobile ID: ");
			try {
				id = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.err
						.println("Please enter a valid phone number only DIGITS");
			}
			LABModel labModel = new LABModel();
			labModel.setName(name);
			labModel.setMail(mail);
			labModel.setPhone(phone);
			labModel.setId(id);
			logger.info("Finished inserting data" + labModel);
			LabService service = new LabServiceImpl();

			try {
				logger.info("Before hitting the service layer");
				result = service.insertDetails(labModel);
				logger.info("After hitting the service layer: " + result);
				if (result == 0) {
					System.err.println("Enter the details again");
				} else {
					System.out.println(result + " Bill Created");
					logger.info("Bill Generated");
				}

			} catch (LABException e) {
				logger.error(e.getMessage());
				System.err.println(e.getMessage());
			}

			break;
		case 2:
			logger.info("Enter switch case 2");
			try {
				LabService service1 = new LabServiceImpl();
				logger.info("Before hitting the service layer");
				List<LABModel> list = service1.getMobiles();

				System.out.println("Mobile Details");
				for (LABModel lab : list) {

					System.out.println(lab.getId() + "---" + lab.getMobile()
							+ "---" + lab.getPrice() + "---"
							+ lab.getQuantity());
					logger.info("after hitting the service layer :"
							+ lab.getId() + "---" + lab.getMobile() + "---"
							+ lab.getPrice() + "---" + lab.getQuantity());
				}

			} catch (LABException e) {
				logger.error(e.getMessage());
				System.err.println(e.getMessage());
			}

			break;
		case 3:
			logger.info("Enter switch case 3");
			int idForDelete = 0;
			System.out.println("Enter the ID for deletion: ");
			try {
				idForDelete = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Enter an ID with only DIGITS");
			}
			logger.info("After accepting value from user: " + idForDelete);
			LabService serviceDelete = new LabServiceImpl();
			try {
				logger.info("Before hitting the service layer");
				result = serviceDelete.deleteDetails(idForDelete);
				System.out.println(result + " rows deleted");
				logger.info("After hitting the service layer" + result);
			} catch (LABException e) {
				logger.error(e.getMessage());
				System.err.println(e.getMessage());
			}
			break;
		case 4:
			logger.info("Enter switch case 4");
			Double minPrice = 0.0;
			Double maxPrice = 0.0;
			System.out.println("Enter the minimum range");
			try {
				minPrice = scanner.nextDouble();
			} catch (InputMismatchException e) {
				System.err.println("Please enter a valid price");
			}
			logger.info("Min Value from user: " + minPrice);
			System.out.println("Enter the maximum range");
			try {
				maxPrice = scanner.nextDouble();
			} catch (InputMismatchException e) {
				System.err.println("Please enter a valid price");
			}
			logger.info("Max Value from user: " + maxPrice);
			LABModel labModel3 = new LABModel();
			labModel3.setMinPrice(minPrice);
			labModel3.setMaxPrice(maxPrice);
			logger.info("Before hitting the service layer" + labModel3);
			LabService serviceSearch = new LabServiceImpl();

			try {
				List<LABModel> list = serviceSearch.serachDetails(labModel3);
				for (LABModel labModel31 : list) {
					System.out.println(labModel31.getId() + "----"
							+ labModel31.getMobile() + "----"
							+ labModel31.getPrice() + "----"
							+ labModel31.getQuantity());
					logger.info("After hitting the service layer: "
							+ labModel31.getId() + "----"
							+ labModel31.getMobile() + "----"
							+ labModel31.getPrice() + "----"
							+ labModel31.getQuantity());
				}

			} catch (LABException e) {
				logger.error(e.getMessage());
				System.err.println(e.getMessage());
			}

			break;
		default:
			break;
		}
		scanner.close();
		logger.info("Finished LAB-MAIN");

	}

}
