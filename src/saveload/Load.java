package saveload;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import world.World;

public class Load {
    public static World load(String path) {
        World world = null;
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(path));
            world = gson.fromJson(reader, World.class);
            System.out.println("Berhasil melakukan load file " + path);
            System.out.println();
        }
        catch (Exception e) {
            System.out.println("Gagal melakukan load file " + path + ". Pesan error: " + e.getMessage());
        }
        return world;
    }
}
