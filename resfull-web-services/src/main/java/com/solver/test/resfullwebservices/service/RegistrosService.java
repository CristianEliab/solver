package com.solver.test.resfullwebservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RegistrosService {

	
	private static List<Registro> registros = new ArrayList<Registro>();
	private static long idCounter = 0;
	
	static {
		registros.add(new Registro(++idCounter, "1234189967", "Learn", new Date()));
		registros.add(new Registro(++idCounter, "1234189967", "Learn", new Date()));
		registros.add(new Registro(++idCounter, "1234189967", "Learn", new Date()));
	}
	
	public Registro save(Registro registro) {
		if(registro.getNumero() == -1  || registro.getNumero() == 0) {
			registro.setNumero(++idCounter);
			registros.add(registro);
		}else {
			deleteById(registro.getNumero());
			registros.add(registro);
		}
		return registro;
	}
	
	public Registro deleteById(long numero) {
		Registro registro = findById(numero);
		
		if(registro == null) return null;
		
		if(registros.remove(registro)) {
			return registro;
		}
		
		return null;
	}

	private Registro findById(long numero) {
		for(Registro regis: registros) {
			if(regis.getNumero() == numero) {
				return regis;
			}
		}
		return null;
	}

	public List<Registro> findAll(){
		return registros;
	}

	
}
