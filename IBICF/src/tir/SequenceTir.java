package tir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SequenceTir {
	
	private int tempPhase;
	
	private int limiteViePhase;
	
	private List<Tir> maListeTir = new ArrayList<Tir>();
	private HashMap<Integer,Rafale> maListeRafale = new HashMap<Integer, Rafale>();
	
	public SequenceTir()
	{
	}
	
	public SequenceTir(int ppv)
	{
		this.limiteViePhase = ppv;
	}

	public int getLimiteViePhase() {
		return limiteViePhase;
	}

	public void setLimiteViePhase(int limiteViePhase) {
		this.limiteViePhase = limiteViePhase;
	}

	public List<Tir> getMaListeTir() {
		return maListeTir;
	}

	public int getTempPhase() {
		return tempPhase;
	}

	public void setTempPhase(int tempPhase) {
		this.tempPhase = tempPhase;
	}

	public HashMap<Integer, Rafale> getMaListeRafale() {
		return maListeRafale;
	}

	
}
