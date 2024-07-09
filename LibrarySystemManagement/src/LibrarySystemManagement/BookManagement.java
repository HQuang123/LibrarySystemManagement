/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibrarySystemManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Home
 */
public class BookManagement  {
    Scanner input = new Scanner(System.in);
    private ArrayList<Book> books;
    private static int count;
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendOptional(DateTimeFormatter.ofPattern("d-M-yyyy")).appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toFormatter();

    public BookManagement() {
        books  = new ArrayList<>();
    }
  

    public void addBook(Book book) {
        if(books.isEmpty()){
            books.add(book);
            return;
        }
        for (Book book1 : books) {
            if(book1.getISBN().equals(book.getISBN())){
                System.out.println("Book of this SerialNo Already Exists.");
                return;
            }
        }
        
        books.add(book);
        
    }

    public void updateBook() {
        System.out.println("Enter ISBN to Update");
        input.nextLine();
        String ISBN = input.nextLine();
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new author: ");
                String title = scanner.nextLine();
                System.out.print("Enter new author: ");
                String newAuthor = scanner.nextLine();
                System.out.print("Enter new genre: ");
                String newGenre = scanner.nextLine();
                book.setTitle(title);
                book.setAuthor(newAuthor);
                book.setGenre(newGenre);
                System.out.println("Book updated successfully.");
                return;
            }
        }
        System.out.println("Book with ISBN " + ISBN + " not found.");
    }

    public void deleteBook() {
        System.out.println("Enter ISBN to Delete");
        String ISBN = input.nextLine();
        for (Book book : books) {
            if(book.getISBN().equals(ISBN)){
                books.remove(book);
                System.out.println("Successfully Deleted");
                return;
            }
        }
        System.out.println("No Book Found");
    }

    public void searchBookbySNo() {
        int flag = 0;
        System.out.println("\t\t\t\tSEARCH BY SERIAL NUMBER\n");
        System.out.println("Enter Serial No of Book:");
        String ISBN = input.nextLine();
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                System.out.println(book.getISBN() + "\t\t"
                    + book.getTitle() + "\t\t"
                    + book.getAuthor() + "\t\t"
                    + "Status:" + book.getIsTaken() + "\t\t"
                    );
                flag++;
                return;
            }
        }
        if(flag == 0){
        System.out.println("Book with ISBN " + ISBN + " not found.");}
    }
        

    public void printBooks() {
        int choice;
        System.out.println("Please Pick The Order to Print");
        System.out.println("1. Order by ISBN");
        System.out.println("2. Order by Title");
        System.out.println("3. Order by Author");
        System.out.println("4. Order by Genre");
        choice = input.nextInt();
        switch(choice){
            case 1: Collections.sort(books,new SortByISBN());
                    break;
            case 2: Collections.sort(books,new SortByTitle());
                    break;
            case 3: Collections.sort(books,new SortByAuthor());
                    break;
            case 4: Collections.sort(books, new SortByGenre());
                    break;
        }
        System.out.println("\t\t\t\t\tSHOWING ALL BOOKS\n");
        System.out.printf("%-5s %-10s %-10s %-20s %-20s %-30s%n","ISBN", "isRented", "RentDate", "Genre", "Author", "Title");
        for (Book book : books) {
            System.out.printf("%-5s %-10s %-10s %-20s %-20s %-30s%n",book.getISBN(),book.getIsTaken(),book.getRentDate(),book.getGenre(),book.getAuthor(),book.getTitle());
        }
    }
    
    
    public void dispMenu()
    {
 
        // Displaying menu
        System.out.println(
            "----------------------------------------------------------------------------------------------------------");
        System.out.println("Press 1 to Add new Book.");
        System.out.println("Press 2 to Search a Book.");
        System.out.println("Press 3 to Show All Books.");
        System.out.println("Press 4 to Register Student.");
        System.out.println(
            "Press 5 to Show All Registered Students.");
        System.out.println("Press 6 to Check Out Book. ");
        System.out.println("Press 7 to Check In Book");
        System.out.println("Press 8 to Delete Student");
        System.out.println("Press 9 to Update Student");
        System.out.println("Press 10 to Delete Book");
        System.out.println("Press 11 to Update Book");
        System.out.println("Press 12 to Exit Application.");
        System.out.println(
            "-------------------------------------------------------------------------------------------------------");
    }
    
    public int isAvailable(String ISBN){
        for (Book book : books) {
            if(book.getISBN().equals(ISBN)){
                if(book.getIsTaken()==false){
                    System.out.println("Book is Available");
                    return books.indexOf(book);
                }
                System.out.println("Book is Unavailable");
                return -1;
            }
        }
        System.out.println("No Book of Serial Number");
        return -1;
    }
    //remove the book from the library
    public Book checkOutBook(){
        System.out.println("Remember: Each Student Can Only Borrow Up To 3 Books");
        System.out.println("Enter Serial No of Book to be Checked Out.");
        String ISBN = input.nextLine();
        int bookIndex = isAvailable(ISBN);
        if(bookIndex!=-1){
            books.get(bookIndex).setTaken(true);
            System.out.println("Enter the date to rent(dd-mm-yyyy):");
            books.get(bookIndex).setRentDate(LocalDate.parse(input.nextLine(), formatter));
            System.out.println("Enter the due date(dd-mm-yyyy):");
            books.get(bookIndex).setDueDate(LocalDate.parse(input.nextLine(), formatter));
            return books.get(bookIndex);
        }
        return null;
    }
    //add the book to the library
    public void checkInBook(Book b){
        for (Book book : books) {
            if(b.equals(book)){
                book.setTaken(false);
                System.out.println("Enter the return date(dd-mm-yyyy)");
                book.setReturnDate(LocalDate.parse(input.nextLine(), formatter));
                return;
            }
        }
    }
    
    
    
}
