/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sePuede;

/**
 *
 * @author Gabo
 */
public class Producto {
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private String cod_bodega;

    public Producto(String codigo, String nombre, String descripcion, double precio, String cod_bodega) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cod_bodega = cod_bodega;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCod_bodega() {
        return cod_bodega;
    }

    public void setCod_bodega(String cod_bodega) {
        this.cod_bodega = cod_bodega;
    }
           
    
    
}
