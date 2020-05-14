package com.transaksi.upload.controller;


import com.transaksi.upload.model.DBFile;
import com.transaksi.upload.model.Transaksi;
import com.transaksi.upload.payload.UploadFileResponse;
import com.transaksi.upload.repository.TransaksiRepository;
import com.transaksi.upload.service.DBFileStorageService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/transaksi")
public class TransaksiController {
    private static final Logger logger = LoggerFactory.getLogger(TransaksiController.class);

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private DBFileStorageService dbFileStorageService;

    private boolean statusUpload;

    private Transaksi transaksiTemp;

    @PostMapping("/proses")
    private ResponseEntity<?> proses(@RequestBody Transaksi transaksi){
        transaksiTemp = transaksi;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","memproses transaksi");
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }

    @PostMapping("/proses/uploadFoto")
    private UploadFileResponse uploadFileResponse(@RequestParam("file") MultipartFile file){
        DBFile dbFile = dbFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("transaksi/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        statusUpload = true;


        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());

    }


    @PostMapping("/proses/saveTransaksi")
    private ResponseEntity<?> save(){
        if(statusUpload == true){
            transaksiRepository.save(transaksiTemp);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message","transaction successful");
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message","transaction failed");
            return new ResponseEntity<>(jsonObject,HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    @PostMapping("/lansgung")
    public ResponseEntity<?> postTransaksi(@RequestBody Transaksi transaksi){
        transaksiRepository.save(transaksi);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","transaksi berhasil");
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }


}
