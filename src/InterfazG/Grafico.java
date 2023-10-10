package InterfazG;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import Componentes.Boton;
import Componentes.Colores;
import Cronometro.Cronometro;
import Datos.Coordenada;
import static Datos.Ploteo.coordenadas;
import static Datos.Ploteo.ejes;
import static Datos.Ploteo.nDatos;
import static InterfazG.Inicio.numPasos;
import static Cronometro.Cronometro.time;
import static Reportes.Reporte.*;
import java.io.File;
import java.io.PrintWriter;

public class Grafico extends JPanel implements Runnable {
	static JFreeChart chart;
	String ordenamiento;
	String sentido;
	String titulo;
	Cronometro cn;
	Boton examinar, generar, ordenar;
	String infoOrd, tablaInicio, tablaFin;
	private static final long serialVersionUID = 1L;

	public Grafico(String titulo, Cronometro cn, Boton examinar, Boton generar, Boton ordenar) {
		this.titulo = titulo;
		this.cn = cn;
		this.examinar = examinar;
		this.generar = generar;
		this.ordenar = ordenar;
		setBounds(0, -5, 785, 500);
		setVisible(true);
		add(generarGrafica());
	}

	ChartPanel generarGrafica() {
		chart = ChartFactory.createBarChart(
				titulo,
				ejes[0],
				ejes[1],
				dataset(),
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(getWidth(), getHeight()));
		return chartPanel;
	}

