package at.fwuick.harryshofladen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptStatementFailedException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class DaheimDatabaseInitializer {

  private static final String SQLFILE = "/db-init.sql";

@Autowired
  JdbcTemplate jdbcTemplate;
  

	public void run(){
		try (Connection con = jdbcTemplate.getDataSource().getConnection();
				InputStream is = getClass().getResourceAsStream(SQLFILE)) {

			if (is == null) {
				throw new FileNotFoundException(SQLFILE + " not found");
			}
			executeSQL(con, is);
		}
		catch (ScriptStatementFailedException e){
			throw new RuntimeException("Database Init failed", e);
		} catch (IOException | SQLException e) {
			throw new RuntimeException("Database Init failed", e);
		} 
	}


private void executeSQL(Connection con, InputStream is) {
	InputStreamResource rus = new InputStreamResource(is);
	ScriptUtils.executeSqlScript(con, rus);
}
}
