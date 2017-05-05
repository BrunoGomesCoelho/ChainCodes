/**
 * @author Bruno Coelho & Gabriel Cruz
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;

public class Main {
	static String file = "";

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
		
		System.out.println("Digite o nome da imagem: ");
		file = EntradaTeclado.leString();
		
		Raster image = readImage(file);
		ProcessamentoImagem imagemProcessada = new ProcessamentoImagem(image);

		// imagemProcessada.printImage();
		try {
			int[] pontoInicial = imagemProcessada.getPontoInicial();
			// invertemos pois guardamos as coordenas (y,x).
			System.out.printf("P0 = (%d, %d)\n", pontoInicial[1], pontoInicial[0]);
		} catch (Exception e) {
			System.out.print(e);
		}

		System.out.printf("Largura: %d, Altura: %d\n\n", imagemProcessada.getLargura(), imagemProcessada.getAltura());
		//imagemProcessada.printImage();
		System.out.printf("======== Dados da Borda ========\n");
		System.out.println("Tamanho: " + imagemProcessada.tamBorda()  + " Pontos: " + imagemProcessada.pontosBorda());
	}
}
