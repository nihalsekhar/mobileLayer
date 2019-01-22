package com.cg.lab.dao;

import java.util.List;

import com.cg.lab.exceptions.LABException;
import com.cg.lab.model.LABModel;

public interface LabDao {

	int insertDetails(LABModel labModel) throws LABException;

	List<LABModel> getMobiles() throws LABException;

	int deleteDetails(int idForDelete)throws LABException;

	List<LABModel> serachDetails(LABModel labModel3)throws LABException;

}
