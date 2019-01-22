package com.cg.lab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.lab.exceptions.LABException;
import com.cg.lab.model.LABModel;

import com.cg.lab.utility.dbUtility;

public class LabDaoImpl implements LabDao {
	static Logger logger = Logger.getLogger(LabDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	int result = 0;
/**
 * method 		: inserting details to the database as a bill
 * argument 	: labMoble object which has the setter customer name, phone number, mail id and mobile id they bought
 * return 		: integer to mark how many rows have been inserted from DB
 * author 		: Capgemini
 * Date 		: 18-01-2019
 * */
	@Override
	public int insertDetails(LABModel labModel) throws LABException {
		logger.info("Inside insertDetails");
		int result = 0;
		int notZero = 0;
		connection = dbUtility.getConnection();
		logger.info("connection found"+connection);
		try {
			
			List<LABModel> list = new ArrayList<>();
			
			statement = connection
					.prepareStatement(QueryClass.checkMobileQuantityQuery);
			logger.info("statement created"+statement);
			statement.setInt(1, labModel.getId());
		//	System.out.println("mobiel id" + labModel.getId());
			resultSet = statement.executeQuery();
			logger.info("Result Set Created"+resultSet.toString());
			while (resultSet.next()) {
				logger.info("inside result set");
				int quantity = resultSet.getInt(1);
				logger.info("quantity" +quantity);
				//System.out.println(quantity);
				LABModel labs = new LABModel();
				labs.setQuantity(quantity);
				list.add(labs);
				for (LABModel lab : list) {

					notZero = lab.getQuantity();
				}
				logger.info("While exit");
			}
			logger.info("checking qunatity if not zero");
			if (!(notZero == 0)) {
				statement = connection.prepareStatement(QueryClass.insertQuery);
				logger.info("insert statement is created"+statement);
				statement.setString(1, labModel.getName());
				statement.setString(2, labModel.getMail());
				statement.setString(3, labModel.getPhone());
				statement.setInt(4, labModel.getId());
				int mobileId = labModel.getId();
				result = statement.executeUpdate();
				logger.info("result has been created" +result);
				statement = connection
						.prepareStatement(QueryClass.updateMobileCountQuery);
				logger.info("update statement is created"+statement);
				statement.setInt(1, mobileId);
				statement.executeUpdate();
			} else {
				System.out.println("There is no stock left for the given ID");
				result = 0;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new LABException(
					"SQL Statement was not created check your exectue query");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new LABException("Statement could not be closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new LABException("Connection could not be closed");
			}
		}
		logger.info("ends insertDetails");
		return result;
	}
	/**
	 * method		 	: getting details from the mobiles database to view the mobiles table
	 * argument			: no argument is being passed as it is only a select all query with no conditions
	 * return 			: list is the return type which gives us the list off all mobiles and their attributes
	 * author 			: Capgemini
	 * Date 			: 18-01-2019
	 * */
	@Override
	public List<LABModel> getMobiles() throws LABException {
		logger.info("Enters inside getMobiles");
		List<LABModel> list = new ArrayList<>();
		connection = dbUtility.getConnection();
		logger.info("connection created");
		try {
			statement = connection.prepareStatement(QueryClass.getDetailsQuery);
			logger.info("statement created");
			resultSet = statement.executeQuery();
			logger.info("result set created");
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String mobile = resultSet.getString(2);
				Double price = resultSet.getDouble(3);
				int quantity = resultSet.getInt(4);

				LABModel labModel = new LABModel();
				labModel.setId(id);
				labModel.setMobile(mobile);
				labModel.setPrice(price);
				labModel.setQuantity(quantity);
				logger.info("LabModel Object created"+labModel);
				list.add(labModel);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new LABException("Statement was not created");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new LABException("ResultSet was not closed");
			}
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new LABException("Statement was not closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new LABException("Connection was not closed");
			}

		}
		logger.info("ends getMobiles");
		return list;
	}
	/**
	 * method 		: deleting certain rows based on the mobile id
	 * argument 	: integer as to which row to deleted from the given mobile id
	 * return 		: integer as to how many rows have been deleted from db
	 * author 		: Capgemini
	 * Date			: 18-01-2019
	 * */
	@Override
	public int deleteDetails(int idForDelete) throws LABException {
		logger.info("Enters inside deleteDetails");
		connection = dbUtility.getConnection();
		logger.info("connection created");
		try {
			statement = connection.prepareStatement(QueryClass.deleteQuery);
			logger.info("statement created");
			statement.setInt(1, idForDelete);
			result = statement.executeUpdate();
			logger.info("result created"+result);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new LABException("Statement was not created");
		}
		try {
			statement.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new LABException("Statement was not closed");
		}
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new LABException("Connection was not closed");
		}
		logger.info("Ends deleteDetails");
		return result;
	}
	/**
	 * method 		: searching the details of the mobiles based on price range
	 * argument 	: labModel3 object contains the price range of mobile in double data type
	 * return 		: list is the return type as the query fetches mobile's within the price range from the database
	 * author 		: Capgemini
	 * Date 		: 18-01-2019
	 * */
	@Override
	public List<LABModel> serachDetails(LABModel labModel3) throws LABException {
		logger.info("enters into searchDetails");
		connection = dbUtility.getConnection();
		logger.info("connection created");
		List<LABModel> list = new ArrayList<>();
		try {
			statement = connection
					.prepareStatement(QueryClass.selectQueryBasedOnPrice);
			logger.info("statement created");
			statement.setDouble(1, labModel3.getMinPrice());
			statement.setDouble(2, labModel3.getMaxPrice());
			resultSet = statement.executeQuery();
			logger.info("resultSet created");
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String mobile = resultSet.getString(2);
				Double price = resultSet.getDouble(3);
				int quantity = resultSet.getInt(4);

				LABModel labModel = new LABModel();
				labModel.setId(id);
				labModel.setMobile(mobile);
				labModel.setPrice(price);
				labModel.setQuantity(quantity);
				logger.info("labModel object created"+labModel);
				list.add(labModel);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new LABException("Statement was not created");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new LABException("ResultSet was not closed");
			}
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new LABException("Statement was not closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new LABException("Connection was not closed");
			}

		}
		logger.info("Ends searchDetails");
		return list;
	}
}
