/**
 * @author Bruno Coelho & Gabriel Cruz
 * Classe que representa uma execeção para a classe ProcessamentoImagem.
 */
public class ProcessamentoImagemException extends Exception {

	public ProcessamentoImagemException() {
		super("Não existe objeto!\n");
	}

	public ProcessamentoImagemException(String message) {
		super(message);
	}



}
