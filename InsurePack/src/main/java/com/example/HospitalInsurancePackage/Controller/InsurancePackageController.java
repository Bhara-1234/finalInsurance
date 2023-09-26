package com.example.HospitalInsurancePackage.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.HospitalInsurancePackage.Contractors.InsurancePackageRepository;
import com.example.HospitalInsurancePackage.model.DiseaseDetails;
import com.example.HospitalInsurancePackage.model.InsurancePackage;
import com.example.HospitalInsurancePackage.model.InsurancePackageCoveredDisease;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class InsurancePackageController {

	private final InsurancePackageRepository insurancePackageRepository;

	@Autowired
	public InsurancePackageController(InsurancePackageRepository insurancePackageRepository) {
		this.insurancePackageRepository = insurancePackageRepository;
	}

	@GetMapping("/list")
	public String getAllInsurancePackages(Model model) {
		// Retrieve data from the repository
		List<InsurancePackage> insurancePackages = insurancePackageRepository.getAllInsurancePackages();

		// Add the data to the model for rendering in the Thymeleaf template
		model.addAttribute("insurancePackages", insurancePackages);

		// Return the name of the Thymeleaf template to render
		return "packages"; // This assumes you have a Thymeleaf template named
							// "insurance-packages-list.html"
	}

	@GetMapping("/view-insurance/{inspId}")
	public String viewInsurance(@PathVariable int inspId, Model model) {
		// Get the covered diseases for the given inspId
		List<InsurancePackageCoveredDisease> coveredDiseases = insurancePackageRepository
				.getCoveredDiseasesByPackageId(inspId);

		// Add the covered diseases to the model
		model.addAttribute("coveredDiseases", coveredDiseases);

		return "insurance-package-view";
	}

	@GetMapping("/diseasedetails/{discId}")
	public String viewDiseseDetails(@PathVariable int discId, Model model) {
		DiseaseDetails dd = insurancePackageRepository.getDiseaseDetailsById(discId);
		model.addAttribute("diseasedetails", dd);
		System.out.println("age1");
		return "diseasedetails";

	}

	@RequestMapping(value = "/start")
	public String packages() {
		return "redirect:/list";

	}

	@GetMapping("/filteredpackages")
	public String getFilteredPackages(@RequestParam("status") String status, @RequestParam("age") String age,
			Model model) {
		System.out.println(status);
		if ("ALL".equals(status) && age.equals("")) {
			System.out.println("if");
			List<InsurancePackage> insurancePackages = insurancePackageRepository.getAllInsurancePackages();

			// Add the data to the model for rendering in the Thymeleaf template
			System.out.println(insurancePackages);
			model.addAttribute("insurancePackages", insurancePackages);

			// Return the name of the Thymeleaf template to render
			return "packages";
		} else if ("ALL".equals(status) && !age.equals("")) {
			System.out.println("if");
			List<InsurancePackage> insurancePackages = insurancePackageRepository
					.getAllInsurancePackagesByAge(Integer.parseInt(age));

			// Add the data to the model for rendering in the Thymeleaf template
			System.out.println(insurancePackages);
			model.addAttribute("insurancePackages", insurancePackages);

			// Return the name of the Thymeleaf template to render
			return "packages";
		} else {

			if (age.equals("")) {
				List<InsurancePackage> insurancePackages = insurancePackageRepository.getPackagesByStatus(status);
				model.addAttribute("insurancePackages", insurancePackages);
				return "packages";
			} else {
				List<InsurancePackage> packages = insurancePackageRepository.getFilteredPackages(status,
						Integer.parseInt(age));
				model.addAttribute("insurancePackages", packages);
				System.out.println(packages);
				System.out.println(age);

				return "packages";

			}
		}

	}

	@RequestMapping(value = "/excel")
	public void downloadExcel(@RequestParam("status") String status,@RequestParam("age") String age ,HttpServletResponse response) throws IOException {
		List<InsurancePackage> insurancePackages = new ArrayList<>();
		System.out.println(status+age);
		
		if ("ALL".equals(status) && age.equals("")) {
			System.out.println("if");
			insurancePackages = insurancePackageRepository.getAllInsurancePackages();

		} else if ("ALL".equals(status) && !age.equals("")) {
			System.out.println("if");
				insurancePackages = insurancePackageRepository.getAllInsurancePackagesByAge(Integer.parseInt(age));

			// Add the data to the model for rendering in the Thymeleaf template

		} else {

			if (age.equals("")) {
				insurancePackages = insurancePackageRepository.getPackagesByStatus(status);

			} else {
				insurancePackages = insurancePackageRepository.getFilteredPackages(status,Integer.parseInt(age));


			}
		}
		Workbook workbook = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Packages List");
		Row headerRow = sheet.createRow(0);

	    // Define column headings
	    headerRow.createCell(0).setCellValue("PackageId");
	    headerRow.createCell(1).setCellValue("PackageTitle");
	    headerRow.createCell(2).setCellValue("Discription");
	    headerRow.createCell(3).setCellValue("Status");
	    headerRow.createCell(4).setCellValue("Amount Start Range");
	    headerRow.createCell(5).setCellValue("Amount End Range");
	    headerRow.createCell(6).setCellValue("Age Limit Start");
	    headerRow.createCell(7).setCellValue("Age Limit End");
	    
	    
	    System.out.println(insurancePackages.size());
	    
	    int rowIdx = 1;
		for (InsurancePackage insurance : insurancePackages) {
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue(insurance.getInspId());
			row.createCell(1).setCellValue(insurance.getInspTitle());
			row.createCell(2).setCellValue(insurance.getInspDescription());
			row.createCell(3).setCellValue(insurance.getInspStatus());
			row.createCell(4).setCellValue(insurance.getInspRangeStart());
			row.createCell(5).setCellValue(insurance.getInspRangeEnd());
			row.createCell(6).setCellValue(insurance.getInspAgeLimitStart());
			row.createCell(7).setCellValue(insurance.getInspAgeLimitEnd());

		}
		
		
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=packages.xlsx");
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.close();
	}
	
	
	@GetMapping(value="/diseases/{inspId}")
	public String getDiseases(@PathVariable int inspId,Model model) {
		List<DiseaseDetails> diseases = insurancePackageRepository.getDiseasesByPackageId(inspId);
		System.out.println("jhjhjh"+inspId);
		int insId = inspId; 
		model.addAttribute("inspId", insId);
		model.addAttribute("diseases",diseases);
		return "diseasedetails";
		
	}
	@RequestMapping(value="/deletedisease/{diseaseId}")
	public String deleteDisease(Model model, @PathVariable int diseaseId) {
		
		return null;
		
	}
	
	@PostMapping("/addDisease")
	@ResponseBody
	public String addDisease(@RequestParam String name, String ICDCode, String Description,String inspId){
		System.out.println("Bha ra"+inspId);
		int message = insurancePackageRepository.addDisease(name, ICDCode, Description, "Ac",Integer.parseInt(inspId));
		
		System.out.println(message);
		if(message==1)
		return "record added successfully";
		else return "error occured while adding record";
	}
	
	@PostMapping("/deleteDisease")
	@ResponseBody
	public String deleteDisease(@RequestParam String did, String inspId){
		System.out.println("Bha ra"+did);
		int message = insurancePackageRepository.deleteDisease(Integer.parseInt(did),Integer.parseInt(inspId));
		System.out.println();
		
		System.out.println(message);
		if(message==1)
		return "record added successfully";
		else return "error occured while adding record";
	}
	@PostMapping("/editDisease")
	@ResponseBody
	public String editDisease(@RequestParam String name, String ICDCode, String Description, String Status, String inspId,Model model){
		String message = insurancePackageRepository.editDisease(name, ICDCode, Description, Status);
		model.addAttribute("diseases", insurancePackageRepository.getDiseasesByPackageId(Integer.parseInt(inspId)));
		return message;
	}
	
	
}
