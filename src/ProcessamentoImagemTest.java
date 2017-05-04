import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.image.Raster;

import static org.junit.Assert.*;

/**
 * Created by bruno on 03/05/17.
 */
public class ProcessamentoImagemTest {

	private static ProcessamentoImagem test;
	private static Raster image;

	@BeforeClass
	public static void testInicializa() {
		Raster image = Main.readImage("circulo.png");
		test = new ProcessamentoImagem(image);
	}

	@Test
	public void testProcessamentoImagem() {
		ProcessamentoImagem temp = new ProcessamentoImagem(image, 140);
	}

	@Test
	public void testGetPontoInicial() throws Exception {
		assertArrayEquals(test.getPontoInicial(), new int[] {16, 23});
		test.getPontoInicial();
	}

	@Test
	public void testGetLargura() throws Exception {
		assertEquals(test.getLargura(), 20);
		test.getLargura();
	}

	@Test
	public void testGetAltura() throws Exception {
		assertEquals(test.getAltura(), 20);
		test.getAltura();
	}

	@Test
	public void testPontosBorda() throws Exception {
		test.pontosBorda();
	}

	@Test
	public void testTamBorda() throws Exception {
		test.tamBorda();
	}

}