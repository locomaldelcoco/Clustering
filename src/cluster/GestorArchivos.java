package cluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GestorArchivos {

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
