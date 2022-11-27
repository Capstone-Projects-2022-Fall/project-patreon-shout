package com.patreonshout.rest;

import com.google.common.net.HttpHeaders;
import com.patreonshout.PSException;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.utils.GaussianFilterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class ImageSvc extends BaseSvc {

	/**
	 * An autowired Spring component that endpoints utilize to send or receive data from the database
	 */
	@Autowired
	WebAccountFunctions webAccountFunctions;

	@GetMapping(path = "/images/blur", produces = "image/png")
	public ResponseEntity<?> blurImage(
			@RequestParam("login_token") String loginToken,
			@RequestParam("image_url") String imageUrl,
			@RequestParam("radius") int radius
	) throws PSException {
		WebAccount webAccount = webAccountFunctions.getAccount(loginToken);

		if (radius <= 0)
			throw new PSException(HttpStatus.BAD_REQUEST, "'radius' must be an integer greater than 0");

		try {
			// Acquire image via URL
			URL url = new URL(imageUrl);
			BufferedImage bufferedImage = ImageIO.read(url);

			// Create filter and container
			GaussianFilterUtil blur = new GaussianFilterUtil(radius);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			// Create filtered image into ByteArrayOutputStream obj, then convert to Byte Array
			ImageIO.write(blur.filter(bufferedImage,  null), "png", byteArrayOutputStream);
			byte[] imageBytes = byteArrayOutputStream.toByteArray();

			// Return Byte Array obj, giving it a content type and generic file name
			return ResponseEntity
					.status(HttpStatus.OK)
					.header(HttpHeaders.CONTENT_DISPOSITION, webAccount.getWebAccountId() + ".png")
					.contentType(MediaType.IMAGE_PNG)
					.body(imageBytes);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new PSException(HttpStatus.BAD_REQUEST, "Image URL is malformed.");
		} catch (IOException e) {
			throw new PSException(HttpStatus.BAD_REQUEST, "Image URL could not be processed into an actual image.");
		}
	}
}
