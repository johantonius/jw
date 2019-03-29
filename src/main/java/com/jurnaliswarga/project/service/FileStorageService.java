package com.jurnaliswarga.project.service;


import com.jurnaliswarga.project.model.Videos;
import com.jurnaliswarga.project.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStorageService {

//    private final Path fileStorageLocation;
//
//    @Autowired
//    private VideosRepository videosRepository;
//
//    public Videos storeFile(MultipartFile file) {
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            Videos videos = new Videos(fileName,  file.getBytes());
//
//            return videosRepository.save(videos);
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }
//
//    public Videos getFile(Long fileId) {
//        return videosRepository.findById(fileId)
//                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
//    }
}
