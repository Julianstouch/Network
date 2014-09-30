package deplacement;
import ibicf.Coord;
import projectile.Projectile;


public class Deplacement {
	private Integer	angle;
	private Integer vitDeplacement;
	private Integer dureeDeplacement;
	private Integer numEtape;
	private boolean isOriente;
	
	public Deplacement(Integer pvitDeplacement, Integer pdureeDeplacement, Integer pangle,  boolean pisOriente) {
		angle = pangle;
		dureeDeplacement=pdureeDeplacement;
		vitDeplacement = pvitDeplacement;
		isOriente = pisOriente;
	}
	public Integer getAngle() {
		return angle;
	}
	public boolean isOriente() {
		return isOriente;
	}
	public Integer getVitDeplacement() {
		return vitDeplacement;
	}
	public void setAngle(Integer pangle) {
		angle = pangle;
	}
	public void setOriente(boolean pisOriente) {
		isOriente = pisOriente;
	}
	public void setVitDeplacement(Integer pvitDeplacement) {
		vitDeplacement = pvitDeplacement;
	}
	public Integer getDureeDeplacement() {
		return dureeDeplacement;
	}
	public void setDureeDeplacement(Integer dureeDeplacement) {
		this.dureeDeplacement = dureeDeplacement;
	}
	public Integer getNumEtape() {
		return numEtape;
	}
	public void setNumEtape(Integer numEtape) {
		this.numEtape = numEtape;
	}
	public Coord getCoordTrajectoire(){
		Coord coordTraj;
		coordTraj=Projectile.getCoord(angle, vitDeplacement);
		return coordTraj;
	}
}
