package cluster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorArchivos {

	public static ArrayList<Vertice> getCoordenadas(String file) {
		ArrayList<Vertice> marks = new ArrayList<Vertice>();
		int count = 0;
		double coordX = 0.0;
		double coordY = 0.0;

		File archivoRanking = new File("files\\" + file);
		try {
			Scanner palabras = new Scanner(archivoRanking);

			while (palabras.hasNext()) {
				String linea = palabras.next().toString();

				if (count % 2 == 0) {
					coordX = Double.parseDouble(linea);
				} else {
					coordY = Double.parseDouble(linea);
					marks.add(new Vertice(coordX, coordY));
				}
				count++;
			}
			palabras.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return marks;
	}

	public static String[] getArchivos() {
		return new File("files\\").list();
	}
}