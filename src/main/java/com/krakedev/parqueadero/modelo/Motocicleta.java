package com.krakedev.parqueadero.modelo;

public class Motocicleta extends Vehiculo {

	private int cilindraje;

	public Motocicleta() {
		super();
	}

	public Motocicleta(String placa, String propietario, int cilindraje) {
		super(placa, propietario);
		this.cilindraje = cilindraje;
	}

	@Override
	public double calcularTarifa(int horasPermanencia) {
		double tarifaPorHora = 0.75;

		if (cilindraje > 250) {
			tarifaPorHora = 1.00;
		}

		double total = horasPermanencia * tarifaPorHora;
		return total;
	}

	@Override
	public String toString() {
		return "Motocicleta [placa=" + getPlaca() + ", propietario=" + getPropietario() + ", horaIngreso="
				+ getHoraIngreso() + ", cilindraje=" + cilindraje + "]";
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
}