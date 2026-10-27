import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class Project {
	public static void main(String[] args) throws IOException {
		// Put index.html in the directory from which this program is run.
		Path htmlFile = Path.of("index.html");
		if (!Files.isRegularFile(htmlFile)) {
			throw new IOException("HTML file not found: " + htmlFile.toAbsolutePath());
		}

		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		server.createContext("/", exchange -> {
			byte[] content = Files.readAllBytes(htmlFile);
			exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
			exchange.sendResponseHeaders(200, content.length);
			try (var response = exchange.getResponseBody()) {
				response.write(content);
			}
		});
		server.start();
		System.out.println("Serving index.html at http://localhost:8080/");
	}
}
