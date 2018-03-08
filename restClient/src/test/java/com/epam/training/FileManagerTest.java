package com.epam.training;

import org.junit.Test;

import java.io.File;

import static com.epam.training.Const.*;
import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

public class FileManagerTest {

    private File file = new File(LOCAL_LIBRARY_URL + "/" + FILE);
    private File fileToError = new File(LOCAL_LIBRARY_URL + FILE);

    @Test
    public void uploadFile() {
        CLIENT.Post(LIBRARY_URL, file);
        File fileUploadedToServer = GSON.fromJson(CLIENT.Get(LIBRARY_URL), File.class);
        assertNotNull(fileUploadedToServer);
        assertEquals(file.getName(), FILE);
    }

    @Test
    public void uploadFileToFail() {
        CLIENT.Post(LIBRARY_URL, fileToError);
        File fileUploadedToServer = GSON.fromJson(CLIENT.Get(LIBRARY_URL), File.class);
        assertNotNull(fileUploadedToServer);
        assertEquals(fileToError, fileUploadedToServer);
    }

    @Test
    public void deleteFiles() {
        String actualResponse = CLIENT.Delete(LIBRARY_URL);
        assertNotNull(actualResponse);
        CLIENT.Post(LIBRARY_URL, file);
    }

    @Test
    public void deleteFilesToFail() {
        String okResponse = CLIENT.Delete(LIBRARY_URL);
        String fakeResponse = CLIENT.Delete(FAKE_LIBRARY_URL);
        assertEquals(fakeResponse, okResponse);
    }

    @Test
    public void getFile() {
        String actualResponse = CLIENT.Get(LIBRARY_URL + "/" + FILE);
        assertNotNull(actualResponse);
    }

    @Test
    public void getFileToFail() {
        String actualResponse = CLIENT.Get(LIBRARY_URL + "/" + FILE);
        assertEquals(actualResponse, FILE);
    }

}