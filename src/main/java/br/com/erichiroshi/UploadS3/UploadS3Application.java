package br.com.erichiroshi.UploadS3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.erichiroshi.UploadS3.services.S3Service;

@SpringBootApplication
public class UploadS3Application implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(UploadS3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		s3Service.uploadFile("D:\\temp\\café.jpg");
		
		s3Service.listObjects();
	}
}