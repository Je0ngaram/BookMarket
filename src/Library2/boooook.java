package Library2;

import java.util.*;

public class boooook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bookstore bookstore = new Bookstore();
        
        while (true) {
            System.out.println("온라인 서점에 오신 것을 환영합니다!");
            System.out.println("1. 관리자로 로그인");
            System.out.println("2. 손님으로 계속하기");
            System.out.println("3. 종료");
            System.out.print("원하시는 옵션을 선택하세요: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    Admin admin = new Admin(bookstore);
                    admin.adminMenu();
                    break;
                case 2:
                    Customer customer = new Customer(bookstore);
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

class Bookstore {
    List<Book> books = new ArrayList<>();
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public void removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
        } else {
            System.out.println("올바르지 않은 인덱스입니다.");
        }
    }
    
    public List<Book> getBooks() {
        return books;
    }
}

class Admin {
    private Bookstore bookstore;
    private Scanner scanner = new Scanner(System.in);
    
    public Admin(Bookstore bookstore) {
        this.bookstore = bookstore;
    }
    
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
                    System.out.println("모드로 선택으로 돌아갑니다.");
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
        scanner.nextLine(); // 버퍼 비우기
        Book book = new Book(title, author, price);
        bookstore.addBook(book);
        System.out.println("책이 추가되었습니다.");
    }
    
    public void removeBook() {
        System.out.println("삭제할 책의 인덱스를 입력하세요:");
        displayBooks();
        int index = scanner.nextInt() + 1;
        scanner.nextLine(); // 버퍼 비우기
        bookstore.removeBook(index);
        System.out.println("책이 삭제되었습니다.");
    }
    
    public void displayBooks() {
        List<Book> books = bookstore.getBooks();
        System.out.println("\n도서 목록:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
}

class Customer {
    private Bookstore bookstore;
    private List<Book> cart = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    
    public Customer(Bookstore bookstore) {
        this.bookstore = bookstore;
    }
    
    public void customerMenu() {
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
                    displayBooks();
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
        System.out.println("장바구니에 추가할 책의 인덱스를 입력하세요:");
        displayBooks();
        int index = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기
        List<Book> books = bookstore.getBooks();
        if (index >= 0 && index < books.size()) {
            cart.add(books.get(index));
            System.out.println("장바구니에 책이 추가되었습니다.");
        } else {
            System.out.println("올바르지 않은 인덱스입니다.");
        }
    }
    
    public void clearCart() {
        cart.clear();
        System.out.println("장바구니가 비워졌습니다.");
    }
    
    public void displayBooks() {
        List<Book> books = bookstore.getBooks();
        System.out.println("\n도서 목록:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
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

