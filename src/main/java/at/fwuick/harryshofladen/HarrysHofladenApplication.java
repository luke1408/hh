package at.fwuick.harryshofladen;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HarrysHofladenApplication implements CommandLineRunner{
	@Autowired
	DaheimDatabaseInitializer dbInit;
	public static void main(String[] args) {
		SpringApplication.run(HarrysHofladenApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		//dbInit.run();
	}
}
