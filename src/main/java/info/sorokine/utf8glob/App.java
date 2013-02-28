package info.sorokine.utf8glob;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
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
			} catch (FileAlreadyExistsException e) {}

			match("glob:*.html");
			match("glob:a*.html");
			match("glob:э*.html");

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void match(String glob) {
    	System.err.println("Glob: " + glob);
        PathMatcher pmatcher = FileSystems.getDefault().getPathMatcher(glob);
        File dir = new File(".");
        for (File file : dir.listFiles()) 
			if (pmatcher.matches(file.toPath().getFileName()))
				System.err.println("" + file);
    	System.err.println();
	}
}
