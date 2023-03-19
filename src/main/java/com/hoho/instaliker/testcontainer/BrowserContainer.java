package com.hoho.instaliker.testcontainer;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.lifecycle.TestDescription;
import org.testcontainers.shaded.com.google.common.io.Files;

import java.io.File;
import java.util.Optional;

@Slf4j
public class BrowserContainer extends BrowserWebDriverContainer {

    private File tempDirectory;

    public void configureAndStart() {
        tempDirectory = Files.createTempDir();
        withRecordingMode(VncRecordingMode.RECORD_ALL, tempDirectory, VncRecordingContainer.VncRecordingFormat.MP4);
        configure();
        start();
    }

    public void getRecordingFile() {
        TestDescription testDescription = TempDescription.builder()
            .filesystemFriendlyName("video")
            .build();
        afterTest(testDescription, Optional.empty());
        log.info(tempDirectory.getAbsolutePath());
    }

}
