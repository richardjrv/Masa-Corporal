
package com.mycompany.pesocorporal.controlador;

import com.mycompany.pesocorporal.vista.PantallaPrincipal;
import com.mycompany.pesocorporal.vista.modelo.IMasaCorporal;
import com.mycompany.pesocorporal.vista.modelo.MasaCorporal;

public class Controlador {
   private PantallaPrincipal vista;
   private MasaCorporal modelo;
   private final IMasaCorporal iMasaCorporal;
   
    public Controlador (PantallaPrincipal pantallaPrincipal) {
        this.vista = pantallaPrincipal;
        this.iMasaCorporal= new MasaCorporal();
    }
   
   
    
    
    public void procesoControlar(){
      double altura=Double.parseDouble(vista.getAltura()) ;
      double peso=Double.parseDouble(vista.getPeso()) ;
      String resultado=iMasaCorporal.calcularIMC(peso, altura);
      vista.setResultado(resultado);
    }
}
