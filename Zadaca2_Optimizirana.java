import java.util.Scanner;

/**
 * PSAA ISPIT - 02.07.2024, ZADACA 1 - OPTIMIZIRAN

*/

class Node<E> {
    protected E data;
    protected Node<E> next;
    
    public Node() {
        data = null;
        next = null;
    }
    
    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }
}

class SLinkedList<E> {
    private Node<E> head;
    
    public Node<E> getHead() {
        return head;
    }
    
    public void setHead(Node<E> n) {
        head = n;
    }
    
    public SLinkedList() {
        head = null;
    }
    
    public void insertFirst(E e) {
        Node<E> first = new Node(e, head);
        head = first;
    }
    
    public void insertLast(E e) {
        if (head != null) {
            Node<E> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            Node<E> last = new Node(e, null);
            tmp.next = last;
        } else {
            this.insertFirst(e);
        }
    }
    
    public void printList() {
        Node<E> tmp = head;
        if (tmp == null) {
            System.out.println("null (prazna lista)");
            return;
        }
        while (tmp.next != null) {
            System.out.print(tmp.data + " -> ");
            tmp = tmp.next;
        }
        System.out.println(tmp.data);
    }
}

public class Zadaca2_Optimizirana {
    
    /**
     * Helper: Proveruva ako broj e PROST
     */
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
    
    /**
     * MAIN FUNKCIJA: Kombinira dve liste
     * 
     * 
     */
    public static SLinkedList<Integer> kombinuiListi(
            SLinkedList<Integer> lista1, 
            SLinkedList<Integer> lista2) {
        
        // Edge case: prazni listi
        if (lista1.getHead() == null || lista2.getHead() == null) {
            System.out.println("Greška: Ednata od listite e prazna!");
            return new SLinkedList<>();
        }
        
        SLinkedList<Integer> rezultat = new SLinkedList<>();
        
        // Pointer za lista1 - od početok
        Node<Integer> ptr1 = lista1.getHead();
        
        // Reverse lista2 so insertFirst
        SLinkedList<Integer> reversed_lista2 = new SLinkedList<>();
        Node<Integer> ptr2_original = lista2.getHead();
        
        while (ptr2_original != null) {
            reversed_lista2.insertFirst(ptr2_original.data);
            ptr2_original = ptr2_original.next;
        }
        
        // Pointer za reversed lista2
        Node<Integer> ptr2 = reversed_lista2.getHead();
        
        // Traverse vo parovi
        while (ptr1 != null && ptr2 != null) {
            int val1 = ptr1.data;
            int val2 = ptr2.data;
            
            // Izračunaj razlika
            int razlika = Math.abs(val1 - val2);
            
            // Provera: Razlika e PROST BROJ?
            if (isPrime(razlika)) {
                // DA - dodaj MALENIOT element
                int maleniot = Math.min(val1, val2);
                rezultat.insertLast(maleniot);
                
                System.out.println("✓ Par: " + val1 + " i " + val2 + 
                                 ", razlika = " + razlika + " (PROST) → dodaj " + maleniot);
            } else {
                System.out.println("✗ Par: " + val1 + " i " + val2 + 
                                 ", razlika = " + razlika + " (NE prost) → SKIP");
            }
            
            // Pomeshta
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        
        return rezultat;
    }
    
    /**
     * MAIN PROGRAM - sa Scanner input
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========== ZADACA 2: DVE LISTE - PROST BROJ ==========\n");
        
        // Vnos lista1
        System.out.print("Vnesi broj na elementi za lista1: ");
        int n1 = scanner.nextInt();
        SLinkedList<Integer> lista1 = new SLinkedList<>();
        
        System.out.println("Vnesi elementi za lista1:");
        for (int i = 0; i < n1; i++) {
            System.out.print("  Element " + (i + 1) + ": ");
            int val = scanner.nextInt();
            lista1.insertLast(val);
        }
        
        // Vnos lista2
        System.out.print("\nVnesi broj na elementi za lista2: ");
        int n2 = scanner.nextInt();
        SLinkedList<Integer> lista2 = new SLinkedList<>();
        
        System.out.println("Vnesi elementi za lista2:");
        for (int i = 0; i < n2; i++) {
            System.out.print("  Element " + (i + 1) + ": ");
            int val = scanner.nextInt();
            lista2.insertLast(val);
        }
        
        // Prikazi listi
        System.out.println("\n--- VNESENI LISTI ---");
        System.out.print("Lista1: ");
        lista1.printList();
        System.out.print("Lista2: ");
        lista2.printList();
        
        // Kombiniraj listi
        System.out.println("\n--- OBRABOTKA ---");
        SLinkedList<Integer> rezultat = kombinuiListi(lista1, lista2);
        
        // Rezultat
        System.out.println("\n--- REZULTAT ---");
        System.out.print("Nova lista: ");
        rezultat.printList();
        
        scanner.close();
    }
}

