package cluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GestorArchivos {

	public static ArrayList<Vertice> getCoordenadas(String file) {
		ArrayList<Vertice> marks = new ArrayList<Vertice>();
		int count = 0;
		double coordX = 0.0;
		double coordY = 0.0;

		File archivoVertice = new File("files\\" + file);
		try {
			Scanner palabras = new Scanner(archivoVertice);

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
	
	public static boolean guardarGrafo(Grafo g) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String json = gson.toJson(g);
		System.out.println(json);
		try {
			FileWriter writer = new FileWriter("files\\grafo.json");
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public static Grafo cargarGrafo(String s) {
		Gson gson = new Gson();
		Grafo g = new Grafo();;
		try {
			BufferedReader br = new BufferedReader(new FileReader("files\\" + s));
			g =  gson.fromJson(br, g.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return g;
	}
}
