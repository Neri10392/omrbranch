package org.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.reports.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

/**
 * 
 * @author Neriyarasan
 * 
 * @category Test Runner Class 
 * 
 * @category Generating JvmReports...
 * 
 * @date 8/07/2022
 * 
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(monochrome=true,dryRun = false,plugin= {"pretty","json:target\\Output.json"}, snippets = SnippetType.CAMELCASE,stepNotifications=true, features = "C:\\Users\\Welcome\\eclipse-workspace\\OMRBranchAPIAutomation\\src\\test\\resources\\Features", glue = "org.stepdefination")
public class TestRunnerClass {

	@AfterClass
	public static void afterClass() {
		 Reporting.generateJvmReport("C:\\Users\\Welcome\\eclipse-workspace\\OMRBranchAPIAutomation\\target\\Output.json");
}

}