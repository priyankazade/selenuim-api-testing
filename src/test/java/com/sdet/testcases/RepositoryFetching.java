package com.sdet.testcases;

import org.testng.annotations.*;

import com.sdet.base.BasePage;
import com.sdet.pages.repoListUI;

public class RepositoryFetching extends BasePage {


	repoListUI RepoListUI;

	public RepositoryFetching() {
		super();
	}

	@BeforeSuite(alwaysRun = true)
	public void setUp() {
		initializaton();
		RepoListUI = new repoListUI();
	}

	@Test
	public void RepositoryFetching() throws Throwable {
		RepoListUI.FetchRepoName();
	}


	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		tearDownMain();
	}

}
