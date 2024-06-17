package br.com.erichiroshi.UploadS3.dto;

import java.util.Date;

import com.amazonaws.services.s3.model.S3ObjectSummary;

public class ObjectS3DTO {

	protected String key;
	protected long size;
	protected Date lastModified;

	public ObjectS3DTO(S3ObjectSummary s3) {
		this.key = s3.getKey();
		this.size = s3.getSize();
		this.lastModified = s3.getLastModified();
	}

	@Override
	public String toString() {
		return key + ", size = " + size + ", lastModified = " + lastModified;
	}
	
	

}
