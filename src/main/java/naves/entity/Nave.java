package naves.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import naves.dto.NaveDTO;

@Entity(name = "NAVE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "COLOR")
	private String color;
	
	@Column(name = "TAMANO")
	private String tamano;
	
	public Nave(NaveDTO nave) {
		this.nombre = nave.getNombre();
		this.color = nave.getColor();
		this.tamano = nave.getTamano();
	}
}
