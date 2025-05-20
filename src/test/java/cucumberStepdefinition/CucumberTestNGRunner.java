package cucumberStepdefinition;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src/test/java/cucumber" , glue={"cucumberStepdefinition"} , monochrome=true , plugin= {"pretty" , "html:Reports/cucumberReport.html"})
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests{

}
