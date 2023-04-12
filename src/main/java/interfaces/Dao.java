package interfaces;

import java.util.List;

public interface Dao <T>{
	public int insert (T t);
	public T read (int id);
	public List<T> getAll();
	public int update(T t);
	public int delete(T t);
}
