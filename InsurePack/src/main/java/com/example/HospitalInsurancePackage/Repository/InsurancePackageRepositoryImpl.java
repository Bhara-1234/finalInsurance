package com.example.HospitalInsurancePackage.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.HospitalInsurancePackage.Contractors.InsurancePackageDAO;
import com.example.HospitalInsurancePackage.Contractors.InsurancePackageRepository;
import com.example.HospitalInsurancePackage.model.DiseaseDetails;
import com.example.HospitalInsurancePackage.model.InsurancePackage;
import com.example.HospitalInsurancePackage.model.InsurancePackageCoveredDisease;

import java.util.List;

@Service
public class InsurancePackageRepositoryImpl implements InsurancePackageRepository {

    private final InsurancePackageDAO insurancePackageDAO;
    
    @Autowired
    public InsurancePackageRepositoryImpl(InsurancePackageDAO insurancePackageDAO) {
        this.insurancePackageDAO = insurancePackageDAO;
    }

    @Override
    public List<InsurancePackage> getAllInsurancePackages() {
        return insurancePackageDAO.getAllInsurancePackages();
    }
    
    public List<InsurancePackageCoveredDisease> getCoveredDiseasesByPackageId(int packageId) {
        return insurancePackageDAO.getCoveredDiseasesByPackageId(packageId);
    }

	@Override
	public DiseaseDetails getDiseaseDetailsById(int discId) {
		// TODO Auto-generated method stub
		return  insurancePackageDAO.getDetailsByDiseaseId(discId);
	}

	@Override
	public List<InsurancePackage> getFilteredPackages(String status, int age) {
		return  insurancePackageDAO.getFiteredDiseases(status,age);
	}

	@Override
	public List<InsurancePackage> getPackagesByStatus(String status) {
		// TODO Auto-generated method stub
		return  insurancePackageDAO.getPackagesByStatus(status);
	}

	@Override
	public List<InsurancePackage> getAllInsurancePackagesByAge(int age) {
		// TODO Auto-generated method stub
		return  insurancePackageDAO.getAllInsurancePackagesByAge(age);
	}
	
	
	@Override
	public List<DiseaseDetails> getDiseasesByPackageId(int id) {
		return  insurancePackageDAO.getDiseasesByPackageId(id);
		
	}

	@Override
	public int addDisease(String name, String iCDCode, String description, String status,int inspid) {
		// TODO Auto-generated method stub
		return insurancePackageDAO.addDisease(name,iCDCode,description,status,inspid);
	}

	@Override
	public int deleteDisease(int did, int inspid) {
		return insurancePackageDAO.deleteDisease(did,inspid);
		
	}

	@Override
	public String editDisease(String name, String iCDCode, String description, String status) {
		// TODO Auto-generated method stub
		return insurancePackageDAO.editDisease(name, iCDCode, description,status);
	}
	

}






