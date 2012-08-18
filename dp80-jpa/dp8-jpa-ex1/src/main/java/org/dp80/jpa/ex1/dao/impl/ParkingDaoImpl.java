
package org.dp80.jpa.ex1.dao.impl;

import org.dp80.jpa.ex1.dao.ParkingDao;
import org.dp80.jpa.ex1.model.ParkingSpace;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingDaoImpl  extends AbstractBaseDaoImpl<ParkingSpace> implements ParkingDao {

	@Override
	public Class<ParkingSpace> getEntityClass() {
	
		return ParkingSpace.class;
	}
	
}
