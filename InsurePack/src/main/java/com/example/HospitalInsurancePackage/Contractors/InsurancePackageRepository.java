package com.example.HospitalInsurancePackage.Contractors;

import java.util.List;

import com.example.HospitalInsurancePackage.model.DiseaseDetails;
import com.example.HospitalInsurancePackage.model.InsurancePackage;
import com.example.HospitalInsurancePackage.model.InsurancePackageCoveredDisease;

public interface InsurancePackageRepository {
    List<InsurancePackage> getAllInsurancePackages();
    List<InsurancePackageCoveredDisease> getCoveredDiseasesByPackageId(int packageId);
	DiseaseDetails getDiseaseDetailsById(int discId);
	List<InsurancePackage> getFilteredPackages(String status, int age);
	List<InsurancePackage> getPackagesByStatus(String status);
	List<InsurancePackage> getAllInsurancePackagesByAge(int parseInt);
	List<DiseaseDetails> getDiseasesByPackageId(int id);
	int addDisease(String name, String iCDCode, String description, String status,int disid);
	int deleteDisease(int parseInt, int parseInt2);
	String editDisease(String name, String iCDCode, String description, String status);
	
}

