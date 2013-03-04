package info.sorokine.utf8glob;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
			try {
				Files.createFile(Paths.get("abc.html"));
				Files.createFile(Paths.get("эюя.html")); // UTF-8 string
				Files.createFile(Paths.get("ой-ёй-ЁЙ.html")); // UTF-8 string with composite characters
			} catch (FileAlreadyExistsException e) {}

			match("glob:*.html");
			match("glob:a*.html");
			match("glob:э*.html");
			match("glob:о*.html");
			match("glob:*Й.html");

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void match(String glob) throws IOException {
    	System.err.println("Glob: " + glob);
        PathMatcher pmatcher = FileSystems.getDefault().getPathMatcher(glob);
        DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get("."));
        for (Path file : dir) 
			if (pmatcher.matches(file.getFileName())) 
				System.err.println("" + file + " canRead? " + file.toFile().canRead());
    	System.err.println();
	}
}
