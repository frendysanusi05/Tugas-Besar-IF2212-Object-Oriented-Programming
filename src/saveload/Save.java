package saveload;

import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import world.World;
import utilz.Clock;
import utilz.ClockSerializer;

public class Save {
   	public static void save(World world) {
		String dataFile = "data/data.json";
		String clockFile = "data/clock.json";
		Path dataPath = Paths.get(dataFile);
		Path clockPath = Paths.get(clockFile);

		/* Serialize Clock */
		ClockSerializer clock = new ClockSerializer(Clock.getDay(), Clock.convertToSeconds(Clock.getStartTime()), Clock.convertToSeconds(Clock.getCurrTime()), Clock.getFirstTimeClock());

		/* Save World */
		try (Writer dataWriter = Files.newBufferedWriter(dataPath, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder()
			.enableComplexMapKeySerialization()
        	.setPrettyPrinting().create();
            JsonElement worldTree = gson.toJsonTree(world);
            gson.toJson(worldTree, dataWriter);
			System.out.println("Berhasil melakukan save ke ." + dataFile);
			System.out.println();
        }
		catch (Exception e) {
			System.out.println("Gagal melakukan save ke . " + dataFile + "Pesan error: " + e.getMessage());
		}

		/* Save Clock */
		try (Writer clockWriter = Files.newBufferedWriter(clockPath, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder()
			.enableComplexMapKeySerialization()
        	.setPrettyPrinting().create();
			JsonElement clockTree = gson.toJsonTree(clock);
			gson.toJson(clockTree, clockWriter);
			System.out.println("Berhasil melakukan save ke ." + clockFile);
			System.out.println();
        }
		catch (Exception e) {
			System.out.println("Gagal melakukan save ke . " + clockFile + " Pesan error: " + e.getMessage());
		}
   	}
}