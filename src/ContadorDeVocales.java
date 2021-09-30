
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class ContadorDeVocales{

	String contador="";//String que tiene como funcion almacenar el texto una vez leído
	int vocales=0;//Cuenta vocales
	char x=' ';//Caracter que determina las vocales que se van a contar
	Scanner y = new Scanner(System.in);

	public String contarvocales(File archivo) {//Metodo que cuenta las vocales sin dinsticcion
		try    {    
			FileReader lee = new FileReader(archivo);    
			int contar = lee.read();    

			while(contar != -1)    {   
				x=(char)contar;
				x=Character.toLowerCase(x);
				switch(x) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
				case 'á':
				case 'é':
				case 'í':
				case 'ó':
				case 'ú':
				case 'ä':
				case 'ë':
				case 'ï':
				case 'ö':
				case 'ü':
					vocales++;
					break;
				}
				contador+=(char)contar;
				contar = lee.read(); 

			}    
			lee.close();  

		}    catch    (IOException    ex)    {    
			System.out.println("Something    bad    has    happended    :-Â­-(");    
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
			System.out.println("Something    bad    has    happended    :-Â­-(");    
		}
		return "El fichero tiene "+palabras+" palabras";
	} 
	public ArrayList contarpalabrasrepetidas(File archivo) {

		ArrayList<Ordenar> lista = new ArrayList<Ordenar>();
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
			String [] tokens = contador.split("[,.;: ?!()]");

			for (int i = 0; i <= tokens.length-1; i++) {
				lista.add(new Ordenar(tokens[i],0));
			}

			for(Ordenar x : lista){
				for(Ordenar i : lista){
					if(x.getPalabra().equals(i.getPalabra())) {
						x.setRepetidas(x.getRepetidas()+1);
					}
				}
			}

			Set<Ordenar> set = new HashSet<Ordenar>(lista);
			lista.clear();
			lista.addAll(set);
			Collections.sort(lista);

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
		ArrayList<Ordenar> x= new ArrayList<Ordenar>();

		System.out.println("1)Contar vocales del fichero sin distinccion de mayusculas");
		System.out.println("2)Contar numero de palabras");
		System.out.println("3)Contar palabras repetidas");

		seleccion=y.nextInt();

		if(seleccion==1) {
			html=contarvocales(archivo);
			enunciado="Vocales del fichero sin distinccion de mayusculas:";
		}else if(seleccion==2) {
			html=contarpalabras(archivo);
			enunciado="Numero de palabras del fichero:";
		}else if(seleccion==3) {
			x=contarpalabrasrepetidas(archivo);
			enunciado="Palabras repetidas: ";
		}
		System.out.println("1)¿Desea generar un documento html?");
		System.out.println("1)Si");
		System.out.println("2)No");
		int seleccion2=0;
		seleccion2=y.nextInt();

		y.nextLine();

		if(seleccion2==1) {
			System.out.println("Escriba la ruta donde guardar el documento html");
			s=y.nextLine();

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

					for(Ordenar u : x) {
						if(u.getRepetidas()>1) {
							pw.write("La palabra "+u.getPalabra()+" se repite "+u.getRepetidas()+" veces</br>");
						}
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
