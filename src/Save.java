import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class Save {
   	public static void save(World world) {
		String fileName = "data/save.json";
		Path path = Paths.get(fileName);

		try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
			// System.out.println("TES0");
            JsonElement tree = gson.toJsonTree(world);
			// System.out.println("TES1");
            gson.toJson(tree, writer);
			// System.out.println("TES2");
			System.out.println("Berhasil melakukan save.");
        }
		catch (Exception e) {
			System.out.println("Gagal melakukan save. Pesan error: " + e.getMessage());
		}
   	}
}