// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * Reads resource files from the res/ directory in the .jar file.
 * @author adam
 */
public class ResourceLoader {
	
	/**
	 * Loads an Image resource. If the resource cannot be loaded, an error
	 * message will be printed to stderr, and the program will exit.
	 * @param filename Name of the resource file to load.
	 * @return The loaded image
	 */
	public static BufferedImage loadImage(String filename)
	{
		BufferedImage result = null;
		try
		{
			InputStream stream = ResourceLoader.class.
				getResourceAsStream(filename);
			if (stream == null)
			{
				throw new IOException("File not found");
			}
			result = ImageIO.read(stream);
		}
		catch (IOException ex)
		{
			System.err.println("FIXME: Failed to load resource " +
				filename);
			String msg = ex.getLocalizedMessage();
			if (msg != null && !msg.equals(""))
			{
				System.err.print("Message: ");
				System.err.println(msg);
			}
			System.err.println("Stack trace:");
			ex.printStackTrace();
			System.exit(2);
		}
		
		return result;
	}
	
	/**
	 * Loads a text file resource as a single string.
	 * @param filename Name of the file to load, relative to the /res/
	 *                 directory.
	 * @return A single String object containing the contents of the text
	 *         file.
	 */
	public static String loadText(String filename)
	{
		String result = null;
		InputStream stream = null;
		try
		{
			stream = ResourceLoader.class.
				getResourceAsStream(filename);
			if (stream == null)
			{
				throw new IOException("File not found");
			}
		}
		catch (IOException ex)
		{
			System.err.println("FIXME: Failed to load resource " +
				filename);
			System.err.println("Stack trace:");
			String msg = ex.getLocalizedMessage();
			if (msg != null && !msg.equals(""))
			{
				System.err.print("Message: ");
				System.err.println(msg);
			}
			System.err.println("Stack trace:");
			ex.printStackTrace();
			System.exit(2);
		}
		Scanner scanner = new Scanner(stream);
		scanner.useDelimiter("\\A");
		result = (scanner.hasNext()) ? scanner.next() : "";
		
		return result;
	}
	
	/**
	 * Loads a text file resource as an array of Strings.
	 * @param filename Name of the file to load, relative to the /res/
	 *                 directory.
	 * @param delimiter The string that separates records in the file, e.g.
	 *                  " " or "\n".
	 * @return The contents of the text file, as an array of strings.
	 */
	public List<String> loadText(String filename, String delimiter)
	{
		ArrayList<String> result = new ArrayList<>();
		InputStream stream = null;
		try
		{
			stream = ResourceLoader.class.
				getResourceAsStream(filename);
			if (stream == null)
			{
				throw new IOException("File not found");
			}
		}
		catch (IOException ex)
		{
			System.err.println("FIXME: Failed to load resource " +
				filename);
			System.err.println("Stack trace:");
			String msg = ex.getLocalizedMessage();
			if (msg != null && !msg.equals(""))
			{
				System.err.print("Message: ");
				System.err.println(msg);
			}
			System.err.println("Stack trace:");
			ex.printStackTrace();
			System.exit(2);
		}
		Scanner scanner = new Scanner(stream);
		scanner.useDelimiter(delimiter);
		while (scanner.hasNext())
		{
			result.add(scanner.next());
		}
		
		return result;
	}
}
// EOF