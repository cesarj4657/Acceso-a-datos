import java.io.File;
import java.io.IOException;
import java.util.*;
public class Menu {

	public static void main(String[] args) {

		String respuesta=" ";
		int seleccion=0;
		Scanner y = new Scanner(System.in);


		ContadorDeVocales x =new ContadorDeVocales();
		System.out.println("1)Crear un fichero");
		System.out.println("2)Usar un fichero existente");
		seleccion=y.nextInt();

		if(seleccion==1) {
			y.nextLine();

			System.out.println("Escriba el nombre del fichero");

			respuesta=y.nextLine();
			File fichero = new File(respuesta);

			try {
				if(fichero.exists()) {
					System.out.println("Ya existe un fichero con ese nombre");
				}else{
					fichero.createNewFile();
					System.out.println("El fichero se ha creado con exito");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else if(seleccion==2) {
			y.nextLine();

			System.out.println("Escriba el nombre del fichero / o la ruta");

			respuesta=y.nextLine();
			File fichero = new File(respuesta);
			x.segundomenu(fichero);
		}

	}

}
