package at.fwuick.harryshofladen.dao.interfaces;

public interface IDao {
	
	public String tableName();
	
	public default String resolveTableName(String query){
		return query.replace("%table", tableName());
	}
	
	public default String query(String query){
		return resolveTableName(query);
	}
	
	public default Object[] params(Object... objects){
		return objects;
	}
	
}
