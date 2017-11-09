package at.fwuick.harryshofladen.view.converter.interfaces;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IDaoViewConverter<DaoModel, ViewModel> {
	public ViewModel convert(DaoModel object);
	public default Collection<ViewModel> convert(Collection<DaoModel> list){
		return list.stream().map(this::convert).collect(Collectors.toList());
	}
	
	public default Stream<ViewModel> convert(Stream<DaoModel> stream){
		return stream.map(this::convert);
	}
}
