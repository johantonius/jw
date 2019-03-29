package com.jurnaliswarga.project.controller;

import java.io.*;
import java.util.Map;

import com.cloudinary.Cloudinary;

import com.cloudinary.utils.ObjectUtils;
import com.jurnaliswarga.project.config.CloudinaryConfig;
import com.jurnaliswarga.project.model.User;
import com.jurnaliswarga.project.model.Videos;
import com.jurnaliswarga.project.repository.VideosRepository;
import com.jurnaliswarga.project.service.CloudinaryService;
import com.jurnaliswarga.project.service.MapValidationErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping(value="/cloud")
public class VideosController
{
    @Autowired
    VideosRepository videosRepository;

    @Autowired
    CloudinaryConfig cloudc;


    private static String UPLOAD_DIR = "uploads";

    private static final Logger logger = LoggerFactory.getLogger(VideosController.class);
    @Autowired
    private CloudinaryService cloudinaryService;

//    @PostMapping("/uploadVideo")
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        String url = cloudinaryService.uploadFile(file);
//        return "File uploaded successfully: File path :  " + url;
//    }

//    @PostMapping("/uploadFile")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//        Videos videos = fileStorageService.storeFile(file);
//        String id = Long.toString(videos.getVideo_id());
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(id)
//                .toUriString();
//
//        return new UploadFileResponse(videos.getTitle(), fileDownloadUri,
//                file.getContentType(), file.getSize());
//    }
//@Autowired
//private MapValidationErrorService mapValidationErrorService;
//
//    @PostMapping("/file")
//    public String uploadVideo(@RequestParam("file")MultipartFile file, HttpServletRequest req){
//        try {
//            String fileName = file.getOriginalFilename();
//            String path = req.getServletContext().getRealPath("")+UPLOAD_DIR+File.separator+fileName;
//            saveFile(file.getInputStream(), path);
//            return fileName;
//        }catch (Exception e){
//            return e.getMessage();
//        }
//    }
//
//    private void saveFile(InputStream inputStream, String path){
//        try {
//            OutputStream outputStream = new FileOutputStream(new File(path));
//            int read = 0;
//            byte[]bytes = new byte[1024];
//            while ((read=inputStream.read(bytes))!=-1){
//                outputStream.write(bytes, 0, read);
//            }
//            outputStream.flush();
//            outputStream.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @PostMapping("/uploadFile")
////    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
////
////        File uploadedFile = new File("./uploadDir", file.getOriginalFilename());
////
////        try {
////            uploadedFile.createNewFile();
////            FileOutputStream fileOutputStream = new FileOutputStream(uploadedFile);
////            fileOutputStream.write(file.getBytes());
////            fileOutputStream.close();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        return new ResponseEntity<Object>("file Uplaoded succesfully", HttpStatus.OK);
////    }
    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(@ModelAttribute Videos videos,
                                         @RequestParam("file")MultipartFile file,
                                         BindingResult result){

//        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
//        if (errorMap != null) return errorMap;
//        if(file.isEmpty()){
//            return null;
//        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            videos.setUrl(uploadResult.get("url").toString());
            Videos vid =  videosRepository.save(videos);
            return new ResponseEntity<Videos>(vid, HttpStatus.CREATED);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("failed");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

//    @PostMapping("/upload")
//    public String uploadVideo(@ModelAttribute Videos videos,
//                              @RequestParam("file")MultipartFile file){
//        if(file.isEmpty()){
//            return "redirect:/add";
//        }
//        try {
//            Map uploadResult = cloudc.upload(file.getBytes(),
//                    ObjectUtils.asMap("resourcetype", "auto"));
//            videos.setUrl(uploadResult.get("url").toString());
//            videosRepository.save(videos);
//        }catch (IOException e){
//            e.printStackTrace();
//            System.out.println("failed");
//            return "redirect:/upload"+" gabisa neh";
//
//        }
////        return "redirect:/" + "yak uploaded";
//        return null;
//    }
    
}