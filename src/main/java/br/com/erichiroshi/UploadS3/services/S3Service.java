package br.com.erichiroshi.UploadS3.services;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import br.com.erichiroshi.UploadS3.dto.ObjectS3DTO;

@Service
public class S3Service {

	private static Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	public void uploadFile(String localFilePath) {

		File file = new File(localFilePath);
		LOG.info("Upload start");

		s3Client.putObject(new PutObjectRequest(bucketName, "test.jpg", file));

		LOG.info("Upload end");
	}

	public void listObjects() {

		ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request();
		listObjectsV2Request.setBucketName(bucketName);

		ListObjectsV2Result listObjectsV2 = s3Client.listObjectsV2(listObjectsV2Request);

		List<S3ObjectSummary> objectSummaries = listObjectsV2.getObjectSummaries();

//		List<String> fileNames = objectSummaries.stream().map(x -> x.getKey()).toList();
		List<ObjectS3DTO> listDTO = objectSummaries.stream().map(x -> new ObjectS3DTO(x)).toList();

//		System.out.println("Number of objects in the bucket: " + objectSummaries.stream().count());
//		objectSummaries.stream().forEach(System.out::println);
//		
//		System.out.println("Number of objects in the bucket: " + fileNames.stream().count());
//		fileNames.stream().forEach(System.out::println);

		System.out.println("Number of objects in the bucket: " + listDTO.stream().count());
		listDTO.stream().forEach(System.out::println);

	}
}