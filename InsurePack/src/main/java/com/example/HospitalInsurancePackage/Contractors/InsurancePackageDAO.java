package com.example.HospitalInsurancePackage.Contractors;

import java.util.List;

import com.example.HospitalInsurancePackage.model.DiseaseDetails;
import com.example.HospitalInsurancePackage.model.InsurancePackage;
import com.example.HospitalInsurancePackage.model.InsurancePackageCoveredDisease;

public interface InsurancePackageDAO {
    List<InsurancePackage> getAllInsurancePackages();
    List<InsurancePackageCoveredDisease> getCoveredDiseasesByPackageId(int packageId);
	DiseaseDetails getDetailsByDiseaseId(int discId);
	List<InsurancePackage> getFiteredDiseases(String status, int age);
	List<InsurancePackage> getPackagesByStatus(String status);
	List<InsurancePackage> getAllInsurancePackagesByAge(int age);
	List<DiseaseDetails> getDiseasesByPackageId(int id);
	int addDisease(String name, String iCDCode, String description, String status,int inspid);
	
	int deleteDisease(int did, int inspid);
	String editDisease(String name, String iCDCode, String description, String status);
    }

