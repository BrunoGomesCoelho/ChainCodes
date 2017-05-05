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
	private int white = 255;
	private int[] pontoInicial = {naoCalculado, naoCalculado};
	private int largura = naoCalculado;
	private int altura = naoCalculado;
	private int numPontosBorda = naoCalculado;
	public double tamBord = naoCalculado;

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
	 */
	public int[] getPontoInicial() throws Exception {
		if ( pontoInicial[0] != naoCalculado)
			return pontoInicial;
		pontoInicial = calculaPontoInicial();
		return pontoInicial;
	}

	/**
	 * Retorna a largura do objeto na imagem
	 * @return Um inteiro, o valor da largura
	 */
	public int getLargura() {
		if (largura != naoCalculado)
			return largura;
		largura = calculaLargura();
		return largura;
	}


	/**
	 * Retorna a altura do objeto na imagem
	 * @return Um inteiro, o valor da altura
	 */
	public int getAltura() {
		if (altura != naoCalculado)
			return altura;
		altura = calculaAltura();
		return altura;
	}


	/**
	 * Calcula qual é a largura do objeto, definida como o tamanho da maior linha innterrupta de pontos.
	 * @return O valor da largura
	 */
	private int calculaAltura() {
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
		return altura;
	}


	/**
	 * Calcula qual é a largura do objeto, definida como o tamanho da maior linha innterrupta de pontos.
	 * @return O valor da largura
	 */
	private int calculaLargura() {
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
		return largura;
	}


	/**
	 * Calcula qual é o primeiro ponto do objeto da imagem, ou seja, o ponto mais a esquerda da primeira linha do objeto.
	 * @return Um int array[2], onde o primeiro elemento representa a coordenada em y, e o segundo em x.
	 */
	private int[] calculaPontoInicial() throws Exception {
		for(int i=0; i< image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				if (image.getSample(j, i, colorChannel) != white)
					return new int[] {i, j};
			}
		}
		throw new Exception("Não existe um objeto na imagem!\n");
	}

	/**
	 * Retorna quantos ponto existem na borda do objeto
	 * @return Quantos pontos existem
	 */
	public int pontosBorda() throws Exception {
		int p0[] =  this.calculaPontoInicial();
		boolean visited[][] = new boolean[image.getHeight()][image.getWidth()];
		int im[][] = new int[image.getHeight()][image.getWidth()];
		int cols = p0[1], rows = p0[0];
		int orientation = 4, a = 0;
		int ni[] = { 0, -1, -1, -1, 0, +1, 1, +1}; // o que adicionar a i ou j dado a orientacao atual
		int nj[] = {-1, -1,  0, +1, 1, +1, 0, -1};
		
		// inicializando vetor de visitados
		for(int i = 0; i < image.getHeight(); i++) {
			for(int j = 0; j < image.getWidth(); j++) {
				visited[i][j] = false;
				if(image.getSample(j, i, colorChannel) != white) im[i][j] = 1;
				else im[i][j] = 0;
			}
		}

		int i = rows, j = cols;
		
		numPontosBorda = 0;

		while(!visited[i][j]) {
			numPontosBorda++;
			visited[i][j] = true;
			for(int k = orientation; k != (7+orientation)%8; k = (k+1)%8) { // orientacao relativa à base
				if(i+ni[k] == rows && j+nj[k] == cols && a == 0) { // só acaba quando passar pelo P0 DUAS vezes
					a++;
				} else if(i+ni[k] == rows && j+nj[k] == cols && a == 1) {
					return numPontosBorda;
				}
				if(i+ni[k] >= 0 && i+ni[k] < image.getHeight() && j+nj[k] >= 0 && j+nj[k] < image.getWidth()) { // para nao extrapolar a matriz
					if(im[i+ni[k]][j+nj[k]] == 1 && !visited[i+ni[k]][j+nj[k]]) {
						i += ni[k];
						j += nj[k];
						
						// atualizando orientacao do referencial
						orientation = (4 + k)%8;
						break;
					}
				}
			}
		}
		
		return numPontosBorda;
	}

	/**
	 * Retorna o tamanho da borda do objeto,
	 * calculado como a soma das distâncias de dois pontos consecutivos da borda.
	 * @return O tamanho da borda
	 */
	public double tamBorda() throws Exception {
		int p0[] =  this.calculaPontoInicial();
		boolean visited[][] = new boolean[image.getHeight()][image.getWidth()];
		int im[][] = new int[image.getHeight()][image.getWidth()];
		int cols = p0[1], rows = p0[0];
		int orientation = 4, a = 0;
		int ni[] = { 0, -1, -1, -1, 0, +1, 1, +1};
		int nj[] = {-1, -1,  0, +1, 1, +1, 0, -1};
		
		// inicializando vetor de visitados
		for(int i = 0; i < image.getHeight(); i++) {
			for(int j = 0; j < image.getWidth(); j++) {
				visited[i][j] = false;
				if(image.getSample(j, i, colorChannel) != white) im[i][j] = 1;
				else im[i][j] = 0;
			}
		}

		int i = rows, j = cols;
		
		tamBord = 1;

		while(!visited[i][j]) {
			visited[i][j] = true;
			for(int k = orientation; k != (7+orientation)%8; k = (k+1)%8) { // orientacao relativa à base
				if(i+ni[k] == rows && j+nj[k] == cols && a == 0) { // ver se é a primeira vez em que se passa pelo P0
					a++;
				} else if(i+ni[k] == rows && j+nj[k] == cols && a == 1) {
					return tamBord;
				}
				if(i+ni[k] >= 0 && i+ni[k] < image.getHeight() && j+nj[k] >= 0 && j+nj[k] < image.getWidth()) { // para nao extrapolar a matriz
					if(im[i+ni[k]][j+nj[k]] == 1 && !visited[i+ni[k]][j+nj[k]]) {
						i += ni[k];
						j += nj[k];
						if(k%2 != 0) {
							tamBord += Math.sqrt(2);
						}
						else tamBord += 1;
						
						// atualizando orientacao do referencial
						orientation = (4 + k)%8;
						break;
					}
				}
			}
		}
		
		return tamBord;
	}



}
