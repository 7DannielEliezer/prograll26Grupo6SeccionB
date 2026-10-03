/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reclutamiento.prueba2;

/**
 *
 * @author PGutierrez
 */

import reclutamiento.prueba2.model.Usuario;

public class Sesion {
    private static Usuario usuarioActual;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }
    
    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
