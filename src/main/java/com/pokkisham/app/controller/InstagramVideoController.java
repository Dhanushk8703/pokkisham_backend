package com.pokkisham.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokkisham.app.model.InstagramVideo;
import com.pokkisham.app.service.InstagramVideoService;

@RestController
@RequestMapping("/api/video")
public class InstagramVideoController {
    
     @Autowired
    private InstagramVideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<InstagramVideo> uploadVideo(@RequestBody InstagramVideo videoUrl) {
        return ResponseEntity.ok(videoService.saveVideo(videoUrl));
    }

    @GetMapping("/current")
    public ResponseEntity<InstagramVideo> getVideo() {
        InstagramVideo video = videoService.getVideo();
        if (video == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(video);
    }
}
