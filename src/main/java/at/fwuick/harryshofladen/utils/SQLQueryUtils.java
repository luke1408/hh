package at.fwuick.harryshofladen.utils;

public class SQLQueryUtils {
	
	private SQLQueryUtils(){
		
	}
	
	public static String concatParts(String... parts){
		String  separator = " ";
		return concatParts(separator, parts);
	}

	private static String concatParts(String separator, String... parts) {
		StringBuilder query = new StringBuilder(parts[0]);
		for(int i=1; i < parts.length; i++){
			query.append(separator);
			query.append(parts[i]);
		}
		return query.toString();
	}
	
	public static String concatPartsWithOr(String... parts){
		String  separator = " or ";
		return concatParts(separator, parts);
	}
	
	public static String concatPartsWithAnd(String... parts){
		String  separator = " and ";
		return concatParts(separator, parts);
	}

}