	DefaultCategoryDataset dataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Coordenada coord : coordenadas) {
			if (coord != null) {
				dataset.setValue(coord.getValores(), coord.getTitulo(), "");
			}
		}
		return dataset;
	}

	public static void saveImg(String nombre) {
		int width = 640;
		int height = 480;
		File lineChart = new File("Reporte/images/" + nombre + ".png");
		try {
			ChartUtils.saveChartAsPNG(lineChart, chart, width, height);
		} catch (Exception e) {
		}
	}

	void generarReporte() {
		String reporteHtml = inicioHtml;
		reporteHtml += tabMiInfo;
		reporteHtml += infoOrd;
		reporteHtml += divCuadrar;
		reporteHtml += titulosT;
		reporteHtml += tablaInicio;
		reporteHtml += tablaFin;
		reporteHtml += imagenes;
		reporteHtml += cDivCuadrar;
		reporteHtml += finHtml;
		try {
			PrintWriter reporte = new PrintWriter(new File("Reporte.html"), "UTF-8");
			reporte.write(reporteHtml);
			reporte.close();
		} catch (Exception e) {
		}
	}

	public void animar(String ordenamiento, String sentido) {
		this.ordenamiento = ordenamiento;
		this.sentido = sentido;
		Thread animacion = new Thread(this);
		animacion.start();
	}

	public void run() {
		try {
			examinar.setEnabled(false);
			generar.setEnabled(false);
			ordenar.setEnabled(false);
			ordenar.diseno(3, Colores.gris2, Colores.gris3);
			int nPasos = 0;
			saveImg("GrafInicio");
			tablaInicio = getTabla(coordenadas);
			if (ordenamiento.equals("BubbleSort")) {
				if (sentido.equals("Ascendente")) {
					cn.iniciarCronometro();
					for (int i = 0; i < nDatos - 1; i++) {
						for (int x = 0; x < nDatos - i - 1; x++) {
							if (coordenadas[x].getValores() > coordenadas[x + 1].getValores()) {
								Coordenada temporal = coordenadas[x];
								coordenadas[x] = coordenadas[x + 1];
								coordenadas[x + 1] = temporal;
								removeAll();
								add(generarGrafica());
								revalidate();
								repaint();
								nPasos++;
								numPasos.setText(String.valueOf(nPasos));
								Thread.sleep(300);
							}
						}
					}
					cn.pararCronometro();
				} else if (sentido.equals("Descendente")) {
					cn.iniciarCronometro();
					for (int i = 0; i < nDatos - 1; i++) {
						for (int x = 0; x < nDatos - i - 1; x++) {
							if (coordenadas[x].getValores() < coordenadas[x + 1].getValores()) {
								Coordenada temporal = coordenadas[x];
								coordenadas[x] = coordenadas[x + 1];
								coordenadas[x + 1] = temporal;
								removeAll();
								add(generarGrafica());
								revalidate();
								repaint();
								nPasos++;
								numPasos.setText(String.valueOf(nPasos));
								Thread.sleep(300);
							}
						}
					}
					cn.pararCronometro();
				}
				infoOrd = getInfoOrd("Bubble Sort", time.getText(), numPasos.getText());
			} else if (ordenamiento.equals("InsertionSort")) {
				if (sentido.equals("Ascendente")) {
					cn.iniciarCronometro();
					for (int i = 1; i < nDatos; i++) {
						for (int x = i; x > 0; x--) {
							if (coordenadas[x].getValores() < coordenadas[x - 1].getValores()) {
								Coordenada temporal = coordenadas[x];
								coordenadas[x] = coordenadas[x - 1];
								coordenadas[x - 1] = temporal;
								removeAll();
								add(generarGrafica());
								revalidate();
								repaint();
								nPasos++;
								numPasos.setText(String.valueOf(nPasos));
								Thread.sleep(300);
							}
						}
					}	
					cn.pararCronometro();
				} else if (sentido.equals("Descendente")) {
					cn.iniciarCronometro();
					for (int i = 1; i < nDatos; i++) {
						for (int x = i; x > 0; x--) {
							if (coordenadas[x].getValores() > coordenadas[x - 1].getValores()) {
								Coordenada temporal = coordenadas[x];
								coordenadas[x] = coordenadas[x - 1];
								coordenadas[x - 1] = temporal;
								removeAll();
								add(generarGrafica());
								revalidate();
								repaint();
								nPasos++;
								numPasos.setText(String.valueOf(nPasos));
								Thread.sleep(300);
							}
						}
					}
					cn.pararCronometro();
				}
				infoOrd = getInfoOrd("Insertion Sort", time.getText(), numPasos.getText());
			} else if (ordenamiento.equals("ShellSort")) {
				if (sentido.equals("Ascendente")) {
					cn.iniciarCronometro();
					int n = nDatos;
					for (int gap = n / 2; gap > 0; gap /= 2) {
						for (int i = gap; i < n; i++) {
							Coordenada key = coordenadas[i];
							for (int j = i; j >= gap && coordenadas[j - gap].getValores() > key.getValores(); j -= gap) {
								coordenadas[j] = coordenadas[j - gap];
								coordenadas[j - gap] = key;
								removeAll();
								add(generarGrafica());
								revalidate();
								repaint();
								nPasos++;
								numPasos.setText(String.valueOf(nPasos));
								Thread.sleep(300);
							}
						}
					}
					cn.pararCronometro();
				} else if (sentido.equals("Descendente")) {
					cn.iniciarCronometro();
					int n = nDatos;
					for (int gap = n / 2; gap > 0; gap /= 2) {
						for (int i = gap; i < n; i++) {
							Coordenada key = coordenadas[i];
							for (int j = i; j >= gap && coordenadas[j - gap].getValores() < key.getValores(); j -= gap) {
								coordenadas[j] = coordenadas[j - gap];
								coordenadas[j - gap] = key;
								removeAll();
								add(generarGrafica());
								revalidate();
								repaint();
								nPasos++;
								numPasos.setText(String.valueOf(nPasos));
								Thread.sleep(300);
							}
						}
					}
					cn.pararCronometro();
				}
				infoOrd = getInfoOrd("Shell Sort", time.getText(), numPasos.getText());
			}
			saveImg("GrafFin");
			tablaFin = getTabla(coordenadas);

			generarReporte();

			examinar.setEnabled(true);
			generar.setEnabled(true);
			ordenar.setEnabled(true);
			ordenar.diseno(3, Colores.azul2, Colores.azul3);
		} catch (Exception e) {
		}
	}
}