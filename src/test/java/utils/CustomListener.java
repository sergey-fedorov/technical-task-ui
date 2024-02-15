package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CustomListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        String filePathWithPrefix = "src/test/resources/artifacts/" + getFilePrefix(result);

        String pageSource = WebDriverManager.getWebDriver().getPageSource();
        File screenshotFile = ((TakesScreenshot) WebDriverManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(filePathWithPrefix + "_screenshot.png"));
            FileUtils.writeStringToFile(new File(filePathWithPrefix + "_pageSource.html"), pageSource, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFilePrefix(ITestResult iTestResult) {
        StringBuilder testMethodName = new StringBuilder(iTestResult.getMethod().getMethodName());
        Object[] parameters = iTestResult.getParameters();
        for (Object parameter : parameters) {
            testMethodName.append("_").append(parameter);
        }
        return testMethodName.toString();
    }

}
