package com.digitizeads.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class UploadFile {

	/*
	 * private static final String AWS_KEY = "AKIAJSAJQNKWCHYCIABA"; private
	 * static final String AWS_SECRET =
	 * "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"; private static final String
	 * BUCKET = "onvider-assets"; private final static String FOLDER_SUFFIX =
	 * "/";
	 */ 
 
	private static final String AWS_KEY = "AKIA6KC4GHRRUJUKFBGD";
	private static final String AWS_SECRET = "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";
	
	private static final String BUCKET = "adwit-community";//SpringProperty.getPropValue("bucketName")!=null?SpringProperty.getPropValue("bucketName"):"staging-leadnow";
	
	private final static String FOLDER_SUFFIX = "/";

	public AmazonS3 getAmazonS3Client() {
		AmazonS3Client client = new AmazonS3Client(new BasicAWSCredentials(
				AWS_KEY, AWS_SECRET));
		AmazonS3 s3 = client;
		Region usWest2 = Region.getRegion(Regions.AP_SOUTH_1);
		s3.setRegion(usWest2);

		return s3;
	}

	public void createFolder(String foldername, AmazonS3 client) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// Create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// Create a PutObjectRequest passing the foldername suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET,
				"plt_hpr_host_event_pics/" + foldername + FOLDER_SUFFIX,
				emptyContent, metadata);
		// InputStream in = null;
		try {
			System.out.println("foldername : " + foldername);

			client.putObject(putObjectRequest
					.withCannedAcl(CannedAccessControlList.PublicRead));

			// in.close();
		} catch (Exception e) {
			System.out.println("createFolder exception : " + e);
		}

	}
 
	public S3Object addUserCouponImage(String destinationFile, File sourceFile,
			AmazonS3 client) {

		// String fileName = "MyWorks/" +folderName+"/ interaction_icon.png";
		client.putObject(new PutObjectRequest(BUCKET, "coupons/"
				+ destinationFile, sourceFile)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		return client.getObject(BUCKET, "coupons/"
				+ destinationFile);

	}
	
	
	public S3Object addCategoryImage(String destinationFile, File sourceFile,
			AmazonS3 client) {

		// String fileName = "MyWorks/" +folderName+"/ interaction_icon.png";
		client.putObject(new PutObjectRequest(BUCKET, "category/"
				+ destinationFile, sourceFile)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		return client.getObject(BUCKET, "category/"
				+ destinationFile);

	}
	
	public S3Object addBusinessImage(String destinationFile, File sourceFile,
			AmazonS3 client) {

		// String fileName = "MyWorks/" +folderName+"/ interaction_icon.png";
		client.putObject(new PutObjectRequest(BUCKET, "business/"
				+ destinationFile, sourceFile)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		return client.getObject(BUCKET, "business/"
				+ destinationFile);

	}

	
}
