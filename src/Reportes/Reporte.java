package Reportes;

import Datos.Coordenada;
import static Datos.Ploteo.ejes;
public class Reporte {
	public static final String inicioHtml = "<!DOCTYPE html>\r\n"
			+ "<html lang=\"en\">\r\n"
			+ "    <head>\r\n"
			+ "        <title>Reporte</title>\r\n"
			+ "        <meta charset=\"UTF-8\">\r\n"
			+ "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
			+ "        <link rel=\"icon\" type=\"image/png\" href=\"Reporte/images/icons/logousac.png\"/>\r\n"
			+ "        <link rel=\"stylesheet\" type=\"text/css\" href=\"Reporte/vendor/bootstrap/css/bootstrap.min.css\">\r\n"
			+ "        <link rel=\"stylesheet\" type=\"text/css\" href=\"Reporte/fonts/font-awesome-4.7.0/css/font-awesome.min.css\">\r\n"
			+ "        <link rel=\"stylesheet\" type=\"text/css\" href=\"Reporte/vendor/animate/animate.css\">\r\n"
			+ "        <link rel=\"stylesheet\" type=\"text/css\" href=\"Reporte/vendor/select2/select2.min.css\">\r\n"
			+ "        <link rel=\"stylesheet\" type=\"text/css\" href=\"Reporte/vendor/perfect-scrollbar/perfect-scrollbar.css\">\r\n"
			+ "        <link rel=\"stylesheet\" type=\"text/css\" href=\"Reporte/css/util.css\">\r\n"
			+ "        <link rel=\"stylesheet\" type=\"text/css\" href=\"Reporte/css/main.css\">\r\n"
			+ "    </head>\r\n"
			+ "    <body>\r\n"
			+ "        <div class=\"limiter\">\r\n"
			+ "            <div class=\"container-table100\">\r\n"
			+ "                <div class=\"wrap-table100\">\r\n"
			+ "                    <div class=\"table100\">\r\n";
	public static final String tabMiInfo = "                        <table style=\"margin-bottom: 50px;\">\r\n"
			+ "                            <thead>\r\n"
			+ "                                <tr class=\"table100-head\">\r\n"
			+ "                                    <th class=\"column1\">\r\n"
			+ "                                        202112030 - Brandon Andy Jefferson Tejax&uacute;n Pichiy&aacute;\r\n"
			+ "                                    </th>\r\n"
			+ "                                </tr>\r\n"
			+ "                            </thead>\r\n"
			+ "                        </table>\r\n";
	public static String getInfoOrd(String algoritmo,String tiempo,String pasos) {
		return "                        <table style=\"margin-bottom: 50px;\">\r\n"
				+ "                            <thead>\r\n"
				+ "                                <tr class=\"table100-head\">\r\n"
				+ "                                    <th class=\"column1\">\r\n"
				+ "                                        Algoritmo usado: " + algoritmo + "\r\n"
				+ "                                    </th>\r\n"
				+ "                                    <th class=\"column3\">\r\n"
				+ "                                        Tiempo transcurrido: " + tiempo + "\r\n"
				+ "                                    </th>\r\n"
				+ "                                    <th class=\"column6\">\r\n"
				+ "                                        Pasos: " + pasos + "\r\n"
				+ "                                    </th>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </thead>\r\n"
				+ "                        </table>\r\n";
	}
	public static final String divCuadrar = "                        <div class=\"cuadrarTablas\">\r\n";
	
	public static final String titulosT = "                            <p style=\"color:white;font-size: 30px;\">Datos Desordenados</p>\r\n"
			+ "                            <p style=\"color:white;font-size: 30px;\">Datos Ordenados</p>"
			+ "\r\n";
	public static String getTabla(Coordenada [] coordenadas) {
		String tabla = "                            <table style=\"margin-bottom: 50px;\">\r\n"
				+ "                                <thead>\r\n"
				+ "                                    <tr class=\"table100-head\">\r\n"
				+ "                                        <th class=\"column1\">" + ejes[0] + "</th>\r\n"
				+ "                                        <th class=\"column6\">" + ejes[1] + "</th>\r\n"
				+ "                                    </tr>\r\n"
				+ "                                </thead>\r\n";
		
		for(Coordenada coordenada : coordenadas) {
			if(coordenada != null) {
				tabla += "                                    <tr>\r\n"
						+ "                                        <td class=\"column1\">" + coordenada.getTitulo() + "</td>\r\n"
						+ "                                        <td class=\"column6\">" + coordenada.getValores() + "</td>\r\n"
						+ "                                    </tr>\r\n";
			}
		}
		
		tabla += "                                </tbody>\r\n"
				+ "                            </table>\r\n";
		return tabla;
	}
	
	public static final String imagenes = "                            <img src=\"Reporte/images/GrafInicio.png\" style=\"width:100%;border-radius: 10px;\" alt=\"\">\r\n"
			+ "                            <img src=\"Reporte/images/GrafFin.png\" style=\"width:100%;border-radius: 10px;\" alt=\"\">\r\n";
	public static final String cDivCuadrar = "                        </div>\r\n";
	public static final String finHtml = "                    </div>\r\n"
			+ "                </div>\r\n"
			+ "            </div>\r\n"
			+ "        </div>\r\n"
			+ "    </body>\r\n"
			+ "</html>";
}
