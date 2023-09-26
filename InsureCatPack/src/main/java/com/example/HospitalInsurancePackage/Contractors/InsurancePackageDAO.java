package com.example.HospitalInsurancePackage.Contractors;

import java.util.List;

import com.example.HospitalInsurancePackage.model.Categories;
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
	List<Categories> getCategoriesByPackageId(int inspId);
	int deleteCategory(int did, int inspid);
	int addCategory(String name, String title, String description, String string, int parseInt);
	String editCategory(String title, String description, String status) ;
	int deletePackage(int did);
    }

