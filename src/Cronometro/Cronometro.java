package Cronometro;
import javax.swing.JPanel;
import Componentes.Colores;
import Componentes.Etiqueta;
public class Cronometro extends JPanel implements Runnable{
	public static Etiqueta time;
	Thread hilo;
	boolean iniciar;
	private static final long serialVersionUID = 1L;
	public Cronometro() {
		setBounds(885,200,195,30);
		setBackground(Colores.blanco);
		setVisible(true);
		
		time = new Etiqueta(885,275,110,30,"00:00:000",Colores.celeste1,18,false);
		add(time);
	}
	public void run() {
		Integer minutos = 0, segundos = 0, milesimas = 0;
		String min = "", seg = "", mil = "";
		try {
	        while(iniciar) {
	            milesimas += 4;
	            if(milesimas == 1000) {
	            	milesimas = 0;
	                segundos += 1;
	                if(segundos == 60) {
	                	segundos = 0;
	                    minutos++;
	                }
	            }
	            if(minutos < 10) min = "0" + minutos;
	            else min = minutos.toString();
	            if(segundos < 10) seg = "0" + segundos;
	            else seg = segundos.toString();
	
	            if(milesimas < 10) mil = "00" + milesimas;
	            else if( milesimas < 100 ) mil = "0" + milesimas;
	            else mil = milesimas.toString();
	            time.setText( min + ":" + seg + ":" + mil );
	            Thread.sleep(4);
	        }
		}catch(Exception e) {}
	}
	public void iniciarCronometro() {
		iniciar = true;
		hilo = new Thread(this);
		hilo.start();
	}
	@SuppressWarnings("deprecation")
	public void pararCronometro() {
		iniciar = false;
		hilo.stop();
	}
}