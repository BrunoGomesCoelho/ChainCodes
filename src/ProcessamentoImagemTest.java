import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.image.Raster;

import static org.junit.Assert.*;

public class ProcessamentoImagemTest {

	private static String imagemVazia = "vazio.png";
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

	@Test(expected = Exception.class)
	public void testGetPontoInicialException() throws Exception{
		ProcessamentoImagem testVazio = new ProcessamentoImagem(Main.readImage(imagemVazia));
		testVazio.getPontoInicial();
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

	// usando a imagem circulo.png como teste
	@Test
	public void testPontosBorda() throws Exception {
		assertEquals(test.pontosBorda(), 52);
		test.pontosBorda();
	}
	
	// usando a imagem circulo.png como teste
	@Test
	public void testTamBorda() throws Exception {
		assertEquals((int)test.tamBorda(), 61);
		test.tamBorda();
	}

}
