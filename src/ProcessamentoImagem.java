import java.awt.image.BufferedImage;

/**
 * @author Bruno Coelho & Gabriel Cruz
 */
public class ProcessamentoImagem {
	/* TODO: Adicionar um tipo exceção especificio do Processamento de image. */

	/**
	 * Verifica qual é o primeiro ponto da imagem, ou seja, o ponto mais a esquerda da primeira linha.
	 * @param image: A imagem a ser analisada
	 * @return: Um int array[2], onde o primeiro elemento representa a coordenada em x, e o segundo em y.
	 * @throws Exception: Caso o ponto não exista
	 */
	public int[] pontoInicial(BufferedImage image) throws Exception{
		return new int[] {1, 2};
	}

	/**
	 * Verifica qual é a largura do objeto, definido como o tamanho da maior linha ininterrupta de pontos.
	 * @param image: A imagem a ser analisada
	 * @return: O valor da largura.
	 * @throws Exception: Caso um objeto não exista.
	 */
	public int larguraObjeto(BufferedImage image) throws Exception {
		return 0;
	}

	public int pontosBorda(BufferedImage image) throws Exception {
		return 0;
	}

	public int tamBorda(BufferedImage image) throws Exception {
		return 0;
	}



}
