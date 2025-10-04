package com.mycompany.pesocorporal.controlador;

import com.mycompany.pesocorporal.vista.PantallaPrincipal;
import com.mycompany.pesocorporal.vista.modelo.IMasaCorporal;
import com.mycompany.pesocorporal.vista.modelo.MasaCorporal;

// **NUEVAS IMPORTACIONES** para manejar el video
import java.io.File;
import java.awt.Desktop;
import javax.swing.JOptionPane; 

public class Controlador {
   private PantallaPrincipal vista;
   private MasaCorporal modelo;
   private final IMasaCorporal iMasaCorporal;
   
    public Controlador (PantallaPrincipal pantallaPrincipal) {
        this.vista = pantallaPrincipal;
        this.iMasaCorporal= new MasaCorporal();
    }
   
    
    /**
     * Lógica principal del controlador: obtiene datos, calcula IMC, 
     * muestra resultado y maneja la reproducción del video.
     */
    public void procesoControlar(){
      double altura;
      double peso;
      String genero;
      String resultado;
      
      // **MEJORA**: Manejo de errores de conversión
      try {
        altura = Double.parseDouble(vista.getAltura());
        peso = Double.parseDouble(vista.getPeso());
        genero = vista.getGenero(); // **NUEVO**: Obtiene el género
      } catch (NumberFormatException e) {
          // Muestra error si no son números
          vista.setResultado("Error: Ingresa números válidos en Peso y Altura.");
          return; // Detiene el proceso
      }
      
      resultado = iMasaCorporal.calcularIMC(peso, altura);
      vista.setResultado(resultado);
      
      // **LÓGICA CONDICIONAL PARA EL VIDEO**
      // Condición: Si es "Mujer" Y el resultado es "Sobrepeso" u "Obesidad"
      if (genero.equals("Mujer")) {
          // Usamos .contains() ya que tu método MasaCorporal devuelve la palabra exacta
          if (resultado.equals("Sobrepeso") || resultado.equals("Obesidad")) {
              reproducirVideo();
          }
      }
    }
    
    /**
     * Abre el archivo de video en la ruta especificada usando el programa predeterminado del sistema.
     */
    private void reproducirVideo() {
        // **RUTA DEL VIDEO** - ¡Debe existir en tu sistema!
        String rutaVideo = "C:\\Users\\PERSONAL\\Downloads\\Video_java.mp4"; 
        File archivoVideo = new File(rutaVideo);
        
        // Verifica si la función de Desktop es soportada por el sistema operativo
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            
            // Verifica si el archivo existe
            if (archivoVideo.exists()) {
                try {
                    desktop.open(archivoVideo);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vista, 
                        "Error al intentar abrir el video. Detalles: " + e.getMessage(), 
                        "Error de Reproducción", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                 // Si el archivo no se encuentra
                JOptionPane.showMessageDialog(vista, 
                    "El archivo de video NO se encuentra en la ruta: " + rutaVideo, 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
             // Si la función Desktop no es compatible (ej. en servidores)
             JOptionPane.showMessageDialog(vista, 
                    "Tu sistema operativo no soporta la función para abrir archivos automáticamente.", 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}