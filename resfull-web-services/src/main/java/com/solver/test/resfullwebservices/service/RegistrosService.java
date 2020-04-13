package com.solver.test.resfullwebservices.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public class RegistrosService {

	private static List<Registro> registros = new ArrayList<Registro>();
	private static int MAX_T = 500;
	private static int MAX_N = 100;
	private static int MAX_WI = 100;
	private static long idCounter = 0;

	public Registro save(Registro registro) {
		if (registro.getNumero() == -1 || registro.getNumero() == 0) {
			registro.setNumero(++idCounter);
			registros.add(registro);
		} else {
			deleteById(registro.getNumero());
			registros.add(registro);
		}
		return registro;
	}

	public Registro deleteById(long numero) {
		Registro registro = findById(numero);

		if (registro == null)
			return null;

		if (registros.remove(registro)) {
			return registro;
		}

		return null;
	}

	private Registro findById(long numero) {
		for (Registro regis : registros) {
			if (regis.getNumero() == numero) {
				return regis;
			}
		}
		return null;
	}

	public List<Registro> findAll() {
		return registros;
	}

	public Registro resolverPeticion(Registro registro) {
		String respuesta = "";
		String archivo = registro.getArchivo();
		int secuencia = 0;
		String[] prueba = archivo.split("\n");
		int numeroT = Integer.parseInt(prueba[0]);
		if (numeroT >= 1 && numeroT <= MAX_T) {
			for (int i = 0; i < numeroT; i++) {
				secuencia++;
				int numeroN = Integer.parseInt(prueba[secuencia]);
				if (numeroN >= 1 && numeroN <= MAX_N) {
					String[] pruebaDia = new String[numeroN];
					for (int j = 0; j < numeroN; j++) {
						secuencia++;
						pruebaDia[j] = prueba[secuencia];
					}
					Arrays.sort(pruebaDia);
					int maximo = calcularMaximo(pruebaDia, numeroN);
					respuesta += "Case #" + (i + 1) + ": " + maximo + "\n";
				}
			}
		}
		registro.setRespuesta(respuesta);
		return registro;
	}

	private int calcularMaximo(String[] pruebaDia, int numeroN) {
		int maximo = 0;
		Queue<Integer> q = new LinkedList<>();
		for (int i = pruebaDia.length - 1; i >= 0; i--) {
			int peso = Integer.parseInt(pruebaDia[i]);
			if (peso >= 50) {
				maximo++;
			} else {
				q.add(Integer.parseInt(pruebaDia[i]));
			}
		}
		maximo += menores(q);
		return maximo;
	}

	private int menores(Queue<Integer> q) {
		int maximo = 0;
		int size = q.size();
		do {
			int head = q.peek();
			if (head * size > 100) {
				if (size >= 2) {
					q.remove(); // Remuevo el mayor
					q.remove(); // Remuevo el siguienteF
				}
				maximo++;
			} else {
				maximo++;
				break;
			}
			size = q.size();
		} while (size <= 2);
		return maximo;
	}

}
