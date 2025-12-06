// Exemplo mais complexo de MiniJava
// Testa herança, arrays e várias construções da linguagem

class BubbleSort {
    public static void main(String[] args) {
        System.out.println(new BubbleSortImpl().Sort(10));
    }
}

class BubbleSortImpl {
    int[] number;
    int size;

    public int Sort(int sz) {
        int aux01;
        int aux02;
        int aux03;
        int i;
        int j;
        int t;
        
        size = sz;
        number = new int[sz];
        
        i = 0;
        while (i < size) {
            number[i] = size - i;
            i = i + 1;
        }
        
        i = 0;
        while (i < size - 1) {
            j = i + 1;
            while (j < size) {
                if (number[j] < number[i]) {
                    t = number[i];
                    number[i] = number[j];
                    number[j] = t;
                } else {
                    aux03 = 0;
                }
                j = j + 1;
            }
            i = i + 1;
        }
        
        return 0;
    }
}

class Animal {
    int idade;
    
    public int getIdade() {
        return idade;
    }
    
    public int setIdade(int i) {
        idade = i;
        return 0;
    }
}

class Cachorro extends Animal {
    boolean latindo;
    
    public int latir() {
        latindo = true;
        System.out.println(1);
        return 0;
    }
    
    public boolean estaLatindo() {
        return latindo;
    }
}
