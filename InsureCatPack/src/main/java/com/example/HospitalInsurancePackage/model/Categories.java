package com.example.HospitalInsurancePackage.model;

public class Categories {
	/*"${category.categoryId}"></td>
					<td th:text="${category.categoryTitle}"></td>
					<td th:text="${category.categoryDescription}"></td>
					<td th:text="${category.diseaseStatus}"></td>
					<td>*/
	private int categoryId;
	private String categoryTitle,categoryDescription;
	private String categoryStatus;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public String getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categories(int categoryId, String categoryTitle, String categoryDescription, String categoryStatus) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
		this.categoryStatus = categoryStatus;
	}
	

}
