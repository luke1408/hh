package at.fwuick.harryshofladen.dao;

public interface IGetDao<T> extends IDefaultResultMapperDao<T>{
	public T get(long id);
}
