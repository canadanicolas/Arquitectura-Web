package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CarreraEstudiantePk implements Serializable{

	@Column(name = "estudianteId")
    int estudianteId;

    @Column(name = "carreraId")
    int carreraId;
    
    public CarreraEstudiantePk() {
    	
    }
    
	public CarreraEstudiantePk(int estudianteId, int carreraId) {
		super();
		this.estudianteId = estudianteId;
		this.carreraId = carreraId;
	}
	
	/*----------------------------------------------Getters y Setters----------------------------------------------*/

	public int getEstudianteId() {
		return estudianteId;
	}

	public void setEstudianteId(int estudianteId) {
		this.estudianteId = estudianteId;
	}

	public int getCarreraId() {
		return carreraId;
	}

	public void setCarreraId(int carreraId) {
		this.carreraId = carreraId;
	}
	
	/*---------------------------------------------- Otros Metodos ----------------------------------------------*/

	@Override
	public String toString() {
		return "CarreraEstudianteId [estudianteId=" + estudianteId + ", carreraId=" + carreraId + "]";
	}
    
    
}
