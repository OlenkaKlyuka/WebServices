package com.epam.training.controller;

import com.epam.training.model.IFileController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class FileController implements IFileController {

    @Value("${fileStoragePath}")
    private String STORAGE_PATH;

    @Override
    public String greeting() {
        return "Hello from Rest Service";
    }

    @Override
    public ResponseEntity<String> upload(MultipartFile file) throws IOException {
        file.transferTo(new File(STORAGE_PATH + "/" + file.getOriginalFilename()));
        return ResponseEntity.ok("You successfully uploaded " + file.getOriginalFilename() + "!");
    }

    @Override
    public ResponseEntity<String> delete(String fileName) {
        new File(STORAGE_PATH + fileName).delete();
        return ResponseEntity.ok("You successfully deleted " + fileName + "!");
    }

    @Override
    public ResponseEntity<String> getAll() {
        File folder = new File(STORAGE_PATH);
        //TODO folder.listFiles() can produce NPE
        List<String> files = Arrays.stream(folder.listFiles())
                .map(File::getName)
                .collect(Collectors.toList());
        return ResponseEntity.ok(files.toString());
    }

    @Override
    public ResponseEntity<Resource> get(String fileName) throws MalformedURLException, URISyntaxException {
        Resource file = new UrlResource(new URI("file://" + STORAGE_PATH + fileName));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").body(file);
    }
}
