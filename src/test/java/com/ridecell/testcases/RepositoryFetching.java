package com.ridecell.testcases;

import org.testng.annotations.*;

import com.ridecell.base.BasePage;
import com.ridecell.pages.repoListUI;

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
