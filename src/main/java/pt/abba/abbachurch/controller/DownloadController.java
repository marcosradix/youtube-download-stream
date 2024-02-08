package pt.abba.abbachurch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.abba.abbachurch.service.DownloadService;

@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @GetMapping
    public ResponseEntity<String> download(@RequestParam("url") String urlVideo) {
        var message = "downloading...";
        try {
            downloadService.download(urlVideo);
        } catch (Exception e) {
            message = e.getMessage();
        }
        return ResponseEntity.ok(message);
    }
}
