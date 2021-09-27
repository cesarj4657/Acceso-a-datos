package Practica1;

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
			lee.close();  

		}    catch    (IOException    ex)    {    
			System.out.println("Something    bad    has    happended    :-­-(");    
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
			System.out.println("Something    bad    has    happended    :-­-(");    
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
			s = new LinkedHashSet<>(lista2);
			lista.clear();
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

		System.out.println("1)Contar vocales del fichero sin distincción de mayúsculas");
		System.out.println("2)Contar numero de palabras");
		System.out.println("3)Contar palabras repetidas");

		seleccion=y.nextInt();

		if(seleccion==1) {
			html=contarvocalessindistinccion(archivo);
			enunciado="Vocales del fichero sin distincción de mayúsculas:";
		}else if(seleccion==2) {
			html=contarpalabras(archivo);
			enunciado="Numero de palabras del fichero:";
		}else if(seleccion==3) {
			x=contarpalabrasrepetidas(archivo);
			enunciado="Palabras repetida: ";
		}
		System.out.println("¿Desea generar un documento html?");
		System.out.println("1)Si");
		System.out.println("2)No");
		int seleccion2=0;
		seleccion2=y.nextInt();

		y.nextLine();

		if(seleccion2==1) {
			System.out.println("Escriba la ruta donde guardar el documento html");
			s=y.nextLine();
			if(s!=null) {
				if(seleccion==1||seleccion==2) {
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
				}else if(seleccion==3) {
					try{
						FileWriter fichero = null;
						PrintWriter pw = null;
						fichero = new FileWriter(s);
						pw = new PrintWriter(fichero);
						pw.write("<html><h1>"+enunciado+"</h1>"+"<body>");

						for(String u : x) {
							pw.write(u+"</br>");
						}
						pw.write("</body></html>");
						fichero.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
