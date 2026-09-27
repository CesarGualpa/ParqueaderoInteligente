package com.krakedev.parqueadero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.parqueadero.modelo.Auto;
import com.krakedev.parqueadero.modelo.Motocicleta;
import com.krakedev.parqueadero.modelo.Vehiculo;
import com.krakedev.parqueadero.servicios.ServicioVehiculos;

public class ServicioVehiculosTest {

	@Test
	public void ingresarVehiculoValidoTest() {
		ServicioVehiculos servicio = new ServicioVehiculos();

		Auto auto = new Auto("ABC123", "Juan Perez", 4);

		boolean resultado = servicio.ingresarVehiculo(auto);

		assertTrue(resultado);
		assertEquals(1, servicio.listarVehiculos().size());
	}

	@Test
	public void ingresarVehiculoDuplicadoTest() {
		ServicioVehiculos servicio = new ServicioVehiculos();

		Auto auto1 = new Auto("ABC123", "Juan Perez", 4);
		Motocicleta moto1 = new Motocicleta("ABC123", "Maria Lopez", 200);

		servicio.ingresarVehiculo(auto1);

		boolean resultado = servicio.ingresarVehiculo(moto1);

		assertFalse(resultado);
		assertEquals(1, servicio.listarVehiculos().size());
	}

	@Test
	public void buscarPorPlacaExistenteTest() {
		ServicioVehiculos servicio = new ServicioVehiculos();

		Auto auto = new Auto("ABC123", "Juan Perez", 4);
		servicio.ingresarVehiculo(auto);

		Vehiculo resultado = servicio.buscarPorPlaca("ABC123");

		assertEquals(auto, resultado);
	}

	@Test
	public void buscarPorPlacaInexistenteTest() {
		ServicioVehiculos servicio = new ServicioVehiculos();

		Vehiculo resultado = servicio.buscarPorPlaca("XXX999");

		assertNull(resultado);
	}

	@Test
	public void retirarVehiculoExistenteTest() {
		ServicioVehiculos servicio = new ServicioVehiculos();

		Auto auto = new Auto("ABC123", "Juan Perez", 4);
		servicio.ingresarVehiculo(auto);

		Vehiculo retirado = servicio.retirarVehiculo("ABC123");

		assertEquals(auto, retirado);
		assertEquals(0, servicio.listarVehiculos().size());
	}

	@Test
	public void retirarVehiculoInexistenteTest() {
		ServicioVehiculos servicio = new ServicioVehiculos();

		Vehiculo retirado = servicio.retirarVehiculo("XXX999");

		assertNull(retirado);
	}

	@Test
	public void capacidadMaximaTest() {
		ServicioVehiculos servicio = new ServicioVehiculos();

		for (int i = 1; i <= 10; i++) {
			Auto auto = new Auto("ABC" + i, "Propietario " + i, 4);
			servicio.ingresarVehiculo(auto);
		}

		Auto autoExtra = new Auto("EXTRA1", "Propietario Extra", 4);

		boolean resultado = servicio.ingresarVehiculo(autoExtra);

		assertFalse(resultado);
		assertEquals(10, servicio.listarVehiculos().size());
	}
}