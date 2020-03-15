package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	private final int nMax = 100;
	private final int tMax = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi;
	
	public Model() {
		this.inGioco = false;
		this.tentativiFatti = 0;
	}
	
	public void nuovaPartita() {
		this.segreto = (int) (Math.random() * nMax) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	this.tentativi = new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo) {
		if(!inGioco) {
			throw new IllegalStateException("La partita è già terminata");
		}
		
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora usato tra 1 e "+nMax+"\n");
		}
		
		this.tentativiFatti++;
		this.tentativi.add(tentativo);
		
		if(this.tentativiFatti==tMax) {
			this.inGioco = false;
		}
		
		if(tentativo==this.segreto) {
			this.inGioco = false;
			return 0;
		}
		
		if(tentativo<this.segreto)
			return -1;
		else
			return 1;
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>nMax)
			return false;
		else {
			if(this.tentativi.contains(tentativo))
				return false;
		}
		return true;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int gettMax() {
		return tMax;
	}
}
