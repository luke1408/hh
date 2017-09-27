package at.fwuick.harryshofladen.view.converter.interfaces;

public interface IDaoViewConverter<DaoModel, ViewModel> {
	public ViewModel convert(DaoModel object);
}
