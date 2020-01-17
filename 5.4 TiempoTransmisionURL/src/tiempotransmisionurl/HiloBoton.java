package tiempotransmisionurl;

import java.io.*;
//
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
/**
 * ****************************************************************************
 * hilo para medir el tiempo que tarda en transmitirse un recurso URL tecleado 
 * por el usuario, desde el servidor hasta el cliente
 *
 * se basa en el encabezado 'Date', donde el servidor le envía al cliente el
 * tiempo transcurrido (en milisegundos) desde el 1 de enero de 1970 GMT, hasta
 * el inicio de la transmisión
 *
 */
class HiloBoton extends Thread {
  //variables locales
  private final String cadenaURL;
  /**
   * **************************************************************************
   * constructor
   *
   * @param cadenaURL
   */
  public HiloBoton(String cadenaURL) {
    //
    this.cadenaURL = cadenaURL;
  }
  /**
   * **************************************************************************
   * código del hilo
   *
   */
  @Override
  public void run() {
    //
    try {
      //crea un objeto de la clase URL en base a la cadena URL
      URL url = new URL(cadenaURL);

      /*Devuelve un objeto URLConnection que representa una nueva conexión
       con el recurso remoto al que se refiere la URL.*/
      URLConnection conexion = url.openConnection();

      /*Establece una conexión entre la aplicación (el cliente) y el recurso (el servidor),
       permite interactuar con el recurso y consultar los tipos de cabeceras
       y contenidos para determinar el tipo de recurso de que se trata*/
      conexion.connect();

      //fuerza la transmisión del recurso mediante su lectura byte a byte
      InputStream inputStream = conexion.getInputStream();
      while(inputStream.read()>-1){ }

      //instante 'fin de transmisión', con respecto al 1 de enero de 1970 GTM
      long tiempoCliente = System.currentTimeMillis();

      //instante 'inicio de transmisión', con respecto al 1 de enero de 1970 GTM
      long tiempoServidor = conexion.getDate();

      //tiempo transcurrido
      System.out.println(String.format("El tiempo de transmisión del recurso "
              + "ha sido de %sms", Math.round(tiempoCliente - tiempoServidor)));
      
    } catch (MalformedURLException e) {
      //
      System.err.println("URL sin sentido");
    } catch (IOException e) {
      //
      System.err.println("Error de lectura/escritura");
    } finally {
      //termina la aplicación
      System.exit(0);
    }
  }
}
