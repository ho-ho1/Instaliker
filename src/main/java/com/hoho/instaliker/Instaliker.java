package com.hoho.instaliker;

import com.hoho.instaliker.testcontainer.BrowserContainer;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.RecordingFileFactory;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.lifecycle.TestDescription;
import org.testcontainers.shaded.com.google.common.io.Files;

import java.io.File;
import java.util.Optional;

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;
import static org.testcontainers.containers.VncRecordingContainer.VncRecordingFormat.MP4;

@Slf4j
public class Instaliker {


    By quote = By.cssSelector("div#container div[class*='quote']");

    public void run() {
        File target = new File("./target");

        BrowserContainer chrome = (BrowserContainer) new BrowserContainer()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(RECORD_ALL, target, MP4)
            .withSharedMemorySize(2147483648L); // Required to run this project on Jenkins using Docker in Docker: https://giters.com/testcontainers/testcontainers-java/issues/4697
        chrome.configureAndStart();

        RemoteWebDriver webDriver = chrome.getWebDriver();
        webDriver.get("http://bash.org.pl/random/");

        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(quote));

        String text = webDriver.findElement(quote).getText();

        System.out.println(text);

        chrome.getRecordingFile();
    }
}
