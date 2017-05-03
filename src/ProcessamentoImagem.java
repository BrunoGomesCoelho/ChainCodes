import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 * Classe para o processamento de uma dada imagem.
 * @author Bruno Coelho & Gabriel Cruz
 */
public class ProcessamentoImagem {

	private static int colorChannel = 0;     // color channel para grayscale
	private static int naoCalculado = -1;

	private Raster image;
	private boolean existeObjeto = true;
	private int white = 255;
	private int[] pontoInicial = {naoCalculado, naoCalculado};
	private int largura = naoCalculado;
	private int altura = naoCalculado;
	private int numPontosBorda = naoCalculado;
	private int tamBorda = naoCalculado;



	// TODO: Deletar essa função antes de enviar, só para debuggar
	public void printImage() {
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				System.out.printf("%d ", image.getSample(j, i, colorChannel));
			}
			System.out.printf("\n");
		}
	}


	public ProcessamentoImagem(Raster image) {
		this.image = image;
	}

	/**
	 * Inicializador caso o usuário queira passar um valor para o pixel branco diferente do padrão(255);
	 * @param image: A imagem
	 * @param whiteValue: O valor do pixel branco.
	 */
	public ProcessamentoImagem(Raster image, int whiteValue) {
		this.image = image;
		this.white = whiteValue;
	}

	/**
	 * Retorna o ponto inicial de um objeto na imagem.
	 * @return O ponto inicial, nas coordenads (y, x), considerando que a imagem começa no (0, 0).
	 * @throws ProcessamentoImagemException Caso o ponto não exista
	 */
	public int[] getPontoInicial() throws ProcessamentoImagemException{
		if (!existeObjeto)
			throw new ProcessamentoImagemException();
		if ( pontoInicial[0] != naoCalculado)
			return pontoInicial;
		pontoInicial = calculaPontoInicial();
		return pontoInicial;
	}

	/**
	 * Retorna a largura do objeto na imagem
	 * @return Um inteiro, o valor da largura
	 * @throws ProcessamentoImagemException Caso não exista objeto.
	 */
	public int getLargura() throws ProcessamentoImagemException{
		if (!existeObjeto)
			throw new ProcessamentoImagemException();
		if (largura != naoCalculado)
			return largura;
		largura = calculaLargura();
		return largura;
	}

	/**
	 * Retorna a altura do objeto na imagem
	 * @return Um inteiro, o valor da altura
	 * @throws ProcessamentoImagemException Caso não exista objeto.
	 */
	public int getAltura() throws ProcessamentoImagemException{
		if (!existeObjeto)
			throw new ProcessamentoImagemException();
		if (altura != naoCalculado)
			return altura;
		altura = calculaAltura();
		return altura;
	}

	/**
	 * Calcula qual é a largura do objeto, definida como o tamanho da maior linha innterrupta de pontos.
	 * @return O valor da largura
	 * @throws ProcessamentoImagemException Caso não exista objeto
	 */
	private int calculaAltura() throws ProcessamentoImagemException {
		int altura = 0;
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				if (image.getSample(i, j, colorChannel) != white) {
					// se acharmos um pixel diferente de 0, verificamos o tamanho dessa coluna, depois saimos fora do for.
					int aux = 0;
					while (j < image.getHeight() && image.getSample(i, j, colorChannel) != white) {
						aux++;
						j++;
					}
					if (aux > altura)
						altura = aux;
					break;
				}
			}
		}
		if (altura == 0) {
			existeObjeto = false;
			throw new ProcessamentoImagemException();
		}
		else return altura;
	}



	/**
	 * Calcula qual é a largura do objeto, definida como o tamanho da maior linha innterrupta de pontos.
	 * @return O valor da largura
	 * @throws ProcessamentoImagemException Caso não exista objeto
	 */
	private int calculaLargura() throws ProcessamentoImagemException {
		int largura = 0;
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				if (image.getSample(j, i, colorChannel) != white) {
					// se acharmos um pixel diferente de 0, verificamos o tamanho dessa linha, depois saimos fora do for.
					int aux = 0;
					while (j < image.getWidth() && image.getSample(j, i, colorChannel) != white) {
						aux++;
						j++;
					}
					if (aux > largura)
						largura = aux;
					break;
				}
			}
		}
		if (largura == 0) {
			existeObjeto = false;
			throw new ProcessamentoImagemException();
		}
		else return largura;
	}
	

	/**
	 * Calcula qual é o primeiro ponto do objeto da imagem, ou seja, o ponto mais a esquerda da primeira linha do objeto.
	 * @return Um int array[2], onde o primeiro elemento representa a coordenada em y, e o segundo em x.
	 * @throws Exception: Caso não haja um objeto.
	 */
	private int[] calculaPontoInicial() throws ProcessamentoImagemException{
		for(int i=0; i< image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				if (image.getSample(j, i, colorChannel) != white)
					return new int[] {i, j};
			}
		}
		existeObjeto = false;
		throw new ProcessamentoImagemException();
	}

	/**
	 * Retorna quantos ponto existem na borda do objeto
 	 * @param image: A imagem a ser analisada
	 * @return Quantos pontos existem
	 * @throws Exception Se não houver um objeto na imagem
	 */
	public int pontosBorda(Raster image) throws Exception {
		return 0;
	}

	/**
	 * Retorna o tamanho da borda do objeto,
	 * calculado como a soma das distâncias de dois pontos consecutivos da borda.
	 * @param image: A imagem a ser analisada
	 * @return: O tamanho da borda
	 * @throws Exception: Caso não exista um objeto
	 */
	public double tamBorda(Raster image) throws Exception {
		return 0.1;
	}



}
