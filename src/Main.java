/**
 * @author Bruno Coelho & Gabriel Cruz
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;

public class Main {
	static String arq1 = "circulo.png";
	static String arq2 = "elipse.png";
	static String arq3 = "vazio.png";

	public static Raster readImage(String arquivo) {
		File inFile = new File(arquivo);
		BufferedImage imageTemp = null;
		try {
			imageTemp = ImageIO.read(inFile);
		} catch (java.io.IOException e) {
			System.out.printf("Erro na hora de abrir o arquivo! O seguinte erro pode ser útil: \n");
			System.out.print(e);
			System.exit(1);
		}
		return imageTemp.getRaster();
	}

	public static void main(String[] args) throws Exception {
		Raster image = readImage(arq1);
		ProcessamentoImagem imagemProcessada = new ProcessamentoImagem(image);

		// imagemProcessada.printImage();
		try {
			int[] pontoInicial = imagemProcessada.getPontoInicial();
			// invertemos pois guardamos as coordenas (y,x).
			System.out.printf("%d %d\n", pontoInicial[1], pontoInicial[0]);
		} catch (Exception e) {
			System.out.print(e);
		}

		System.out.printf("Largura: %d, Altura: %d\n\n", imagemProcessada.getLargura(), imagemProcessada.getAltura());
	}
}
