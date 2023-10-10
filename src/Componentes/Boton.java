package Componentes;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
public class Boton extends JButton{
	private static final long serialVersionUID = 1L;
	String texto;
	JLabel titulo;
	public Boton(String texto) {
		this.texto = texto;
	}
	public void ubicacion(int x,int y,int w,int h) {
		this.setBounds(x,y,w,h);
		this.setLayout(null);
		this.setVisible(true);
	}
	public void texto(Color color,int tamano) {
		this.removeAll();
		titulo = new Etiqueta(0,0,this.getWidth(),this.getHeight(),texto,color,tamano,true);
		this.add(titulo);
	}
	public void diseno(int grosorBorde,Color colorBorde,Color colorFondo) {
		this.setBackground(colorFondo);
		this.setBorder(BorderFactory.createLineBorder(colorBorde,grosorBorde));
	}
}