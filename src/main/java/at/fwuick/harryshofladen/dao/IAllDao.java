package at.fwuick.harryshofladen.dao;

import java.util.List;

public interface IAllDao<T> extends IDefaultResultMapperDao<T> {
	
	public List<T> all();
}
