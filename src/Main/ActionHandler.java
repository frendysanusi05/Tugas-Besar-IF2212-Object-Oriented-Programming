package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ActionHandler implements ActionListener{
    GameManager gm;

    public ActionHandler(GameManager gm){
        
        this.gm = gm;
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gm.threadAksi == null || !gm.threadAksi.isAlive()) {
            String command = e.getActionCommand();
            int indexObj = -1;

            if (command.contains("%")) {
                String[] listCmd = command.split("%");
                command = listCmd[1];
                indexObj = Integer.parseInt(listCmd[0]);
            }

            switch (command) {
                // Main menu
                case "start":
                    gm.routing.showScreen(1);
                    break;
                case "new-sim":
                    String name = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "What is your sim name?",
                            "Input Full Name",
                            JOptionPane.PLAIN_MESSAGE);
                    if (name != null && name.replaceAll(" ", "").length() > 0) {
                        if (gm.world.addSim(name)) {
                            // set current sim terbaru
                            gm.setCurrentSim(gm.world.getDaftarSim().get(gm.world.getDaftarSim().size() - 1));
                            gm.updateAttribute();
                            gm.ui.bgPanel[2].removeAll();
                            gm.ui.bgPanel[2].add(gm.ui.bgLabel[2]);
                            for (int i = 0; i < gm.world.getDaftarRumah().size(); i++) {
                                gm.ui.createObjek(gm.ui.bgPanel[2],
                                        gm.world.getDaftarRumah().get(i).getLocRumah().getX() * 10 + 30,
                                        gm.world.getDaftarRumah().get(i).getLocRumah().getY() * 10 + 30, 20, 20,
                                        "rumah.png",
                                        new String[] { "View Home%" + gm.world.getDaftarRumah().get(i).getNama() }, i);
                            }
                            gm.routing.showScreen(2);
                            gm.ui.messagText.setText("Hello Welcome, " + gm.getCurrentSim().getNamaLengkap()
                                    + "! Select your house location!");
                            gm.ui.nameText.setText(gm.getCurrentSim().getNamaLengkap());
                        } else {
                            JOptionPane.showMessageDialog(null, "Nama sudah digunakan!");
                        }
                    } else if (name != null) {
                        JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                    }
                    break;
                case "choose-sim":
                    List<String> options = gm.world.getDaftarSim().stream()
                            .map(Sim::getNamaLengkap)
                            .collect(Collectors.toList());
                    Object selected = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "Your Sim", "Choose Sim",
                            JOptionPane.PLAIN_MESSAGE, null, options.toArray(), options.get(0));
                    if (selected != null) {
                        int index = options.indexOf(selected);
                        // set current sim by index choosen
                        gm.setCurrentSim(gm.world.getDaftarSim().get(index));
                        gm.updateAttribute();
                        gm.ui.bgPanel[2].removeAll();
                        gm.ui.bgPanel[2].add(gm.ui.bgLabel[2]);
                        for (int i = 0; i < gm.world.getDaftarRumah().size(); i++) {
                            if (gm.getCurrentSim().getRumah() != null && gm.getCurrentSim().getRumah().getNama()
                                    .equals(gm.world.getDaftarRumah().get(i).getNama())) {
                                gm.ui.createObjek(gm.ui.bgPanel[2],
                                        gm.getCurrentSim().getRumah().getLocRumah().getX() * 10 + 30,
                                        gm.getCurrentSim().getRumah().getLocRumah().getY() * 10 + 30, 20, 20,
                                        "rumahku.png",
                                        new String[] { "View Home%" + gm.world.getDaftarRumah().get(i).getNama() }, i);
                            } else {
                                gm.ui.createObjek(gm.ui.bgPanel[2],
                                        gm.world.getDaftarRumah().get(i).getLocRumah().getX() * 10 + 30,
                                        gm.world.getDaftarRumah().get(i).getLocRumah().getY() * 10 + 30, 20, 20,
                                        "rumah.png",
                                        new String[] { "View Home%" + gm.world.getDaftarRumah().get(i).getNama() }, i);
                            }
                        }
                        gm.ui.bgPanel[2].repaint();
                        gm.routing.showScreen(2);
                        if (gm.getCurrentSim().getRumah() == null) {
                            gm.ui.messagText.setText("Hello Welcome Back, " + gm.getCurrentSim().getNamaLengkap()
                                    + "! Select your house location!");
                        } else {
                            gm.ui.messagText.setText("Hello Welcome Back, " + gm.getCurrentSim().getNamaLengkap());
                        }
                        gm.ui.nameText.setText(gm.getCurrentSim().getNamaLengkap());
                    }
                    break;
                case "exit":
                    int result = JOptionPane.showConfirmDialog(gm.ui.bgPanel[1],
                            "Do you want to Exit ?", "Exit Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        int save = JOptionPane.showConfirmDialog(gm.ui.bgPanel[1],
                                "Do you want to Save Game ?", "Save Confirmation",
                                JOptionPane.YES_NO_OPTION);
                        if (save == JOptionPane.YES_OPTION) {
                            gm.save();
                        }
                        System.exit(0);
                    }
                    break;

                case "Back to World":
                    gm.routing.showScreen(2);
                    if (gm.getCurrentSim().getRumah() == null) {
                        gm.ui.messagText.setText("Hello, " + gm.getCurrentSim().getNamaLengkap()
                                + "! Select your house location!");
                    } else {
                        gm.ui.messagText.setText("Silakan berkunjung");
                    }
                    gm.getCurrentSim().getCurrLokasi().setRuangan(null);
                    gm.getCurrentSim().getCurrLokasi().setRumah(null);
                    break;
                case "Back to Home":
                    Rumah currHome = gm.getCurrentSim().getCurrLokasi().getRumah();
                    gm.routing.showScreen(4);
                    if (currHome.getNama().equals(gm.getCurrentSim().getRumah().getNama())) {
                        gm.ui.messagText.setText("Berada di rumah milik Anda, " + currHome.getNama());
                    } else {
                        gm.ui.messagText.setText("Saat ini Anda berkunjung ke " + currHome.getNama());
                    }
                    gm.getCurrentSim().getCurrLokasi().setRuangan(null);
                    break;

                case "View Room":
                    currRuangan = gm.getCurrentSim().getCurrLokasi().getRumah().getDaftarRuangan().get(indexObj);
                    gm.getCurrentSim().getCurrLokasi().setRuangan(currRuangan);
                    gm.ui.messagText.setText("Berada di ruang " + currRuangan.getNama() + " di dalam "
                            + gm.getCurrentSim().getCurrLokasi().getRumah().getNama());
                    gm.ui.refreshRoom(currRuangan);
                    break;
                case "View Home":
                    Rumah currRumah = gm.world.getDaftarRumah().get(indexObj);
                    gm.getCurrentSim().getCurrLokasi().setRumah(currRumah);
                    if (gm.getCurrentSim().getRumah() != null
                            && currRumah.getNama().equals(gm.getCurrentSim().getRumah().getNama())) {
                        gm.ui.messagText.setText("Berada di rumah milik Anda, " + currRumah.getNama());
                    } else {
                        gm.ui.messagText.setText("Saat ini Anda berkunjung ke " + currRumah.getNama());
                    }
                    gm.ui.refreshHome(currRumah);
                    break;

                // Upgrade Rumah
                case "Upgrade House":
                    // Buat Pilihan Acuan Kamar
                    // Boolean status;
                    Rumah currHouse = gm.getCurrentSim().getCurrLokasi().getRumah();
                    List<Ruangan> ruangan = currHouse.getDaftarRuangan();
                    List<String> listRuangans = new ArrayList<String>();
                    for (Ruangan ruang : gm.getCurrentSim().getCurrLokasi().getRumah().getDaftarRuangan()) {
                        listRuangans.add(ruang.getNama());
                    }
                    int jumlahKamarAwal = currHouse.getDaftarRuangan().size();
                    Object acuanRuang = JOptionPane.showInputDialog(gm.ui.bgPanel[3], "Pilih ruangan acuan",
                            "Upgrade House",
                            JOptionPane.PLAIN_MESSAGE, null, listRuangans.toArray(), listRuangans.get(0));
                    Ruangan RuanganAcuan = ruangan.get(listRuangans.indexOf(acuanRuang));
                    // Buat pilihan posisi
                    List<String> acuanposisi = Arrays.asList("Kanan", "Kiri", "Atas", "Bawah");
                    Object posisiacuan = JOptionPane.showInputDialog(gm.ui.bgPanel[3],
                            "Silakan pilih posisi kamar baru dibanding kamar acuan",
                            "Upgrade House", JOptionPane.PLAIN_MESSAGE, null, acuanposisi.toArray(),
                            acuanposisi.get(0));
                    String acuan = acuanposisi.get(acuanposisi.indexOf(posisiacuan));

                    // Melakukan upgrade rumah
                    // Meminta input namaruangan
                    String namaruangan = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                            "Silakan masukkan nama ruangan yang baru", "Input Nama Ruangan Baru",
                            JOptionPane.PLAIN_MESSAGE);
                    currHouse.upgradeRumah(RuanganAcuan, acuan, namaruangan);
                    if (jumlahKamarAwal == currHouse.getDaftarRuangan().size()) {
                        gm.ui.messagText.setText("Upgrade gagal karena ruangan tidak tersedia");
                    } else {
                        gm.getCurrentSim().setwaktuUpgradeRumah(1080);
                        gm.ui.messagText.setText("Sedang upgrade rumah");
                        if (gm.getCurrentSim().getWaktuUpgradeRumah() == 0){
                            gm.ui.refreshHome(currHouse);
                        }
                    }
                    break;
                case "Edit Room":
                    // Opsi tombol edit room
                    List<String> option = Arrays.asList("Add Object", "Delete Object", "Move Object");
                    Object selectedPane = JOptionPane.showInputDialog(gm.ui.bgPanel[3], "Your Action", "Edit Room",
                            JOptionPane.PLAIN_MESSAGE, null, option.toArray(), option.get(0));
                    int index = option.indexOf(selectedPane);

                    // Ceritanya ini Inventory

                    List<String> inventory =gm.getCurrentSim().getInventory().getIventoryString();
                    // List<String> inventory = Arrays.asList("kasur single 4x1", "kasur queen size 4x2",
                    //         "kasur king size 5x2", "jam 1x1", "meja kursi 3x3", "toilet 1x1", "kompor gas 2x1",
                    //         "kompor listrik 1x1", "laptop 1x1", "tv 1x1", "matras 2x1", "sofa 2x1");

                    // Tambah Barang
                    if (index == 0) {
                        // Inventory ObjekNonMakanan kosong
                        if (inventory.size() == 0) {
                            JOptionPane.showMessageDialog(gm.ui.bgPanel[3], "Tidak ada barang");
                        } else {
                            // Opsi inventory
                            Object selectedInventory = JOptionPane.showInputDialog(gm.ui.bgPanel[3],
                                    "Choose Object you want to add", "Edit Room", JOptionPane.PLAIN_MESSAGE, null,
                                    inventory.toArray(), inventory.get(0));
                            if (selectedInventory != null) {
                                int indexInventory = inventory.indexOf(selectedInventory);

                                // Bikin 3 dropdown buat milih titik dan posisi barang
                                String[] coordOptions = { "0", "1", "2", "3", "4", "5" };
                                String[] positionOptions = { "v", "h" };

                                JComboBox<String> selectionX = new JComboBox<>(coordOptions);
                                JComboBox<String> selectionY = new JComboBox<>(coordOptions);
                                JComboBox<String> selectionPosisi = new JComboBox<>(positionOptions);

                                Object[] message = { "Posisi X:", selectionX, "Posisi Y:", selectionY,
                                        "Horizontal/Vertikal (h/v):", selectionPosisi };
                                int optionPosisi1 = JOptionPane.showConfirmDialog(null, message, "Posisi Barang",
                                        JOptionPane.OK_CANCEL_OPTION);

                                if (optionPosisi1 == JOptionPane.OK_OPTION) {
                                    String stringX = (String) selectionX.getSelectedItem();
                                    String stringY = (String) selectionY.getSelectedItem();
                                    String posisi = (String) selectionPosisi.getSelectedItem();

                                    // Konversi ke integer
                                    int x = Integer.parseInt(stringX);
                                    int y = Integer.parseInt(stringY);

                                    // Bikin point dan objek sesuai pilihan
                                    Point point = new Point(x, y);
                                    ObjekNonMakanan o = new ObjekNonMakanan(inventory.get(indexInventory));
                                    // ObjekNonMakanan o = ObjekNonMakanan.returnObject(inventory.get(indexInventory));

                                    // Ngecek nabrak ato ga
                                    if (currRuangan.nabrakGa(o, point, posisi)) {
                                        currRuangan.tambahObjek(o, point, posisi);
                                        gm.getCurrentSim().getInventory().kurangiItem(o.getNamaObjek(), 1);

                                        gm.ui.refreshRoom(currRuangan);
                                        gm.ui.messagText.setText("Barang berhasil ditambahkan ke ruangan");
                                    } else {
                                        gm.ui.messagText.setText("Barang tidak bisa ditambahkan karena nabrak");
                                    }
                                } else {
                                    gm.ui.messagText.setText("Dialog ditutup.");
                                }
                            }
                        }
                    }
                    // Hapus Barang
                    else if (index == 1) {
                        // Ga ada barang
                        if (currRuangan.getDaftarObjekString().size() == 0) {
                            JOptionPane.showMessageDialog(gm.ui.bgPanel[3], "Tidak ada barang");
                        } else {
                            // Opsi barang yang ada di currRuangan
                            List<String> optionObjek = currRuangan.getDaftarObjekString();
                            Object selectedObjek = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                                    "Choose Object you want to delete", "Delete Object", JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    optionObjek.toArray(), optionObjek.get(0));
                            if (selectedObjek != null) {
                                // Hapus barang sesuai pilihan
                                int indexOptionObjek = optionObjek.indexOf(selectedObjek);
                                ObjekNonMakanan deleteObject = currRuangan.getObjek(indexOptionObjek);
                                currRuangan.hapusObjek(deleteObject);
                                // deleteObject.setNamaBarang(gm.getCurrentSim().getInventory().nameConverterReverse(deleteObject.getNamaObjek()));
                                gm.getCurrentSim().getInventory().addItemPeralatan(deleteObject, 1);

                                // Refresh panel
                                gm.ui.refreshRoom(currRuangan);
                                gm.ui.messagText.setText("Barang berhasil dihapus dari ruangan");
                            }
                        }
                    }
                    // Pindah barang
                    else if (index == 2) {
                        if (currRuangan.getDaftarObjekString().size() == 0) {
                            JOptionPane.showMessageDialog(gm.ui.bgPanel[3], "Tidak ada barang");
                        } else {
                            // Opsi barang yang ada di currRuangan
                            List<String> optionObjek2 = currRuangan.getDaftarObjekString();
                            Object selectedObjek = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                                    "Choose Object you want to move", "Move Object", JOptionPane.PLAIN_MESSAGE, null,
                                    optionObjek2.toArray(), optionObjek2.get(0));
                            if (selectedObjek != null) {
                                // Hapus objek sesuai pilihan
                                int indexOptionObjek = optionObjek2.indexOf(selectedObjek);
                                ObjekNonMakanan moveObject = currRuangan.getObjek(indexOptionObjek);
                                Point pointAwal = moveObject.getTitik();
                                String posisiAwal = moveObject.getPosisi();
                                currRuangan.hapusObjek(moveObject);
                                // Bikin 3 dropdown buat milih titik dan posisi barang
                                String[] coord = { "0", "1", "2", "3", "4", "5" };
                                String[] position = { "v", "h" };

                                JComboBox<String> selectionX = new JComboBox<>(coord);
                                JComboBox<String> selectionY = new JComboBox<>(coord);
                                JComboBox<String> selectionPosisi = new JComboBox<>(position);

                                Object[] message = { "Posisi X:", selectionX, "Posisi Y:", selectionY,
                                        "Horizontal/Vertikal (h/v):", selectionPosisi };
                                int optionPosisi2 = JOptionPane.showConfirmDialog(null, message, "Posisi Barang",
                                        JOptionPane.OK_CANCEL_OPTION);

                                if (optionPosisi2 == JOptionPane.OK_OPTION) {
                                    String stringX = (String) selectionX.getSelectedItem();
                                    String stringY = (String) selectionY.getSelectedItem();
                                    String posisi = (String) selectionPosisi.getSelectedItem();

                                    // Konversi ke Integer
                                    int x = Integer.parseInt(stringX);
                                    int y = Integer.parseInt(stringY);

                                    // Bikin point dan ngecek nabrak ato ga
                                    Point point = new Point(x, y);
                                    if (currRuangan.nabrakGa(moveObject, point, posisi)) {
                                        currRuangan.tambahObjek(moveObject, point, posisi);
                                        gm.ui.messagText.setText("Barang berhasil ditambahkan ke ruangan");
                                    } else {
                                        currRuangan.tambahObjek(moveObject, pointAwal, posisiAwal);
                                        gm.ui.messagText.setText("Barang tidak bisa ditambahkan karena nabrak");
                                    }
                                    // Refresh panel
                                    gm.ui.refreshRoom(currRuangan);
                                } else {
                                    gm.ui.messagText.setText("Dialog ditutup");
                                }
                            }
                        }
                    }
                    break;

                case "save":
                    int save = JOptionPane.showConfirmDialog(gm.ui.bgPanel[1],
                            "Do you want to Save Game ?", "Save Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (save == JOptionPane.YES_OPTION) {
                        gm.save();
                    }
                    break;

                case "View Inventory":
                    if (gm.getCurrentSim().getInventory().getData().size() == 0) {
                        JOptionPane.showMessageDialog(null, "Inventory Kosong");
                    } else {
                        gm.ui.inventoryPopUp();
                        gm.ui.popInventory.setVisible(true);
                    }
                    break;

                // ACTION METHOD
                case "Tidur":
                    String waktuTidurString = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                            "Berapa lama kamu ingin tidur (dalam detik)?", "Input Waktu Tidur",
                            JOptionPane.PLAIN_MESSAGE);
                    if (waktuTidurString != null) {
                        int waktutidur = Integer.parseInt(waktuTidurString);
                        gm.threadAksi(waktutidur);
                        gm.getCurrentSim().tidur(waktutidur);
                        gm.ui.messagText.setText("Tidur dulu " + waktutidur + " detik ah, biar ga capek");
                        gm.getCurrentSim().setStatus("Sedang tidur");
                    }
                    break;

                case "Olahraga":
                    String waktuOlahragaString = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                            "Berapa lama kamu ingin berolahraga (dalam detik)?", "Input Waktu Tidur",
                            JOptionPane.PLAIN_MESSAGE);
                    if (waktuOlahragaString != null) {
                        int waktuOlahraga = Integer.parseInt(waktuOlahragaString);
                        while (waktuOlahraga % 20 != 0) {
                            JOptionPane.showMessageDialog(null, "Waktu harus kelipatan 20!");
                            waktuOlahragaString = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                                    "Berapa lama kamu ingin berolahraga (dalam detik)?", "Input Waktu Tidur",
                                    JOptionPane.PLAIN_MESSAGE);
                            if (waktuOlahragaString == null) {
                                break;
                            } else {
                                waktuOlahraga = Integer.parseInt(waktuOlahragaString);
                            }
                        }
                        if (waktuOlahragaString != null) {
                            gm.threadAksi(waktuOlahraga);
                            gm.getCurrentSim().olahraga(waktuOlahraga);
                            gm.ui.messagText.setText("Olahraga dulu cuy biar sehat...");
                            gm.getCurrentSim().setStatus("Sedang olahraga");
                        }
                    }
                    break;

                case "Duduk":
                    gm.threadAksi(30);
                    gm.getCurrentSim().duduk();
                    gm.ui.messagText.setText("Enaknya... Santai sekali");
                    gm.getCurrentSim().setStatus("Sedang duduk");
                    break;

                case "Ngudud":
                    gm.threadAksi(30);
                    gm.getCurrentSim().ngudud();
                    gm.ui.messagText.setText("Fiuhh.. Dunhill emang mantep cuy..");
                    gm.getCurrentSim().setStatus("Sedang ngudud");
                    break;

                case "Menonton":
                    gm.threadAksi(30);
                    gm.getCurrentSim().nontonTv();
                    gm.ui.messagText.setText("Horee... Seru sekali acaranya");
                    gm.getCurrentSim().setStatus("Sedang nonton tv");
                    break;

                case "Ngoding":
                    gm.threadAksi(30);
                    gm.getCurrentSim().ngoding();
                    gm.ui.messagText.setText("Ngoding dulu biar jago...");
                    gm.getCurrentSim().setStatus("Sedang ngoding");
                    break;

                case "Meditasi":
                    gm.threadAksi(30);
                    gm.getCurrentSim().meditasi();
                    gm.ui.messagText.setText("Meditasi itu membuat lebih tenang..");
                    gm.getCurrentSim().setStatus("Sedang meditasi");
                    break;

                case "Main PS":
                    gm.threadAksi(30);
                    gm.getCurrentSim().mainPS();
                    gm.ui.messagText.setText("Game PS itu seru euyy.. T-tapi mataku kok rasanya agak sakit ya..");
                    gm.getCurrentSim().setStatus("Sedang main ps");
                    break;

                case "Main Game":
                    gm.threadAksi(30);
                    gm.getCurrentSim().bermain();
                    gm.ui.messagText.setText("Horee... Seru sekali gamenya");
                    gm.getCurrentSim().setStatus("Sedang main game");
                    break;

                case "Makan":
                    List<String> inventoryMakanan = new ArrayList<String>();
                    for (InventoryItem item : gm.getCurrentSim().getInventory().getData()) {
                        if (item.getKategori().equals("Makanan") || item.getKategori().equals("Bahan Makanan")) {
                            inventoryMakanan.add(item.getNamaBarang());
                        }
                    }
                    if (!inventoryMakanan.isEmpty()) {
                        Object selectMakanan = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "Choose Food", "Eat",
                                JOptionPane.PLAIN_MESSAGE, null, inventoryMakanan.toArray(), inventoryMakanan.get(0));
                        if (selectMakanan != null) {
                            String namaMakanan = (String) selectMakanan;
                            // cari kekenyangannya
                            int kekenyangan = 0;
                            for (ObjekMakanan makanan : gm.world.getDaftar_makanan()) {
                                if (namaMakanan.equals(makanan.getNamaObjek())) {
                                    kekenyangan = makanan.getKekenyangan();
                                }
                            }
                            for (ObjekBahanMakanan bahan : gm.world.getDaftar_bahan()) {
                                if (namaMakanan.equals(bahan.getNamaObjek())) {
                                    kekenyangan = bahan.getKekenyangan();
                                }
                            }
                            gm.threadAksi(30);
                            gm.getCurrentSim().makan(namaMakanan, kekenyangan);
                            gm.ui.messagText.setText("Yaammyy... makan " + namaMakanan + " dulu yaa... Enakkk..");
                            gm.getCurrentSim().setStatus("Sedang makan");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Anda tidak memiliki makanan!");
                    }
                    break;

                case "Bekerja":
                    if (gm.getCurrentSim().getJedaGantiKerja() >= 720 || !gm.getCurrentSim().getSudahGantiKerja()) {
                        String waktuBekerjaString = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                                "Berapa lama kamu ingin bekerja (dalam detik)?", "Input Waktu Kerja",
                                JOptionPane.PLAIN_MESSAGE);
                        if (waktuBekerjaString != null) {
                            int waktukerja = Integer.parseInt(waktuBekerjaString);
                            gm.threadAksi(waktukerja);
                            gm.getCurrentSim().kerja(waktukerja);
                            gm.ui.messagText.setText("Kerja dulu boss, selama " + waktukerja + " detik");
                            gm.getCurrentSim().setStatus("Sedang bekerja");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Anda dapat mulai bekerja 1 hari setelah ganti kerja!");
                    }
                    break;

                case "Ganti Pekerjaan":
                    if (gm.getCurrentSim().getTotalWaktuKerja() >= 720) {
                        List<String> listPekerjaan = new ArrayList<String>();
                        for (ObjekPekerjaan pekerjaan : gm.world.getDaftarPekerjaan()) {
                            if (!pekerjaan.getNamaObjek().equals(gm.getCurrentSim().getPekerjaan())) {
                                listPekerjaan.add(pekerjaan.getNamaObjek());
                            }
                        }
                        Object selectPekerjaan = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "Choose New Work",
                                "Change Work",
                                JOptionPane.PLAIN_MESSAGE, null, listPekerjaan.toArray(), listPekerjaan.get(0));
                        if (selectPekerjaan != null) {
                            ObjekPekerjaan newPekerjaan = null;
                            for (ObjekPekerjaan pekerjaan : gm.world.getDaftarPekerjaan()) {
                                if (pekerjaan.getNamaObjek().equals((String) selectPekerjaan)) {
                                    newPekerjaan = pekerjaan;
                                }
                            }
                            gm.getCurrentSim().setPekerjaan(newPekerjaan);
                            gm.updateAttribute();
                            gm.ui.messagText.setText("Selamat anda dapat pekerjaan baru, yeayyy");
                            gm.getCurrentSim().setStatus("Ganti pekerjaan");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Belum dapat ganti pekerjaan, waktu bekerja belum 1 hari!");
                    }
                    break;
                case "Melihat Waktu":
                    if(gm.getCurrentSim().getWaktuUpgradeRumah() > 0){
                        int time = gm.getCurrentSim().getWaktuUpgradeRumah();
                        int menit = time / 60;
                        int detik = time % 60;
                        String stringmenit = String.valueOf(menit < 10 ? "0" + menit : menit);
                        String stringdetik = String.valueOf(detik < 10 ? "0" + detik : detik);
                        JOptionPane.showMessageDialog(null, stringmenit + " : " + stringdetik);
                        
                    }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Aksi lain belum selesai!");
        }
    }
}

