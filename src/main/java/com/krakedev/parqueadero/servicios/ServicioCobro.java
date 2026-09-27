package com.krakedev.parqueadero.servicios;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.parqueadero.modelo.TicketCobro;
import com.krakedev.parqueadero.modelo.Vehiculo;

@Service
public class ServicioCobro {

	private final ServicioVehiculos servicioVehiculos;

	private ArrayList<TicketCobro> historicoTickets = new ArrayList<TicketCobro>();

	public ServicioCobro(ServicioVehiculos servicioVehiculos) {
		this.servicioVehiculos = servicioVehiculos;
	}

	public TicketCobro procesarSalida(String placa, int horas) {
		if (horas <= 0) {
			return null;
		}

		Vehiculo vehiculo = servicioVehiculos.retirarVehiculo(placa);

		if (vehiculo == null) {
			return null;
		}

		double total = vehiculo.calcularTarifa(horas);

		String codigoTicket = generarCodigoTicket();

		TicketCobro ticket = new TicketCobro(codigoTicket, vehiculo, horas, total);

		historicoTickets.add(ticket);

		return ticket;
	}

	private String generarCodigoTicket() {
		int numero = (int) (Math.random() * 900 + 100);
		String codigo = "TCK-" + numero;
		return codigo;
	}

	public double calcularTotalRecaudado() {
		double total = 0;

		for (TicketCobro ticket : historicoTickets) {
			total = total + ticket.getTotalPagar();
		}

		return total;
	}

	public ArrayList<TicketCobro> listarTickets() {
		return historicoTickets;
	}
}