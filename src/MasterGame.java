//package src.Main;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;



//import com.src.Rumah;
//import com.src.Sim;
//import com.src.World;

public class MasterGame {
	
	World world = new World();
	private Sim currentSim;
	//ActionHandler actionHandler = new ActionHandler(this);
	GUI gui = new GUI(this);
	Rout rout = new Rout(this);

	// Thread threadTime;
	Thread threadAksi;
    

	public MasterGame() {
		rout.Screen(0);
	}

	public Sim getCurrentSim() {
		return currentSim;
	}

	public void setCurrentSim(Sim currentSim) {
		this.currentSim = currentSim;
	}

	public void save() {
		try (FileWriter file = new FileWriter("save.json")) {
			//file.write(world.toJson().toJSONString());
			file.flush();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Save gagal");
			e.printStackTrace();
		}
	}

}
