package com.epam.training.model;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface IFileController {

    @RequestMapping("/hi")
    public @ResponseBody String greeting();

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException;

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String fileName);

    @GetMapping("/getAll")
    public ResponseEntity<String> getAll();

    @GetMapping
    public ResponseEntity<Resource> get(@RequestParam String fileName) throws MalformedURLException, URISyntaxException;
}
