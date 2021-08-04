package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student{
    int roll;
    String Name;
    int Book_Issued = 0;

    Student(int roll, String Name, int Book_Issued){
        this.roll = roll;
        this.Name = Name;
        this.Book_Issued = Book_Issued;
    }
}

class Book{
    int id;
    String Book_Name;
    String Publications;
    int quantity;

     Book(int id, String Book_Name, String Publications, int quantity) {
        this.id = id;
        this.Book_Name = Book_Name;
        this.Publications = Publications;
        this.quantity = quantity;
    }
}
public class Main {
    static Scanner sc=new Scanner(System.in);
    static List<Book> list = new ArrayList<>(); //This is our library
    static List<Student> stu = new ArrayList<>(); // if any student recieved book, that will get captured here

    static int add(){
        System.out.println("Enter the Book Id:");
        int id=sc.nextInt();
        sc.nextLine(); // bug from Java

        System.out.println("Enter the Book Name: ");
        String Name=sc.nextLine();

        System.out.println("Enter the Publications Name: ");
        String Publications = sc.nextLine();

        System.out.println("Enter the quantity of the books: ");
        int quantity= sc.nextInt();

        if(search(id)==1){
            System.out.println("Enter the quantity of this Book");
            int q = sc.nextInt();
            for(Book book: list){
                if(book.id == id){
                    book.quantity = book.quantity + q;
                    return 0;
                }
            }
            return 0;
        }else {

            Book obj1 = new Book(id, Name, Publications, quantity);
            list.add(obj1);
        }
        return  0;

    }
    static int delete(int id){
        if(search(id)==1){
            for(Book book: list){
                if(book.id == id){
                    list.remove(book);
                    return 1;
                }
            }
        }
        return 0;
    }

    static int Lend(int id){
        if(search(id)==1){
            for(Book book: list){
                if(book.id == id && book.quantity>0){
                    book.quantity = book.quantity-1;
                    return 1;
                }
            }
        }
        return 0;
    }

    static int search(int id){
        int size = list.size();

        for(Book book : list){
            if(book.id == id) {
                System.out.println("Book Id: " + book.id);
                System.out.println("Book Name: " + book.Book_Name);
                System.out.println("Book Publication Name: " + book.Publications);
                System.out.println("Book Quantity: " + book.quantity);
                return 1;
            }
        }
        return 0;
    }

    static void index(){
        System.out.println("\n\n ***---------Welcome to Library Management System---------***");

        //Operation options
        System.out.println("1, Book Search");
        System.out.println("2, Add books");
        System.out.println("3, Delete book");
        System.out.println("4. Book loan");
        System.out.println("5. Book return");
        System.out.println("6, Exit");
        System.out.println("Please enter options (1-6)");

        //Create object
        Scanner input = new Scanner(System.in);

        //Keyboard input operation options
        int test = input.nextInt();

        switch (test) {
            case 1:
                System.out.println("Search");
                System.out.println("Enter the ID of the Book: ");
                int id = input.nextInt();
                int test2 = search(id);
                if (test2 == 0) {
                    System.out.println("No such Book Found");
                }
                break;
            case 2:
                System.out.println("Add");
                add();
                 break;
            case 3:
                System.out.println("Delete");
                System.out.println("Enter the Book Id: ");
                int bid=input.nextInt();
                if (delete(bid)==1){
                    System.out.println("Removed Successfully ");
                }
                else {
                    System.out.println("Book does not exist. ");
                }

                break;
            case 4:
                System.out.println("Lend");
                System.out.println("Enter your Roll Number: ");
                int Sroll = input.nextInt();

                for(Student st: stu){
                    if(st.roll == Sroll){
                        System.out.println("You can not issue new book, please return the existing book to library");
                        index();
                    }
                }

                System.out.println("Enter your name: ");
                input.nextLine();
                String Sname = input.nextLine();

                System.out.println("Enter the book ID, you want to issue :");
                int Bissue = input.nextInt();

                Student obj=new Student(Sroll,Sname,Bissue);
                stu.add(obj);

                if(Lend(Bissue)==1){
                    System.out.println("\n Book Issued Successfully");
                    System.out.println("\n Now the current Status of the particular Book left in library is :");
                    search(Bissue);
                }else{
                    System.out.println("No Book Found in Library");
                    stu.remove(obj);
                }

                break;
            case 5:
                System.out.println("Return");
                System.out.println("Enter your Roll number: ");
                int Broll=input.nextInt();
                int count=0;
                for (Student st:stu){
                    if(st.roll==Broll){
                        for (Book book: list){
                            if(book.id==st.Book_Issued){
                                book.quantity=book.quantity+1;
                                stu.remove(st);
                                System.out.println("Book Returned Successfully");
                                count=1;
                                break;
                            }
                        }
                        if(count==1)
                            break;
                    }
                }
                if (count==0){
                        System.out.println("Access denied");
                }
                break;
            case 6:
                System.exit(0);//Exit the program
            default:
                System.out.println("Please enter the correct option!");//Prompt when the option is entered incorrectly
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);   // This is used for taking the input
        System.out.println("***---------Welcome to Library Management System Login---------***");

        System.out.println("Enter the Account Number to Log In: ");
        int account=sc.nextInt();
        System.out.println("Enter the Password: ");
        int password=sc.nextInt();

        if(account==123456 && password == 123456){
            while(true) {
                index();
            }
        }
        else{
            System.out.println("Sorry, you can't log in with wrong credentials");
        }
    }
}
