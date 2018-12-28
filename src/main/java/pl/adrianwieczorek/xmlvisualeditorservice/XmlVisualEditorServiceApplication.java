package pl.adrianwieczorek.xmlvisualeditorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class XmlVisualEditorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlVisualEditorServiceApplication.class, args);
	}
}
