import java.net.*;
import java.io.*;

public class LeerArchivoURL {
	
	public static void main (String args[]) {
		
		// define un objeto url asociado a un recurso de Internet		
		URL url = null;
		
        try {
			// crear la URL
			url = new URL("ftp://ftp.rediris.es/welcome.msg");

			// se conecta a esa URL 
			// => no hace falta ya que se usa más abajo openStream() 
			// que es similar a openConnection().getInputStream()
			// url.openConnection();

			//Asocia un flujo de entrada a la conexión URL
			InputStream flujoIn = url.openStream();

			//Crea flujo de salida asociado al destino
			FileOutputStream flujoOutFile = new FileOutputStream("fichero.txt");

			// Definir el buffer de lectura con un tamaño de 512 bytes
			byte [] buffer = new byte [512];
			int BytesLeidos, totalBytesLeidos = 0;
			
			System.out.println ("Escribiendo en el archivo 'fichero.txt'" + 
				" el contenido de 'ftp://ftp.rediris.es/welcome.msg'\n");
			
			//mientras hay bytes leer del archivo			
			while ((BytesLeidos = flujoIn.read(buffer)) > 0) {
				//almacena lo que lee en el buffer en el fichero de destino "fichero.txt"
				flujoOutFile.write(buffer, 0, BytesLeidos);
				totalBytesLeidos += BytesLeidos;
				System.out.print (".");				
			}
			
			System.out.println ("\nCopia finalizada!!");
            
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e); 
       
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe);
        }	
		
	}
}
