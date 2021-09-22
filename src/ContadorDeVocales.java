import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class ContadorDeVocales{

	String contador="";
	int vocales=0;
	char x=' ';
	Scanner y = new Scanner(System.in);

	public void contarvocalessindistinccion(File archivo) {
		try    {    
			FileReader lee = new FileReader(archivo);    
			int contar = lee.read();    

			while(contar != -1)    {   
				x=(char)contar;
				x=Character.toLowerCase(x);
				switch(x) {
				case 'a':
				case 'á':
				case 'ä':
				case 'e':
				case 'é':
				case 'ë':
				case 'i':
				case 'í':
				case 'ï':
				case 'o':
				case 'ó':
				case 'ö':
				case 'u':
				case 'ú':
				case 'ü':
					vocales++;
					break;
				}
				contador+=(char)contar;
				contar = lee.read(); 

			}    
			System.out.println("El fichero tiene "+vocales+" vocales");

			lee.close();       
		}    catch    (IOException    ex)    {    
			System.out.println("Something    bad    has    happended    :-­-(");    
		}    
	} 
	public void contarpalabras(File archivo) {
		try{    
			FileReader lee = new FileReader(archivo);    
			int  contar  = lee.read();    
			int palabras=1;
			while(contar!=-1){   

				x=(char)contar;
				if(x==' ') {
					palabras++;
				}
				contador+=(char)contar;
				contar = lee.read(); 
			}    
			System.out.println("El fichero tiene "+palabras+" palabras");

			lee.close();       
		}catch(IOException ex){    
			System.out.println("Something    bad    has    happended    :-­-(");    
		}
	} 
	public void contarpalabrasrepetidas(File archivo) {
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<String> lista2 = new ArrayList<String>();
		FileReader lee;
		int repetidas=0,contarp=0;
		try {
			lee = new FileReader(archivo);
			int contar = lee.read();    

			while(contar != -1)    {   
				x=(char)contar;
				contador+=(char)contar;
				contar = lee.read();
			}
			lee.close();
			String [] tokens = contador.split("[,.;: ?¿¡!()]");

			for (int i = 0; i <= tokens.length-1; i++) {
				lista.add(tokens[i]);
			}

			for(String i: lista) {
				for(String x: lista) {
					if(i.equals(x)) {
						repetidas++;
					}
				}
				if(repetidas>=2) {
					lista2.add(i+" se repite " + repetidas + " veces");
					repetidas=0;
				}
				repetidas=0;
			}
			Set<String> s = new LinkedHashSet<>(lista2);
			
			for(String u: s) {
				System.out.println(u);
			}
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   

	}
	public void segundomenu(File archivo) {
		int seleccion=0;
		String html="";
		System.out.println("1)Contar vocales del fichero sin distincción de mayúsculas");
		System.out.println("2)Contar numero de palabras");
		System.out.println("3)Contar palabras repetidas");
		seleccion=y.nextInt();
		if(seleccion==1) {
			this.contarvocalessindistinccion(archivo);
		}else if(seleccion==2) {
			this.contarpalabras(archivo);
		}else if(seleccion==3) {
			this.contarpalabrasrepetidas(archivo);
		}else {

		}
		System.out.println("¿Desea generar un documento html?");
		System.out.println("1)Si");
		System.out.println("2)No");
		seleccion=y.nextInt();

		if(seleccion==1) {
			System.out.println("Nombre del documento html");
			html=y.nextLine();
			FileWriter fichero = null;
			PrintWriter pw = null;
			try
			{
				fichero = new FileWriter("c:/prueba.txt");
				pw = new PrintWriter(fichero);

				for (int i = 0; i < 10; i++)
					pw.println("Linea " + i);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != fichero)
						fichero.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	}
}