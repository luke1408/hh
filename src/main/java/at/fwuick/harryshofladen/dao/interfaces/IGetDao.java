package at.fwuick.harryshofladen.dao.interfaces;

public interface IGetDao<T> extends IDefaultResultMapperDao<T>{
	public T get(long id);
	public boolean exists(long id);
}
