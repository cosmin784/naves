package naves;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


import naves.dto.NaveDTO;
import naves.entity.Nave;

@SpringBootTest
@ContextConfiguration(classes = NavesApplication.class)
public class NaveDTOTest {

	@Test
	public void testConstructorNull1() {
		Nave nave = new Nave();
		nave.setId(null);
		nave.setNombre(null);
		nave.setColor(null);
		nave.setTamano(null);
		
		NaveDTO naveDTO = new NaveDTO(nave);
		
		Assertions.assertThat(nave.getId()).isEqualTo(naveDTO.getId());
		Assertions.assertThat(nave.getNombre()).isEqualTo(naveDTO.getNombre());
		Assertions.assertThat(nave.getColor()).isEqualTo(naveDTO.getColor());
		Assertions.assertThat(nave.getTamano()).isEqualTo(naveDTO.getTamano());
	}
	
	@Test
	public void testConstructorNull2() {
		NaveDTO naveDTO = new NaveDTO(null);
		
		Assertions.assertThat(naveDTO.getId()).isNull();
		Assertions.assertThat(naveDTO.getNombre()).isNull();
		Assertions.assertThat(naveDTO.getColor()).isNull();
		Assertions.assertThat(naveDTO.getTamano()).isNull();
	}
	
	@Test
	public void testConstructorNull3() {
		Nave nave = new Nave();
		
		NaveDTO naveDTO = new NaveDTO(nave);
		
		Assertions.assertThat(naveDTO.getId()).isNull();
		Assertions.assertThat(naveDTO.getNombre()).isNull();
		Assertions.assertThat(naveDTO.getColor()).isNull();
		Assertions.assertThat(naveDTO.getTamano()).isNull();
	}
	
	
	@Test
	public void testContructorValues() {
		Nave nave = new Nave();
		nave.setId(15L);
		nave.setNombre("dasdas");
		nave.setColor("321");
		nave.setTamano("asdas");
		
		NaveDTO naveDTO = new NaveDTO(nave);
		
		Assertions.assertThat(nave.getId()).isEqualTo(naveDTO.getId());
		Assertions.assertThat(nave.getNombre()).isEqualTo(naveDTO.getNombre());
		Assertions.assertThat(nave.getColor()).isEqualTo(naveDTO.getColor());
		Assertions.assertThat(nave.getTamano()).isEqualTo(naveDTO.getTamano());
	}
	
	@Test
    public void testGettersAndSetters() {
        NaveDTO naveDTO = new NaveDTO();

        naveDTO.setId(87L);
        naveDTO.setNombre("x-wing");
        naveDTO.setColor("65487");
        naveDTO.setTamano("XS");

        Assertions.assertThat(87L).isEqualTo(naveDTO.getId().longValue());
        Assertions.assertThat("x-wing").isEqualTo(naveDTO.getNombre());
        Assertions.assertThat("65487").isEqualTo(naveDTO.getColor());
        Assertions.assertThat("XS").isEqualTo(naveDTO.getTamano());
    }
}