
//package src.Main;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import src.Utama.Sim;
import src.Utama.World;

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
	public void load() {
		try {
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(new FileReader("save.json"));
			JSONObject jsonObject = (JSONObject) obj;

			world.setTime(Integer.parseInt(jsonObject.get("time").toString()));
			world.setHari(Integer.parseInt(jsonObject.get("hari").toString()));

			JSONArray jsonArrayRumah = (JSONArray) jsonObject.get("listRumah");
			List<Rumah> listRumah = new ArrayList<Rumah>();
			for (Object object : jsonArrayRumah) {
				listRumah.add(new Rumah((JSONObject) object));
			}
			World.setListRumah(listRumah);

			JSONArray jsonArraySim = (JSONArray) jsonObject.get("listSim");
			List<Sim> listSim = new ArrayList<Sim>();
			for (Object object : jsonArraySim) {
				listSim.add(new Sim((JSONObject) object, world.getDaftarRumah()));
			}
			World.setListSim(listSim);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Load gagal");
			e.printStackTrace();
		}
	}

	public void threadAksi(int waktuAksi) {
		threadAksi = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < waktuAksi; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					world.setTime(1);
					currentSim.setwaktuUpgradeRumah(-1);
					currentSim.addOnTimeWorld(1); // untuk aksi yang perlu kondisi
					ui.jamText.setText(world.getTime());
					ui.hariText.setText("Hari ke-" + world.getHari());
				}
				updateAttribute();
			}
		});

		threadAksi.start();
	}

	public void updateAttribute() {
		gui.pekerjaanText.setText(getCurrentSim().getPekerjaan());
		gui.kesehatanText.setText(getCurrentSim().getKesehatan());
		gui.moodText.setText(getCurrentSim().getMood());
		gui.kekenyanganText.setText(getCurrentSim().getKekenyangan());
		gui.uangText.setText(getCurrentSim().getUang());
	}
}
