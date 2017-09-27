package at.fwuick.harryshofladen.service.interfaces;

import at.fwuick.harryshofladen.exceptions.HofladenException;

public interface IOrderService {

	void order(int userId, long productId, int amount) throws HofladenException;

}
