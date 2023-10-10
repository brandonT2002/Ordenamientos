package Componentes;
import java.awt.Color;
import javax.swing.JRadioButton;
public class RadioB extends JRadioButton{
	private static final long serialVersionUID = 1L;
	String texto;
	public RadioB(String texto) {  
		this.texto = texto;
		this.setBackground(null);
	}
	public void ubicacion(int x,int y, int w, int h) {
		this.setBounds(x,y,w,h);
		this.setLayout(null);
		this.setVisible(true);
	}
	public void texto(Color color, int tamano) {
		this.removeAll();
		this.add(new Etiqueta(0,0,this.getWidth(),this.getHeight(),texto,color,tamano,true));
	}
}
