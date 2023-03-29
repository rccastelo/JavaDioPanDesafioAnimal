// Para ler e escrever dados em Java, aqui na DIO padronizamos da seguinte forma:
// - new Scanner(System.in): cria um leitor de Entradas, com métodos úteis com prefixo "next";
// - System.out.println:.imprime um texto de Saída (Output) e pulando uma linha.

import java.util.*;

public class Main {
//    static List<Animal> animais;

    public static void main(String[] args) {
        Repositorio repositorio = new Repositorio();

        Scanner sc = new Scanner(System.in);
        String AN1,AN2,AN3;

        AN1 = sc.nextLine();
        AN2 = sc.nextLine();
        AN3 = sc.nextLine();

        //List<Animal> lista = animais.stream().filter(a -> a.getCaracteristicas().contains(AN1)).toList();
        List<Animal> lista = repositorio.animais.stream().filter(a -> a.getCaracteristicas().contains(AN1)).toList();
        lista = lista.stream().filter(a -> a.getCaracteristicas().contains(AN2)).toList();
        lista = lista.stream().filter(a -> a.getCaracteristicas().contains(AN3)).toList();

        //List<Animal> lista = verificar(repositorio.animais, AN1);
        //lista = verificar(lista, AN2);
        //lista = verificar(lista, AN3);

        if (lista != null && !lista.isEmpty()) {
            for (Animal a : lista) {
                System.out.println(a.getNome());
            }
        }

        sc.close();
    }
//
//    private static List<Animal> verificar(List<Animal> animais, String caracteristica) {
//        if (animais != null && !animais.isEmpty()) {
//            List<Animal> nova = new ArrayList<>();
//
//            for (Animal a : animais) {
//                if (a.getCaracteristicas().contains(caracteristica)) {
//                    nova.add(a);
//                }
//            }
//
//            return nova;
//        } else {
//            return null;
//        }
//    }
}

class Animal {
    private final String nome;
    private final List<String> caracteristicas;

    public Animal(String nome, List<String> caracteristicas) {
        this.nome = nome;
        List<String> caracsFiltradas = new ArrayList<>();

        if (caracteristicas != null && !caracteristicas.isEmpty()) {
            caracsFiltradas = caracteristicas.stream().distinct().sorted().toList();
        }

        this.caracteristicas = caracsFiltradas;
        //this.caracteristicas = caracteristicas;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        List<String> lista = caracteristicas.stream().sorted().toList();
        List<String> listaObj = animal.caracteristicas.stream().sorted().toList();
        return Objects.equals(nome, animal.nome) && Objects.equals(lista, listaObj);
        //return Objects.equals(nome, animal.nome) && Objects.equals(caracteristicas, animal.caracteristicas);
    }

    @Override
    public int hashCode() {
        List<String> lista = caracteristicas.stream().sorted().toList();
        return Objects.hash(nome, lista);
        //return Objects.hash(nome, caracteristicas);
    }

    @Override
    public String toString() {
        List<String> lista = caracteristicas.stream().sorted().toList();
        return "Animal : nome [" + nome + "] caracteristicas [" + lista + "]";
        //return "Animal [nome=" + nome + "] caracteristicas [" + caracteristicas + "]";
    }
}

class Repositorio {
    public List<Animal> animais;

    public Repositorio() {
        animais = new ArrayList<>();

        carregarDados();
    }

    public void incluir(String nome, List<String> caracteristicas) {
        Animal animal = new Animal(nome, caracteristicas);

        if (!animais.contains(animal)) {
            animais.add(animal);
        }
    }

    private void carregarDados() {
        incluir("aguia", Arrays.asList("vertebrado", "ave", "carnivoro"));
        incluir("pomba", Arrays.asList("vertebrado", "ave", "onivoro"));
        incluir("homem", Arrays.asList("vertebrado", "mamifero", "onivoro"));
        incluir("vaca", Arrays.asList("vertebrado", "mamifero", "herbivoro"));
        incluir("pulga", Arrays.asList("invertebrado", "inseto", "hematofago"));
        incluir("lagarta", Arrays.asList("invertebrado", "inseto", "herbivoro"));
        incluir("sanguessuga", Arrays.asList("invertebrado", "anelideo", "hematofago"));
        incluir("minhoca", Arrays.asList("invertebrado", "anelideo", "onivoro"));
    }
}
