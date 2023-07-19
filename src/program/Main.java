package program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Scanner tc = new Scanner(System.in);
		File file = new File(tc.next());
//		Stream<Product> p = null;
		List<Product> p = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = br.readLine();

			while (line != null) {
				String vet[] = line.split(",");
				String name = vet[0];

				Double price = Double.parseDouble(vet[1]);
				p.add(new Product(name, price));
//				p = Stream.of(new Product(name, price));
				line = br.readLine();

			}
			
			Comparator<String> comp = new Comparator<String>() {
				
				@Override
				public int compare(String o1, String o2) {

					return o1.toUpperCase().compareTo(o2.toUpperCase());
					
				}
			};

			double a1 = (p.stream().map(x -> x.getPrice()).reduce(0.0, (a, b) -> a + b)) / p.size();
			System.out.println("Average price: " + String.format("%.2f", a1));
			List<String> pro = p.stream().filter(x -> x.getPrice() < a1).map(x -> x.getName())
					.sorted(comp.reversed()).toList();
			
			
			pro.forEach(System.out :: println);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
