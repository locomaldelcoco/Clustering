package interfaz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Filter {

	public static ArrayList<Coordinate> coordenadas(String n) {
		ArrayList<Coordinate> marks = new ArrayList<Coordinate>();
		int count = 0;
		double coordX = 0.0;
		double coordY = 0.0;

		File archivoRanking = new File("instancia" + n + ".txt");
		try {
			Scanner palabras = new Scanner(archivoRanking);

			while (palabras.hasNext()) {
				String linea = palabras.next().toString();

				if (count % 2 == 0) {
					coordX = Double.parseDouble(linea);
				} else {
					coordY = Double.parseDouble(linea);
					marks.add(new Coordinate(coordX, coordY));
					//System.out.println("X = " + coordX);
					//System.out.println("Y = " + coordY);
				}
				count++;
			}
			palabras.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return marks;
	}
}
