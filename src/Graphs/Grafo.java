package Graphs;
import java.io.*;
import java.io.File;
import java.util.*;
import java.util.LinkedList;

public class Grafo {
  private int N;
  private LinkedList<Integer> adjList[];

Grafo() {

  }

void grafoTam(int n) {
    N = n;
    adjList = new LinkedList[n];
    for (int i = 0; i < n; i++) {
      adjList[i] = new LinkedList();
    }
}

  void AgregarArista(int x, int y) {
    adjList[x].add(y);
    adjList[y].add(x);
  }
  void NumeroCromatico(int arr[]) {
    int size = arr.length;
    Set<Integer> hashSet = new HashSet<Integer>();

    for (int j = 0; j < size; j++) {
      hashSet.add(arr[j]);
    }

    int encontrarChromatico = hashSet.size();

    System.out.println("El Numero cromático es: " + encontrarChromatico);
  }

  void AristasColoreadas(int number_colored) {
    int res[] = new int[N];
    Arrays.fill(res, -1);
    res[0] = number_colored;

    boolean avail[] = new boolean[N];
    Arrays.fill(avail, true);
    for (int n = 1; n < N; n++) {
      Iterator<Integer> itr = adjList[n].iterator();
      while (itr.hasNext()) {
        int i = itr.next();
        if (res[i] != -1) avail[res[i]] = false;
      }

      int colorDisponible;
      for (colorDisponible = 0; colorDisponible < N; colorDisponible++) {
        if (avail[colorDisponible]) {
          break;
        }
      }

      res[n] = colorDisponible;

      Arrays.fill(avail, true);
    }

    NumeroCromatico(res);
  }

  public static void main(String argvs[]) throws Exception {

	String numeroAristas = null;
	String colorInicial = null;
    File file = new File("grafoColor5 - 3.txt");
   
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
  		Grafo grafo = new Grafo();
      while ((line = br.readLine()) != null) {
        String[] aristas = line.split(" ");
        if(aristas.length == 1) {
        	
        	numeroAristas = aristas[0];
        	aristas[0] = br.readLine();
        	colorInicial = aristas[0];
        	grafo.grafoTam(Integer.parseInt(numeroAristas));
        	
        	
        }
        if (aristas.length > 1) {
        	grafo.AgregarArista(
            Integer.parseInt(aristas[0]),
            Integer.parseInt(aristas[1])
          );

        }
  
      }
	
		grafo.AristasColoreadas(Integer.parseInt(colorInicial));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
