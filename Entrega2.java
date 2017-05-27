import java.util.*;

class Entrega2 {

	public static void main(String[] args) {

		FileManager file = new FileManager();
		LetterSoup soup = new LetterSoup();

		file.setFileName("Sopa.in");
		file.readFile();

		soup.setSoup(file.getFileContent());

		file.setFileName("Palabras.in");
		file.readFile();
		soup.setSearchingWords(file.getFileContent());

		soup.searchWords();

		file.setFileName("Solucion.out");
		file.writeFile(soup.getFindingWords(), soup.getMissingWords());

		System.out.println("Done!");
	}
}