package tir;

public class Rafale 
{
	private int nbEncours;
	private int nbMax;
	
	private int delai;
	private int initialdelai;
	
	public int getDelai() {
		return delai;
	}

	public Rafale(int nbMax, int delai) {
		this.nbEncours =1;
		this.nbMax = nbMax;
		this.delai = delai;
		this.initialdelai = 0;
	}
	
	public Rafale(int nbMax, int delai, int pInitialdelai) {
		this.nbEncours =1;
		this.nbMax = nbMax;
		this.delai = delai;
		this.initialdelai = pInitialdelai;
	}
	
	public void setDelai(int delai) {
		this.delai = delai;
	}

	public int getNbEncours() {
		return nbEncours;
	}

	public void setNbEncours(int nbEncours) {
		this.nbEncours = nbEncours;
	}

	public int getNbMax() {
		return nbMax;
	}

	public void setNbMax(int nbMax) {
		this.nbMax = nbMax;
	}

	public int getInitialdelai() {
		return initialdelai;
	}

	public void setInitialdelai(int initialdelai) {
		this.initialdelai = initialdelai;
	}
	
	
	
}
