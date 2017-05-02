/**
 * @author Bruno Coelho & Gabriel Cruz
 */

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
	String arq1 = "image.jpg";

	public void main() {
		File inFile = new File(arq1);

		try {
			BufferedImage imageTemp = ImageIO.read(inFile);
			BufferedImage image = new BufferedImage(imageTemp.getWidth(),
					imageTemp.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		} catch (java.io.IOException e) {
			System.out.printf("Erro na hora de abrir o arquivo!\n");
			System.out.print(e);
		}





	}

}
