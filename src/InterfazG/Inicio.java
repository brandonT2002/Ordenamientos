package InterfazG;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Componentes.Boton;
import Componentes.Colores;
import Componentes.Etiqueta;
import Componentes.RadioB;
import Componentes.Texto;
import Cronometro.Cronometro;
import Datos.Ploteo;
import static Datos.Ploteo.coordenadas;
public class Inicio extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static JPanel marco;
	public static Etiqueta numPasos;
	Texto rutaArch,tituloG;
	Boton examinar,generar,ordenar;
	RadioB ascendente,descendente;
	RadioB bubble,insertion,shell;
	Etiqueta tiempo,datos;
	JPanel grafica;
	String ruta;
	Grafico grafico;
	Cronometro cn;
	
    public Inicio(){
    	super("Grafica");
		this.setLayout(null);
		this.setBounds(-10,0,1390,760);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Colores.blanco);
		
		elementos();
    }
    public void elementos() {
    	marco = new JPanel();
		marco.setLayout(null);
		marco.setBackground(null);
		marco.setBounds(5,5,1355,703);
		marco.setBorder(BorderFactory.createLineBorder(Colores.azul3,3));
		add(marco);
		
    	rutaArch = new Texto();
    	rutaArch.setBounds(80,60,1000,30);
    	rutaArch.setPlaceholder("Ruta del Archivo");
    	rutaArch.setEditable(false);
		marco.add(rutaArch);
		
		examinar = new Boton("Examinar");
		examinar.ubicacion(1122,60,150,30);
		examinar.texto(Colores.blanco,15);
		examinar.diseno(3,Colores.verde2,Colores.verde3);
		examinar.addActionListener(this);
		marco.add(examinar);
		
		tituloG = new Texto();
		tituloG.setBounds(80,115,1000,30);
		tituloG.setPlaceholder("Título de la Gráfica");
		marco.add(tituloG);
		
		generar = new Boton("Generar Gráfica");
		generar.ubicacion(1122,115,150,30);
		generar.texto(Colores.blanco,15);
		generar.diseno(3,Colores.verde2,Colores.verde3);
		generar.addActionListener(this);
		marco.add(generar);
		
		ascendente = new RadioB("Ascendente");
		ascendente.ubicacion(1122,170,150,30);
		ascendente.texto(Colores.celeste1,15);
		marco.add(ascendente);
		
		descendente = new RadioB("Descendente");
		descendente.ubicacion(1122,195,160,30);
		descendente.texto(Colores.celeste1,15);
		marco.add(descendente);
		
		ButtonGroup bgO=new ButtonGroup();    
		bgO.add(ascendente);bgO.add(descendente);
		
		bubble = new RadioB("BubbleSort");
		bubble.ubicacion(1122,245,150,30);
		bubble.texto(Colores.celeste1,15);
		marco.add(bubble);
		
		insertion = new RadioB("InsertionSort");
		insertion.ubicacion(1122,270,170,30);
		insertion.texto(Colores.celeste1,15);
		marco.add(insertion);
		
		shell = new RadioB("ShellSort");
		shell.ubicacion(1122,295,145,30);
		shell.texto(Colores.celeste1,15);
		marco.add(shell);
		
		ButtonGroup bgA=new ButtonGroup();    
		bgA.add(bubble);bgA.add(insertion);bgA.add(shell);
		
		ordenar = new Boton("Ordenar");
		ordenar.ubicacion(1122,350,150,30);
		ordenar.texto(Colores.blanco,15);
		ordenar.diseno(3,Colores.azul2,Colores.azul3);
		ordenar.addActionListener(this);
		marco.add(ordenar);
		
		generarG();
		
		marco.add(new Etiqueta(885,170,110,30,"Cronómetro",Colores.celeste1,18,false));
		generarC();
		
		marco.add(new Etiqueta(885,275,110,30,"Pasos",Colores.celeste1,18,false));
		
		numPasos = new Etiqueta(885,305,195,30,"0",Colores.celeste1,18,true);
        marco.add(numPasos);
    }
    void generarC() {
    	cn = new Cronometro();
    	marco.add(cn);
    }
    void actualizar() {
    	grafica.setVisible(false);
		marco.remove(grafica);
		generarG();
	}
    void generarG() {
    	grafico = new Grafico(tituloG.getText(),cn,examinar,generar,ordenar);
    	grafica = new JPanel();
		grafica.setLayout(null);
		grafica.setBackground(Colores.blanco);
		grafica.setBounds(80,170,785,500);
		grafica.removeAll();
		grafica.add(grafico);
		marco.add(grafica);
    }
    void examinar() {
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
		}
    	
    	JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("csv","csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	ruta = String.valueOf(chooser.getSelectedFile());
        }
    }
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == examinar) {
			examinar();
			rutaArch.setText(ruta);
		}
		else if(e.getSource() == generar) {
			if(ruta != null) {
				if(!tituloG.getText().replace(" ","").equals("")) {
					Ploteo.limpiar();
					Ploteo.leer(ruta);
					actualizar();
					rutaArch.setText("");
					tituloG.setText("");
					ruta = null;
				}else {
					JOptionPane.showMessageDialog(null, "Ingrese un Título");
				}
			}else {
				JOptionPane.showMessageDialog(null, "No se ha cargado ningún archivo");
			}
		}
		else if(e.getSource() == ordenar) {
			if(coordenadas[0] != null) {
				if(bubble.isSelected()) {
					if(ascendente.isSelected()) {
						grafico.animar("BubbleSort", "Ascendente");
					}
					else if(descendente.isSelected()) {
						grafico.animar("BubbleSort", "Descendente");
					}
				}
				else if(insertion.isSelected()) {
					if(ascendente.isSelected()) {
						grafico.animar("InsertionSort", "Ascendente");
					}
					else if(descendente.isSelected()) {
						grafico.animar("InsertionSort", "Descendente");
					}
				}
				else if(shell.isSelected()) {
					if(ascendente.isSelected()) {
						grafico.animar("ShellSort", "Ascendente");
					}
					else if(descendente.isSelected()) {
						grafico.animar("ShellSort", "Descendente");
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "No hay datos cargados");
			}
		}
	}
}