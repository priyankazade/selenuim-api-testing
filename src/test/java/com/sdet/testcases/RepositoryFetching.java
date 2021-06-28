package com.sdet.testcases;

import org.testng.annotations.*;

import com.sdet.base.BasePage;
import com.sdet.pages.repoTest;

public class RepositoryFetching extends BasePage {


	repoTest RepoListUI;

	public RepositoryFetching() {
		super();
	}

	@BeforeSuite(alwaysRun = true)
	public void setUp() {
		initializaton();
		RepoListUI = new repoTest();
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
