package com.krakedev.parqueadero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.krakedev.parqueadero.modelo.Auto;
import com.krakedev.parqueadero.modelo.Motocicleta;
import com.krakedev.parqueadero.modelo.TicketCobro;
import com.krakedev.parqueadero.servicios.ServicioCobro;
import com.krakedev.parqueadero.servicios.ServicioVehiculos;

public class ServicioCobroTest {

	@Test
	public void calcularTarifaAutoSinRecargoTest() {
		Auto auto = new Auto("ABC123", "Juan Perez", 4);

		double total = auto.calcularTarifa(4);

		assertEquals(6.00, total, 0.001);
	}

	@Test
	public void calcularTarifaAutoConRecargoTest() {
		Auto auto = new Auto("ABC123", "Juan Perez", 4);

		double total = auto.calcularTarifa(5);

		assertEquals(9.50, total, 0.001);
	}

	@Test
	public void calcularTarifaMotocicletaBajoCilindrajeTest() {
		Motocicleta moto = new Motocicleta("MOT123", "Maria Lopez", 200);

		double total = moto.calcularTarifa(2);

		assertEquals(1.50, total, 0.001);
	}

	@Test
	public void calcularTarifaMotocicletaAltoCilindrajeTest() {
		Motocicleta moto = new Motocicleta("MOT123", "Maria Lopez", 300);

		double total = moto.calcularTarifa(2);

		assertEquals(2.00, total, 0.001);
	}

	@Test
	public void procesarSalidaAutoTest() {
		ServicioVehiculos servicioVehiculos = new ServicioVehiculos();
		ServicioCobro servicioCobro = new ServicioCobro(servicioVehiculos);

		Auto auto = new Auto("ABC123", "Juan Perez", 4);
		servicioVehiculos.ingresarVehiculo(auto);

		TicketCobro ticket = servicioCobro.procesarSalida("ABC123", 5);

		assertNotNull(ticket);
		assertEquals("ABC123", ticket.getVehiculo().getPlaca());
		assertEquals(5, ticket.getHoras());
		assertEquals(9.50, ticket.getTotalPagar(), 0.001);
		assertEquals(0, servicioVehiculos.listarVehiculos().size());
	}

	@Test
	public void procesarSalidaVehiculoInexistenteTest() {
		ServicioVehiculos servicioVehiculos = new ServicioVehiculos();
		ServicioCobro servicioCobro = new ServicioCobro(servicioVehiculos);

		TicketCobro ticket = servicioCobro.procesarSalida("XXX999", 3);

		assertNull(ticket);
	}

	@Test
	public void calcularTotalRecaudadoTest() {
		ServicioVehiculos servicioVehiculos = new ServicioVehiculos();
		ServicioCobro servicioCobro = new ServicioCobro(servicioVehiculos);

		Auto auto = new Auto("ABC123", "Juan Perez", 4);
		Motocicleta moto = new Motocicleta("MOT123", "Maria Lopez", 300);

		servicioVehiculos.ingresarVehiculo(auto);
		servicioVehiculos.ingresarVehiculo(moto);

		servicioCobro.procesarSalida("ABC123", 5);
		servicioCobro.procesarSalida("MOT123", 2);

		double total = servicioCobro.calcularTotalRecaudado();

		assertEquals(11.50, total, 0.001);
	}
}