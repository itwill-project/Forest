package com.example.forest.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nimbusds.jose.shaded.gson.JsonObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SummernoteController {
    
    @RequestMapping("/uploadSummernoteImageFile")
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
        log.info("Starting Image Uploading");
        
        JsonObject jsonObject = new JsonObject();
        
        String fileRoot = "C:\\Users\\ITWILL\\git\\Forest\\src\\main\\resources\\static\\img\\post_image"; //저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();  //오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));   //파일 확장자
                
        String savedFileName = UUID.randomUUID() + extension;   //저장될 파일 명
        
        File targetFile = new File(fileRoot + savedFileName);   
        
        log.info("extension = {}", extension);
        log.info("savedFileName = {}", savedFileName);       
        log.info("targetFile = {}", targetFile);       
        
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);    //파일 저장
            jsonObject.addProperty("url", "/images/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");
                
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);    //저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }
        
        return jsonObject;
    }
    
}
