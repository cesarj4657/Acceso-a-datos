import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class ContadorDeVocales{

	String contador="";
	int vocales=0;
	char x=' ';
	Scanner y = new Scanner(System.in);

	public String contarvocalessindistinccion(File archivo) {
		try    {    
			FileReader lee = new FileReader(archivo);    
			int contar = lee.read();    

			while(contar != -1)    {   
				x=(char)contar;
				x=Character.toLowerCase(x);
				switch(x) {
				case 'a':
				case '�':
				case '�':
				case 'e':
				case '�':
				case '�':
				case 'i':
				case '�':
				case '�':
				case 'o':
				case '�':
				case '�':
				case 'u':
				case '�':
				case '�':
					vocales++;
					break;
				}
				contador+=(char)contar;
				contar = lee.read(); 

			}    
			lee.close();  

		}    catch    (IOException    ex)    {    
			System.out.println("Something    bad    has    happended    :-�-(");    
		}  
		return "El fichero tiene "+vocales+" vocales";
	} 
	public String contarpalabras(File archivo) {
		int palabras=1;
		try{    
			FileReader lee = new FileReader(archivo);    
			int  contar  = lee.read();    

			while(contar!=-1){   

				x=(char)contar;
				if(x==' ') {
					palabras++;
				}
				contador+=(char)contar;
				contar = lee.read(); 
			}    
			System.out.println();

			lee.close();       
		}catch(IOException ex){    
			System.out.println("Something    bad    has    happended    :-�-(");    
		}
		return "El fichero tiene "+palabras+" palabras";
	} 
	public ArrayList contarpalabrasrepetidas(File archivo) {
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<String> lista2 = new ArrayList<String>();
		FileReader lee;
		int repetidas=0,contarp=0;
		Set<String> s = new LinkedHashSet<>();
		try {
			lee = new FileReader(archivo);
			int contar = lee.read();    

			while(contar != -1)    {   
				x=(char)contar;
				contador+=(char)contar;
				contar = lee.read();
			}
			lee.close();
			String [] tokens = contador.split("[,.;: ?��!()]");

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
			s = new LinkedHashSet<>(lista2);

			for(String u: s) {
				lista.add(u);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;   

	}
	public void segundomenu(File archivo) {
		int seleccion=0;
		String html="";
		String s=null;
		String enunciado="";
		ArrayList<String> x= new ArrayList<>();

		System.out.println("1)Contar vocales del fichero sin distincci�n de may�sculas");
		System.out.println("2)Contar numero de palabras");
		System.out.println("3)Contar palabras repetidas");

		seleccion=y.nextInt();

		if(seleccion==1) {
			html=contarvocalessindistinccion(archivo);
			enunciado="Vocales del fichero sin distincci�n de may�sculas:";
		}else if(seleccion==2) {
			html=contarpalabras(archivo);
			enunciado="Numero de palabras del fichero:";
		}else if(seleccion==3) {
			x=contarpalabrasrepetidas(archivo);
		}else {

		}

		System.out.println("�Desea generar un documento html?");
		System.out.println("1)Si");
		System.out.println("2)No");

		seleccion=y.nextInt();

		y.nextLine();

		if(seleccion==1) {
			System.out.println("Escriba la ruta donde guardar el documento html");
			s=y.nextLine();
			if(s!=null) {
				try{
					FileWriter fichero = null;
					PrintWriter pw = null;

					fichero = new FileWriter(s);
					pw = new PrintWriter(fichero);

					pw.write("<html><h1>"+enunciado+"</h1>");
					pw.write("<body>"+html+"</br></body></html>");
					fichero.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(x!=null){
				try{
					FileWriter fichero = null;
					PrintWriter pw = null;
					fichero = new FileWriter(s);
					pw = new PrintWriter(fichero);
					pw.write("<html><h1>"+enunciado+"</h1>");
					
					for(String u : x) {
						pw.write("<body>"+u+"</br></body></html>");
					}
					
					fichero.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}