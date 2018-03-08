package com.epam.training;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Const {

    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static RestClient CLIENT = new RestClient();
    public static String LIBRARY_URL = "http://localhost:8080/api/files";
    public static String FAKE_LIBRARY_URL = "http://localhost:8080/fake";
    public static String LOCAL_LIBRARY_URL = "src/test/resources";
    public static String FILE = "seven.jpg";
}
