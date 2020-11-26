package ui;

import javax.swing.JPanel;

public abstract class Page extends JPanel{	
	void runAssist(LayoutAssist assist) {
		assist.setLayout(this);
	}
}
