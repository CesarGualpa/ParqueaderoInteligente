package com.krakedev.parqueadero.controladores;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.parqueadero.modelo.Auto;
import com.krakedev.parqueadero.modelo.Motocicleta;
import com.krakedev.parqueadero.modelo.Vehiculo;
import com.krakedev.parqueadero.servicios.ServicioVehiculos;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

	private final ServicioVehiculos servicioVehiculos;

	public VehiculoController(ServicioVehiculos servicioVehiculos) {
		this.servicioVehiculos = servicioVehiculos;
	}

	@PostMapping("/auto")
	public ResponseEntity<?> ingresarAuto(@RequestBody Auto auto) {
		boolean ingresado = servicioVehiculos.ingresarVehiculo(auto);

		if (ingresado) {
			return ResponseEntity.ok(auto);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("No se pudo ingresar el auto. Verifique cupo disponible o placa duplicada.");
		}
	}

	@PostMapping("/moto")
	public ResponseEntity<?> ingresarMotocicleta(@RequestBody Motocicleta moto) {
		boolean ingresado = servicioVehiculos.ingresarVehiculo(moto);

		if (ingresado) {
			return ResponseEntity.ok(moto);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("No se pudo ingresar la motocicleta. Verifique cupo disponible o placa duplicada.");
		}
	}

	@GetMapping
	public ArrayList<Vehiculo> listarVehiculos() {
		return servicioVehiculos.listarVehiculos();
	}

	@GetMapping("/{placa}")
	public ResponseEntity<?> buscarPorPlaca(@PathVariable String placa) {
		Vehiculo vehiculo = servicioVehiculos.buscarPorPlaca(placa);

		if (vehiculo != null) {
			return ResponseEntity.ok(vehiculo);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un vehiculo con la placa: " + placa);
		}
	}
}