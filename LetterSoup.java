import java.util.*;
import java.io.*;

public class LetterSoup
{
	private List<String> searchingWords;
	private List<String> findingWords = new ArrayList<String>();
	private List<String> missingWords = new ArrayList<String>();
	private List<Integer> coordinates = new ArrayList<Integer>(Arrays.asList(0,0));
	private List<String> soup;
	private int currentChar;

	public void LetterSoup()
	{
		this.searchingWords = null;
		this.soup = null;
		this.currentChar = 1;
	}

	public void setSearchingWords(List<String> searchWords)
	{
		this.searchingWords = searchWords;
		this.missingWords = searchWords;
	}

	public void setSoup(List<String> soup)
	{
		this.soup = soup;
	}

	public List<String> getFindingWords()
	{
		return this.findingWords;
	}

	public List<String> getMissingWords()
	{
		return this.missingWords;
	}

	public void searchWords()
	{
		for (String word: this.searchingWords) {
			System.out.print(word+": ");
			this.findWord(word);
		}
	}

	public void findWord(String word)
	{
		// System.out.println("missingWords: "+this.missingWords);
		// System.out.println("findingWords: "+this.findingWords);
		for (int j =0 ; j<this.soup.size(); j++) {
			for (int i = 0; i < this.soup.get(j).length(); i ++) {
				if (Objects.equals(this.soup.get(j).charAt(i), word.charAt(0))) {
					List<String> lastPositions = new ArrayList<String>();
					List<String> backFrom = new ArrayList<String>();
					List<List<Integer>> set = new ArrayList<List<Integer>>();

					this.coordinates.set(0, i);
					this.coordinates.set(1, j);
					System.out.println(this.coordinates);
					set.add(Arrays.asList(i,j));
					this.currentChar = 1;
					if (this.breadthFirstSearch(word, lastPositions, backFrom, set)) {
						System.out.println("!!!find!!!");
						this.findingWords.add(0, word);
						break;
						// this.missingWords.remove(word);
					}			
				}
			}
		}
	}

	public boolean breadthFirstSearch(String word, List<String> lastPositions, List<String> backFrom, List<List<Integer>> positionsSet)
	{
		List<Integer> position = new ArrayList<Integer>(Arrays.asList(0,0));

		// System.out.println("Set: "+ positionsSet);

		lastPositions.add(0, "back");
		lastPositions.add(0, "down");
		lastPositions.add(0, "up");
		lastPositions.add(0, "right");
		lastPositions.add(0, "left");

		if (this.currentChar == 0) {
			
			System.out.println("not found 404");
			return false;
			//add to missing
		}

		while (this.currentChar < word.length() && lastPositions.size() != 0) {
			String direction = lastPositions.get(0);
			lastPositions.remove(0);

			if (this.canMoveTo(direction)) {
				switch (direction){
					case "left":
						position.set(1, this.coordinates.get(1));
						position.set(0, this.coordinates.get(0)-1);
						System.out.println("contains "+position+"? "+ positionsSet.contains(position));
						if (this.soup.get(this.coordinates.get(1)).charAt(this.coordinates.get(0)-1) == word.charAt(this.currentChar) && !positionsSet.contains(position)) {
							this.coordinates.set(0, this.coordinates.get(0)-1);
							System.out.println("left: "+this.coordinates);
							
							positionsSet.add(0, position);
							System.out.println("Set: "+positionsSet);
														
							this.currentChar = this.currentChar+1;
							backFrom.add(0, direction);
							return this.breadthFirstSearch(word, lastPositions, backFrom, positionsSet);
						}
						break;
					case "right":
						position.set(1, this.coordinates.get(1));
						position.set(0, this.coordinates.get(0)+1);
						System.out.println("contains "+position+"? "+ positionsSet.contains(position));
						if (this.soup.get(this.coordinates.get(1)).charAt(this.coordinates.get(0)+1) == word.charAt(this.currentChar) && !positionsSet.contains(position)) {
							this.coordinates.set(0, this.coordinates.get(0)+1);
							System.out.println("right: "+this.coordinates);
							positionsSet.add(0, position);
							System.out.println("Set: "+positionsSet);

							this.currentChar = this.currentChar+1;
							backFrom.add(0, direction);
							return this.breadthFirstSearch(word, lastPositions, backFrom, positionsSet);
						}
						break;
					case "up":
						position.set(0, this.coordinates.get(0));
						position.set(1, this.coordinates.get(1)-1);
						System.out.println("contains "+position+"? "+ positionsSet.contains(position));
						if (this.soup.get(this.coordinates.get(1)-1).charAt(this.coordinates.get(0)) == word.charAt(this.currentChar) && !positionsSet.contains(position)) {
							this.coordinates.set(1, this.coordinates.get(1)-1);
							System.out.println("up: "+this.coordinates);
							positionsSet.add(0, position);
							System.out.println("Set: "+positionsSet);
														
							this.currentChar = this.currentChar+1;
							backFrom.add(0, direction);
							return this.breadthFirstSearch(word, lastPositions, backFrom, positionsSet);
						}
						break;
					case "down":
						position.set(0, this.coordinates.get(0));
						position.set(1, this.coordinates.get(1)+1);
						System.out.println("contains "+position+"? "+ positionsSet.contains(position));
						if (this.soup.get(this.coordinates.get(1)+1).charAt(this.coordinates.get(0)) == word.charAt(this.currentChar) && !positionsSet.contains(position)) {
							this.coordinates.set(1, this.coordinates.get(1)+1);
							System.out.println("down: "+this.coordinates);
							positionsSet.add(0, position);
							System.out.println("Set: "+positionsSet);

							this.currentChar = this.currentChar+1;
							backFrom.add(0, direction);
							return this.breadthFirstSearch(word, lastPositions, backFrom, positionsSet);
						}
						break;
					default:
						// if (!backFrom.isEmpty()) {
						// positionsSet.remove(this.coordinates);
						position.set(0, this.coordinates.get(0));
						position.set(1, this.coordinates.get(1));
						positionsSet.remove(position);
						// System.out.println("backFrom: "+backFrom);
						this.goBack(backFrom.get(0));
						this.currentChar = this.currentChar-1;
						backFrom.remove(0);
						// System.out.println("Back: "+this.coordinates);
						// System.out.println("Set: "+positionsSet);
						// }
						// this.coordinates.set(1, y);
						// this.coordinates.set(0, x);
						
						// this.currentChar = this.currentChar-1;
						break;
				}
			}
			
		}
		System.out.println("Set: "+positionsSet);
		return true;
	}

	public boolean canMoveTo(String direction)
	{
		switch (direction){
			case "left":
				if (this.coordinates.get(0)-1 < 0) {
					return false;
				}
				break;
			case "right":
				if (this.coordinates.get(0)+1 >=this.soup.get(0).length()) {
					return false;
				}
				break;
			case "up":
				if (this.coordinates.get(1)-1 < 0) {
					return false;
				}
				break;
			case "down":
				if (this.coordinates.get(1)+1 >= this.soup.size()) {
					return false;
				}
				break;
			default:
				return true;
		}
		return true;
	}

	public void goBack(String direction)
	{
		switch (direction){
			case "left":
				this.coordinates.set(0, this.coordinates.get(0)+1);
				break;
			case "right":
				this.coordinates.set(0, this.coordinates.get(0)-1);
				break;
			case "up":
				this.coordinates.set(1, this.coordinates.get(1)+1);
				break;
			case "down":
				this.coordinates.set(1, this.coordinates.get(1)-1);
				break;
			default:
				return;
		}
	}
}