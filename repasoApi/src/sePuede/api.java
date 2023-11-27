/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sePuede;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Gabo
 */
public class api {
    
    public DefaultTableModel mostrar(javax.swing.JTable tabla, String nombre, String direccion){
        
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            
            String api="http://localhost/sisePuede/api.php?nombre="+nombre+"&direccion="+direccion;
            
            HttpGet get = new HttpGet(api);
            HttpClient cliente = HttpClientBuilder.create().build();
            HttpResponse respuesta = cliente.execute(get);
            String info = EntityUtils.toString(respuesta.getEntity());
            JSONArray jsonArray = new  JSONArray(info);
            
            for (int i = 0; i < jsonArray.length(); i++) {
                
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                
                String codigo = jsonObject.getString("codigo");
                String producto = jsonObject.getString("nombre");
                String descripcion = jsonObject.getString("descripcion");
                double precio = jsonObject.getDouble("precio");
                String cod_bodega = jsonObject.getString("cod_bodega");
                modelo.addRow(new Object[]{codigo,producto,descripcion,precio,cod_bodega});
            }
            
            return modelo;
        } catch (IOException ex) {
            Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
   public DefaultTableModel mostrarTodo(javax.swing.JTable tabla){
        
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            
            String api="http://localhost/sisePuede/api.php";
            
            HttpGet get = new HttpGet(api);
            HttpClient cliente = HttpClientBuilder.create().build();
            HttpResponse respuesta = cliente.execute(get);
            String info = EntityUtils.toString(respuesta.getEntity());
            JSONArray jsonArray = new  JSONArray(info);
            
            for (int i = 0; i < jsonArray.length(); i++) {
                
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                
                String codigo = jsonObject.getString("codigo");
                String producto = jsonObject.getString("nombre");
                String descripcion = jsonObject.getString("descripcion");
                double precio = jsonObject.getDouble("precio");
                String cod_bodega = jsonObject.getString("cod_bodega");
                modelo.addRow(new Object[]{codigo,producto,descripcion,precio,cod_bodega});
            }
            
            return modelo;
        } catch (IOException ex) {
            Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
    public void insertar(String codigo, String nombre,String descripcion,Double precio,String cod_bodega){
        
         try{
            String url="http://localhost/sisePuede/api.php";
            HttpClient cliente = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            ArrayList<BasicNameValuePair> parametros = new ArrayList<BasicNameValuePair>();
            parametros.add(new BasicNameValuePair("codigo",codigo));
            parametros.add(new BasicNameValuePair("nombre",nombre));
            parametros.add(new BasicNameValuePair("descripcion",descripcion));
            parametros.add(new BasicNameValuePair("precio",String.valueOf(precio)));
            parametros.add(new BasicNameValuePair("cod_bodega",cod_bodega));
            post.setEntity(new UrlEncodedFormEntity(parametros));
            cliente.execute(post);
         //   mostrarDatos();
        }catch(Exception e){
            System.out.println("Error  : "+e);
        }
        
    }
    
     public void editar(String cedula , String nombre , String apellido , String telefono , String direccion) {
        try{
        String apiUrl="http://localhost/sisePuede/api.php";
        String urlParametros="cedula="+cedula+"&nombre="+nombre+"&apellido="+apellido+"&direccion="+direccion+"&telefono="+telefono;
        
        URL url = new URL(apiUrl+"?"+urlParametros);
        HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("PUT");
           connection.getResponseCode();
       
        
        
        }catch(Exception e){
            
        }
    }
    
       public void eliminar(String cedula) {
        try{
        String apiUrl="http://localhost/sisePuede/api.php";
        String urlParametros="cedula="+cedula;
        
        URL url = new URL(apiUrl+"?"+urlParametros);
        HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
           connection.getResponseCode();
   
        
        
        }catch(Exception e){
            
        }
    }
    
}
