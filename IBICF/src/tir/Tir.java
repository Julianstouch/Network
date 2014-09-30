package tir;


public class Tir {
	private int type;
	private int cadence;
	private int degats;
	private long tempsdernier;
	private Integer	angle;
	private Integer decalagex;
	private Integer decalagey;
	private Integer vitDeplacement;
	private boolean isAutoGuide;
	private int groupe;
	
	
	public int getGroupe() {
		return groupe;
	}
	public void setGroupe(int groupe) {
		this.groupe = groupe;
	}
	public boolean isAutoGuide() {
		return isAutoGuide;
	}
	public void setAutoGuide(boolean isAutoGuide) {
		this.isAutoGuide = isAutoGuide;
	}
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public int getCadence() {
		return cadence;
	}
	public void setCadence(int cadence) {
		this.cadence = cadence;
	}
	public int getDecalagex() {
		return decalagex;
	}
	public void setDecalagex(int decalagex) {
		this.decalagex = decalagex;
	}
	public int getDecalagey() {
		return decalagey;
	}
	public void setDecalagey(int decalagey) {
		this.decalagey = decalagey;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Tir(int ptype, int pcadence, int pdegats,int pangle, int pdecalagex, int pdecalagey,int pvitessedep,  boolean pautoguide, int pGroupe) {
		super();
		this.type = ptype;
		this.cadence = pcadence;
		this.degats = pdegats;
		this.angle = pangle;
		this.decalagex = pdecalagex;
		this.decalagey = pdecalagey;
		this.tempsdernier = 0L;
		this.vitDeplacement = pvitessedep;
		this.isAutoGuide = pautoguide;
		this.groupe = pGroupe;
	}
	
	public Tir(int ptype, int pcadence,  int pdegats ,int pangle,int pvitessedep,  boolean pautoguide, int pGroupe) {
		super();
		this.type = ptype;
		this.cadence = pcadence;
		this.degats = pdegats;
		this.angle = pangle;
		this.decalagex = 0;
		this.decalagey = 0;
		this.tempsdernier = 0L;
		this.vitDeplacement = pvitessedep;
		this.isAutoGuide = pautoguide;
		this.groupe = pGroupe;
	}
	
	public long getTempsdernier() {
		return tempsdernier;
	}
	public void setTempsdernier(long ptempsdernier) {
		this.tempsdernier = ptempsdernier;
	}
	public Integer getVitDeplacement() {
		return vitDeplacement;
	}
	public void setVitDeplacement(Integer pvitDeplacement) {
		this.vitDeplacement = pvitDeplacement;
	}
	public int getDegats() {
		return degats;
	}
	public void setDegats(int degats) {
		this.degats = degats;
	}
	
	
	
}
