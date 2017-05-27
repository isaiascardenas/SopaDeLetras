import java.util.*;

class Entrega2 {

	public static void main(String[] args) {
		// List<Integer> coordenadas = new ArrayList<Integer>();
		// Set<List<Integer>> conjunto = new HashSet<List<Integer>>();

		// coordenadas.add(3);
		// coordenadas.add(5);

		// conjunto.add(coordenadas);

		// coordenadas.remove(0);
		// coordenadas.add(0, 1);
		// System.out.println(coordenadas);
		// System.out.println(conjunto.contains(coordenadas)); 


		FileManager file = new FileManager();
		LetterSoup soup = new LetterSoup();

		file.setFileName("Sopa.in");
		file.readFile();

		soup.setSoup(file.getFileContent());

		file.setFileName("Palabras.in");
		file.readFile();
		soup.setSearchingWords(file.getFileContent());

		soup.searchWords();

		System.out.println("\nfinding: "+soup.getFindingWords());
		// for (String w: soup.getFindingWords()) {
		// 	System.out.println(w);
		// }

		System.out.println("\nmissing: "+ soup.getMissingWords());
		// for (String w: soup.getMissingWords()) {
		// 	System.out.println(w);
		// }

		// file.setFileName("Solucion.out");
		// file.writeFile(soup.getFindingWords(), soup.getMissingWords());
	}
}