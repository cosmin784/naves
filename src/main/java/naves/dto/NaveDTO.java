package naves.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import naves.entity.Nave;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NaveDTO {
	
	private Long id;
	
	private String nombre;
	
	private String color;
	
	private String tamano;
	
	public NaveDTO(Nave nave) {
		if(nave != null) {
			this.id = nave.getId();
			this.nombre = nave.getNombre();
			this.color = nave.getColor();
			this.tamano = nave.getTamano();	
		}
	}
}
