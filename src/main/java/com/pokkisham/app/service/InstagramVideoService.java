package com.pokkisham.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokkisham.app.model.InstagramVideo;
import com.pokkisham.app.repository.InstagramVideoRepository;

@Service
public class InstagramVideoService {
    
    @Autowired
    private InstagramVideoRepository instagramVideoRepository;

    public InstagramVideo saveVideo(InstagramVideo videoUrl) {
        instagramVideoRepository.deleteAll(); // remove old one
        
        return instagramVideoRepository.save(videoUrl);
    }

    public InstagramVideo getVideo() {
        List<InstagramVideo> videos = instagramVideoRepository.findAll();
        return videos.isEmpty() ? null : videos.get(0);
    }
}
