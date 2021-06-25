package com.sdet.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.sdet.base.BasePage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class repoListUI extends BasePage{

	List<String> descUI = new ArrayList<String>();
	List<String> namesUI = new ArrayList<String>();
	public repoListUI() { 
		super(); 
		PageFactory.initElements(driver, this);
	}

	//navigate to repository section
	@FindBy(xpath = "//a[@class='UnderlineNav-item selected']")
	WebElement repositorySection;

	//to get list of repositories
	@FindBy(xpath = "")
	WebElement cancelBtn;

	public repoListUI FetchRepoName() {
		System.out.println("At Repository Page");
		repositorySection.click();
		
		List<WebElement> listName = driver.findElements(By.xpath("//a[@class='d-inline-block']"));
		System.out.println();
		for (int i=0; i<listName.size();i++)
		{
			namesUI.add(listName.get(i).getText());
			Collections.sort(namesUI);
		}
		System.out.println("UI repo name is: " + namesUI);
		

		//UI List
		List<WebElement> listDesc = driver.findElements(By.xpath("//p[@itemprop='description']"));
		for (int j=0; j<listDesc.size();j++)
		{
			descUI.add(listDesc.get(j).getText());
			Collections.sort(descUI);
		}
		System.out.println("UI repo description is: " + descUI);
		
		RestAssured.baseURI = "https://api.github.com/users/django"; 
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/repos");

		List<String> namesAPI = response.jsonPath().getList("name");
		Collections.sort(namesAPI);
		System.out.println("API rep name is: "+namesAPI);
		
		List<String> descAPI = response.jsonPath().getList("description");
		Collections.sort(descAPI);
		System.out.println("API repo desc is: "+descAPI);
		
		Assert.assertEquals(namesAPI, namesUI, "Repo names are matching");
		Assert.assertEquals(descAPI, descUI, "Repo descriptions are matching");
		
		return new repoListUI();
	}

}
