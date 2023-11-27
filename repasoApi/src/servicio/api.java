/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;


import javax.swing.table.DefaultTableModel;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author Gabo
 */
public class api {
      HttpGet get = new HttpGet("http://localhost/Quinto/api.php");
    public DefaultTableModel  obtener(javax.swing.JTable Tabla){
        DefaultTableModel modelo;
          modelo= (DefaultTableModel) Tabla.getModel();
           modelo.setRowCount(0);
        try{
        HttpClient cliente = HttpClientBuilder.create().build();

      
        HttpResponse respuesta=cliente.execute(get);
        String info=EntityUtils.toString(respuesta.getEntity());
        JSONArray jsonArray = new JSONArray(info);
            for (int i = 0; i < jsonArray.length(); i++)
             {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String cedula = jsonObject.getString("cedula");
                String nombre = jsonObject.getString("nombre");
                String apellido = jsonObject.getString("apellido");
                String direccion = jsonObject.getString("direccion");
                String telefono = jsonObject.getString("telefono");
                modelo.addRow(new Object[]{cedula,nombre,apellido,direccion,telefono});
            }
            return modelo;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
     
    }
    
    
     public DefaultTableModel mostrar(javax.swing.JTable tabla, String nivel, String paralelo) {
         
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        try {
            String apiUrl = "http://localhost/repasoApi/api.php?nivel=" + nivel + "&paralelo=" + paralelo;
            HttpGet get = new HttpGet(apiUrl);
            HttpClient cliente = HttpClientBuilder.create().build();
            HttpResponse respuesta = cliente.execute(get);
            String info = EntityUtils.toString(respuesta.getEntity());
            
            System.out.println("Contenido de info: " + info); // Verificar el contenido de la respuesta

            if (info.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(info);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String cedula = jsonObject.getString("id");
                    String nombre = jsonObject.getString("nombre");
                    String apellido = jsonObject.getString("apellido");
                    String codigo = jsonObject.getString("cod_curso");
                    modelo.addRow(new Object[]{cedula, nombre, apellido, codigo});
                }

                return modelo;
            } else {
                System.out.println("El texto de la respuesta no tiene el formato adecuado de un JSONArray.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
