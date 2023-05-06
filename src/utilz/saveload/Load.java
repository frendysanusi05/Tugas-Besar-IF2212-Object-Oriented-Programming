package utilz.saveload;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import world.World;
import utilz.Clock;
import utilz.ClockSerializer;

public class Load {
    public static World load(String dataPath, String clockPath) {
        World world = null;
        ClockSerializer clockSerializer = null;
        try {
            Gson gson = new Gson();
            Reader dataReader = Files.newBufferedReader(Paths.get(dataPath));
            Reader clockReader = Files.newBufferedReader(Paths.get(clockPath));
            world = gson.fromJson(dataReader, World.class);
            System.out.println("Berhasil melakukan load file " + dataPath);
            clockSerializer = gson.fromJson(clockReader, ClockSerializer.class);
            System.out.println("Berhasil melakukan load file " + clockPath);
            System.out.println();
        }
        catch (Exception e) {
            System.out.println("Gagal melakukan load file " + dataPath + " dan file " + clockPath +  ". Pesan error: " + e.getMessage());
        }
        /* Deserialize Clock Serializaber to Clock */
        Clock.setDay(clockSerializer.getDay());
        Clock.setStop(clockSerializer.getStop());
        Clock.setStartTime(Clock.convertToLocalTime(clockSerializer.getStartTime()));
        Clock.setCurrTime(Clock.convertToLocalTime(clockSerializer.getCurrTime()));
        Clock.setFirstTimeClock(clockSerializer.isFirstTimeClock());
        
        return world;
    }
}
