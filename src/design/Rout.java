//kurang import package doang

public class Rout {
    MasterGame mg;

    public Rout(MasterGame mg) {
        this.mg = mg;
    }

    public void Screen(int screenIndex) {
        for (int i = 0; i < mg.gui.bgPanel.length; i++) {
            if (i == screenIndex) {
                mg.gui.bgPanel[i].setVisible(true);
                if (screenIndex > 1) {
                    mg.gui.attributePanel.setVisible(true);
                    mg.gui.textPanel.setVisible(true);
                } else {
                    mg.gui.attributePanel.setVisible(false);
                    mg.gui.textPanel.setVisible(false);
                }
            } else {
                if (mg.gui.bgPanel[i] != null) {
                    mg.gui.bgPanel[i].setVisible(false);
                }
            }
        }

    }
}