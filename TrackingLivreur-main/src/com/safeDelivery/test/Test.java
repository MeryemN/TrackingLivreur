package com.safeDelivery.test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import com.safeDelivery.model.Zone;
import com.safeDelivery.service.impl.ZoneServiceimpl;
import com.safeDelivery.utils.SingletonConnexion;



public class Test {
	public static void main(String[] args) throws NoSuchAlgorithmException {
	Connection connection = SingletonConnexion.startConnection();
		ZoneServiceimpl zoneservice = new ZoneServiceimpl(connection);

		Zone zone = zoneservice.findById(1);
		System.out.println("le resultat "+zone.getNom());
		
		SingletonConnexion.closeConnection(connection);

	}
}
