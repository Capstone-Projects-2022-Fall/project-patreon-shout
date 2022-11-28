package com.patreonshout.utils;

import com.patreonshout.PSException;
import org.springframework.http.HttpStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Contains image utility functions
 */
public class ImageUtil {

	/**
	 * Generated an image with a Gaussian Blurred background and text overlaying it in the middle with the desired text color
	 *
	 * @param imageUrl  image URL
	 * @param radius    blur amount (1 to 100)
	 * @param text      message to display on image
	 * @param textColor HEX string color the message should take
	 * @return array of bytes representing the image
	 */
	public static byte[] GenerateBlurredImage(String imageUrl, int radius, String text, String textColor) throws PSException {
		try {
			// Acquire image via URL
			URL url = new URL(imageUrl);
			BufferedImage bufferedImage = ImageIO.read(url);

			// Check if file is too big
			if (bufferedImage.getWidth() > 1024 || bufferedImage.getHeight() > 1024)
				throw new PSException(HttpStatus.BAD_REQUEST, "Image dimension maximum is 1024x1024");

			// * Source: https://stackoverflow.com/questions/11447035/java-get-image-extension-type-using-bufferedimage-from-url
			// Acquire image file type
			int pushbackLimit = 100;
			InputStream urlStream = url.openStream();
			PushbackInputStream pushUrlStream = new PushbackInputStream(urlStream, pushbackLimit);
			byte[] firstBytes = new byte[pushbackLimit];

			// download the first initial bytes into a byte array, which we will later pass to URLConnection.guessContentTypeFromStream
			pushUrlStream.read(firstBytes);
			// push the bytes back onto the PushbackInputStream so that the stream can be read by ImageIO reader in its entirety
			pushUrlStream.unread(firstBytes);

			// Pass the initial bytes to URLConnection.guessContentTypeFromStream in the form of a ByteArrayInputStream, which is mark supported.
			String imageType;
			ByteArrayInputStream bais = new ByteArrayInputStream(firstBytes);
			String mimeType = URLConnection.guessContentTypeFromStream(bais);

			if (mimeType.startsWith("image/"))
				imageType = mimeType.substring("image/".length());
			else // Stop if we could not determine the image file type
				throw new PSException(HttpStatus.BAD_REQUEST, "Image file type could not be determined.");

			// Create filter and container
			GaussianFilterUtil blur = new GaussianFilterUtil(radius);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			// Blur image then resize to 512x512
			bufferedImage = blur.filter(bufferedImage, null);
			bufferedImage = scaleImage(bufferedImage, 512, 512);

			// Add text on top of blurred image
			Font font = new Font("Arial White", Font.BOLD, 30);
			Graphics graphics = bufferedImage.getGraphics();

			// Create text graphic
			String cssStyle = "padding: 4px; text-align: center; margin-bottom: " + ((text.length() / 5) * 4) + "px; color: " + textColor;
			JLabel label = new JLabel("<html><div style='" + cssStyle + "'>" + text + "</div></html>");
			label.setFont(font);
			label.setSize(512, 512);
			label.paint(graphics);

			// Create filtered image into ByteArrayOutputStream obj, then return as Byte Array
			ImageIO.write(bufferedImage, imageType, byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new PSException(HttpStatus.BAD_REQUEST, "Image URL is malformed.");
		} catch (IOException e) {
			throw new PSException(HttpStatus.BAD_REQUEST, "Image URL could not be processed into an actual image.");
		}
	}

	// * Source: https://stackoverflow.com/questions/9417356/bufferedimage-resize
	private static BufferedImage scaleImage(BufferedImage src, int w, int h) {
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		int x, y;
		int ww = src.getWidth();
		int hh = src.getHeight();
		int[] ys = new int[h];
		for (y = 0; y < h; y++)
			ys[y] = y * hh / h;
		for (x = 0; x < w; x++) {
			int newX = x * ww / w;
			for (y = 0; y < h; y++) {
				int col = src.getRGB(newX, ys[y]);
				img.setRGB(x, y, col);
			}
		}
		return img;
	}
}
