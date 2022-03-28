import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceManager {
	public static void save(final Serializable theData, final String theFilename) throws IOException {	
		try(ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(theFilename)))) {
			outputStream.writeObject(theData);
		}
	}
	
	public static Object load(final String theFilename) throws ClassNotFoundException, IOException {
		try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(theFilename)))) {
			return inputStream.readObject();
		}
	}
}