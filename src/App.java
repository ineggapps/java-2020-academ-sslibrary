import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LibraryService ls = new LibraryServiceImpl();
		try {
			ls.entrance();
		} finally {
			System.out.println("���α׷��� �����մϴ�.");
			sc.close();
		}
	}
}
