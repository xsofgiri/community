package com.digitizeads.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;
import com.amazonaws.util.IOUtils;

public class PollyDemo {

	private final AmazonPollyClient polly;
	private Voice voice;
	private static final String SAMPLE = "It's a wonderful place. I am from Mumbai.";

	private static final String AWS_KEY = "AKIAIR5OHQBGCIVAQRYQ";
	private static final String AWS_SECRET = "vaTtsdGNQOKVccK2M/BoObI6utKOeDmkm46doSKL";

	public PollyDemo(Region region) {

		AWSCredentials awsCreds = new AWSCredentials() {

			@Override
			public String getAWSSecretKey() {
				// TODO Auto-generated method stub
				return AWS_SECRET;
			}

			@Override
			public String getAWSAccessKeyId() {
				// TODO Auto-generated method stub
				return AWS_KEY;
			}
		};

		// create an Amazon Polly client in a specific region
		polly = new AmazonPollyClient(awsCreds);
		polly.setRegion(region);
		// Create describe voices request.
		DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

		describeVoicesRequest.setLanguageCode("en-IN");

		// Synchronously ask Amazon Polly to describe available TTS voices.
		DescribeVoicesResult describeVoicesResult = polly.describeVoices(describeVoicesRequest);
		voice = describeVoicesResult.getVoices().get(0);

	}

	public InputStream synthesize(String text, OutputFormat format) throws IOException {
		SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(text).withVoiceId(voice.getId())
				.withOutputFormat(format);
		SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);

		return synthRes.getAudioStream();
	}

	public static void main(String args[]) throws Exception {
		// create the test class
		PollyDemo helloWorld = new PollyDemo(Region.getRegion(Regions.US_EAST_1));
		// get the audio stream
		String fileName = "AWS";

		InputStream speechStream = helloWorld.synthesize(SAMPLE, OutputFormat.Mp3);
		try (OutputStream out = new FileOutputStream("mp3/" + fileName + ".mp3")) {
			IOUtils.copy(speechStream,out);
			System.out.println("Audio content written to file " + fileName + ".mp3");
		}
	}
}
