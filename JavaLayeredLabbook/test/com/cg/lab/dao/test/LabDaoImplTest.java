package com.cg.lab.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.lab.dao.LabDao;
import com.cg.lab.dao.LabDaoImpl;
import com.cg.lab.exceptions.LABException;
import com.cg.lab.model.LABModel;

public class LabDaoImplTest {

	LabDao DaoImpl = null;

	@Before
	public void setUp() throws Exception {
		DaoImpl = new LabDaoImpl();

	}

	@After
	public void tearDown() throws Exception {
		DaoImpl = null;
	}

	@Test
	public void testInsertDetails() {
		LABModel labModel = new LABModel();
		labModel.setId(1001);
		labModel.setName("Nihal");
		labModel.setPhone("9876543210");

		labModel.setMail("nihalsekhar@gmail.com");
		try {
			int result = DaoImpl.insertDetails(labModel);
			assertEquals(1, result);
		} catch (LABException e) {

		}

	}

	@Test
	public void testSerachDetails() {
		LABModel labModel = new LABModel();
		labModel.setMinPrice(8000.00);
		labModel.setMaxPrice(30000.00);

		try {
			List<LABModel> list = DaoImpl.serachDetails(labModel);
			assertEquals(5, list.size());
		} catch (LABException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
