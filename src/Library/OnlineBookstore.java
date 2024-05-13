package Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineBookstore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("온라인 서점에 오신 것을 환영합니다!");
            System.out.println("1. 관리자로 로그인");
            System.out.println("2. 손님으로 계속하기");
            System.out.println("3. 종료");
            System.out.print("원하시는 옵션을 선택하세요: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    Admin admin = new Admin();
                    admin.adminMenu();
                    break;
                case 2:
                    Customer customer = new Customer();
                    customer.customerMenu();
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }
}

// Admin 클래스, 손님 모드에서 사용 불가능
class Admin {
    List<Book> books = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    
    public void adminMenu() {
        while (true) {
            System.out.println("\n관리자 메뉴:");
            System.out.println("1. 도서 추가");
            System.out.println("2. 도서 삭제");
            System.out.println("3. 도서 목록 조회");
            System.out.println("4. 돌아가기");
            System.out.print("원하는 작업을 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    displayBooks();
                    break;
                case 4:
                    System.out.println("손님 모드로 돌아갑니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }
    
    public void addBook() {
        System.out.print("추가할 책의 제목을 입력하세요: ");
        String title = scanner.nextLine();
        System.out.print("작가를 입력하세요: ");
        String author = scanner.nextLine();
        System.out.print("가격을 입력하세요: ");
        double price = scanner.nextDouble();
        books.add(new Book(title, author, price));
        System.out.println("책이 추가되었습니다.");
    }
    
    public void removeBook() {
        System.out.println("삭제할 책의 인덱스를 입력하세요:");
        displayBooks();
        int index = scanner.nextInt();
        if (index >= 0 && index < books.size()) {
            books.remove(index);
            System.out.println("책이 삭제되었습니다.");
        } else {
            System.out.println("올바르지 않은 인덱스입니다.");
        }
    }
    
    public void displayBooks() {
        System.out.println("\n도서 목록:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
}

// Customer 클래스, 관리자 모드에서 사용 불가능
class Customer {
    List<Book> cart = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    
    public void customerMenu() {
        Admin admin = new Admin(); // 손님도 도서 목록을 조회할 수 있도록
        admin.displayBooks();
        
        while (true) {
            System.out.println("\n손님 메뉴:");
            System.out.println("1. 장바구니에 책 추가");
            System.out.println("2. 장바구니 비우기");
            System.out.println("3. 도서 목록 조회");
            System.out.println("4. 돌아가기");
            System.out.print("원하는 작업을 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기
            
            switch (choice) {
                case 1:
                    addToCart();
                    break;
                case 2:
                    clearCart();
                    break;
                case 3:
                    admin.displayBooks();
                    break;
                case 4:
                    System.out.println("관리자 모드로 돌아갑니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }
    
    public void addToCart() {
        Admin admin = new Admin(); // 손님도 도서 목록을 조회할 수 있도록
        admin.displayBooks();
        
        System.out.print("장바구니에 추가할 책의 인덱스를 입력하세요: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < admin.books.size()) {
            cart.add(admin.books.get(index));
            System.out.println("장바구니에 책이 추가되었습니다.");
        } else {
            System.out.println("올바르지 않은 인덱스입니다.");
        }
    }
    
    public void clearCart() {
        cart.clear();
        System.out.println("장바구니가 비워졌습니다.");
    }
}

class Book {
    String title;
    String author;
    double price;
    
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "책 제목: " + title + ", 작가: " + author + ", 가격: " + price + "원";
    }
}
