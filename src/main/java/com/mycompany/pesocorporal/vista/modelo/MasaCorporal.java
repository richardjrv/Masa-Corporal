package com.mycompany.pesocorporal.vista.modelo;

public class MasaCorporal implements IMasaCorporal {
    @Override
    public String calcularIMC(double peso, double altura) {
        double calculo = peso / (altura * altura);
        String mensaje;

        if (calculo < 18.5) {
            mensaje = "Bajo peso";
        } else if (calculo >= 18.5 && calculo <= 24.9) {
            mensaje = "Peso normal";
        } else if (calculo >= 25 && calculo <= 29.9) {
            mensaje = "Sobrepeso";
        } else {
            mensaje = "Obesidad";
        }

        return mensaje;
    }
}
