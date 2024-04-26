package br.com.byteup.hellodocker.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RequestMapping("aws-S3")
@RestController
public class HelloDockerController {

	@Autowired
	private AmazonS3 s3Client;

	//private String bucketName = "bucket name";

	@PostMapping("upload/file")
	public String uploadFile(@RequestParam(name = "file") MultipartFile file) throws IOException {

		File modifiedFile = new File(file.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(modifiedFile);
		outputStream.write(file.getBytes());

		String fileName = file.getOriginalFilename();

		s3Client.putObject(bucketName, fileName, modifiedFile);

		modifiedFile.delete();
        return fileName + "\n UPLOAD DO ARQUIVO REALIZADO COM SUCESSO NO BUCKET S3";
    }

	@GetMapping("download/file")
	public ResponseEntity<ByteArrayResource> donwloadFileFromS3(@RequestParam(name = "file") String fileName) throws IOException {
		S3Object s3Object = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();

		byte[] byteArray = IOUtils.toByteArray(inputStream);

		ByteArrayResource resource = new ByteArrayResource(byteArray);

		return ResponseEntity.ok()
				.contentLength(byteArray.length)
				.header("content-type", "application/octet-stream")
				.header("content-disposition", "attachment;fileName="+ fileName)
				.body(resource);
    }

	@DeleteMapping("delete/file")
	public String deleteFileFromS3(@RequestParam(name = "file") String fileName){
		s3Client.deleteObject(bucketName, fileName);
		return fileName + "\n ARQUIVO DELETADO DO BUCKET S3 COM SUCESSO!";
	}

	@GetMapping()
	public String helloDocker() {
		return "bora aprender sobre s3";
	}
}
