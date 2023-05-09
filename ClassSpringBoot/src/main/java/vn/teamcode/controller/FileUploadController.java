package vn.teamcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import vn.teamcode.models.ResponseObject;
import vn.teamcode.services.IStorageService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "api/v1/fileUpload")
public class FileUploadController {
    // Inject Storage Service here
    @Autowired
    private IStorageService storageService;
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file) {
        try {
            // save files to a folder => user a service
            String generatedFileName = storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Upload file successfully", generatedFileName)
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Faled", exception.getMessage(), "")
            );
        }
    }

    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<byte[]> readDataFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadedFiles() {
        try {
            List<String> urls = storageService.loadAll()
                    .map(path -> {
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDataFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity
                    .ok(new ResponseObject("Success", "List files successfully", urls));
        } catch (Exception exception) {
            return ResponseEntity
                    .ok(new ResponseObject("Success", "List files failed", new String[] {}));
        }
    }
}
