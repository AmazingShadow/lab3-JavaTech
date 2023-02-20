package test;

import dao.ManufactureDAO;
import dao.PhoneDAO;
import database.HibernateUtils;
import org.hibernate.Session;
import pojo.Manufacture;
import pojo.Phone;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static Scanner scanner = new Scanner(System.in);

    public static void inputManufacture() {
        System.out.print("Nhap ten nha san xuat: ");
        String name = scanner.nextLine();
        System.out.print("Nhap dia chi nha san xuat: ");
        String location = scanner.nextLine();
        System.out.print("Nhap so luong nhan vien dang lam viec: ");
        int employee = scanner.nextInt();

        if (ManufactureDAO.getInstance().add(new Manufacture(name, location, employee))) {
            System.out.println("Them nha san xuat thanh cong!");
        }
        System.out.println();
    }

    public static void inputPhone() {
        System.out.print("Nhap ten dien thoai: ");
        String name = scanner.nextLine();
        System.out.print("Nhap gia tien: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nhap mau sac cua dien thoai: ");
        String color = scanner.nextLine();
        System.out.print("Nhap quoc gia san xuat cua dien thoai: ");
        String country = scanner.nextLine();
        System.out.print("Nhap so luong dien thoai hien co: ");
        int quantity = scanner.nextInt();

        PhoneDAO.getInstance().add(new Phone(name, price, color, country, quantity));
        System.out.println("Them dien thoai moi thanh cong");
        System.out.println();
    }

    public static void getManufactureById() {
        System.out.print("Nhap id nha san xuat can tim kiem: ");
        int id = scanner.nextInt();

        Manufacture manufacture = ManufactureDAO.getInstance().get(id);

        if (manufacture != null) {
            System.out.println(manufacture);
        } else {
            System.out.println("Khong ton tai nha san xuat nay");
        }
        System.out.println();
    }

    public static void getPhoneById() {
        System.out.print("Nhap id dien thoai can tim kiem: ");
        int id = scanner.nextInt();

        Phone phone = PhoneDAO.getInstance().get(id);

        if (phone != null) {
            System.out.println(phone);
        } else {
            System.out.println("Khong ton tai dien thoai nay");
        }
        System.out.println();
    }

    public static void getAllManufacture() {
        System.out.println("--------------------Danh sach nha san xuat--------------------");
        List<Manufacture> manufactureList = ManufactureDAO.getInstance().getAll();

        if (manufactureList.size() == 0) {
            System.out.println("Khong co nha san xuat nao");
        } else {
            manufactureList.stream().forEach(manufacture -> System.out.println(manufacture));
        }
        System.out.println();
    }

    public static void getAllPhone() {
        System.out.println("--------------------Danh sach dien thoai--------------------");
        List<Phone> phones = PhoneDAO.getInstance().getAll();

        if (phones.size() == 0) {
            System.out.println("Khong co nha san xuat nao");
        } else {
            phones.stream().forEach(phone-> System.out.println(phone));
        }
        System.out.println();
    }

    public static void removeObjectByID(String name) {
        System.out.print("Nhap id " + name + " muon xoa: ");
        int id = scanner.nextInt();
        boolean check;
        if (name.equalsIgnoreCase("nha san xuat")) {
            check = ManufactureDAO.getInstance().remove(id);
        } else {
            check = PhoneDAO.getInstance().remove(id);
        }

        if (check) {
            System.out.println("Xoa thanh cong");
        } else {
            System.out.println("Khong tim thay " + name);
        }
        System.out.println();
    }

    public static void removeObject(String name) {
        boolean check;
        System.out.print("Nhap id " + name + " muon xoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhap ten " + name + " muon xoa: ");
        String nameObject = scanner.nextLine();

        if (name.equalsIgnoreCase("nha san xuat")) {
            check = ManufactureDAO.getInstance().remove(new Manufacture(id, nameObject));
        } else {
            check = PhoneDAO.getInstance().remove(new Phone(id, nameObject));
        }

        if (check) {
            System.out.println("Xoa " + name + " thanh cong!");
        }
        System.out.println();
    }

    public static void updateManufacture() {
        System.out.println("Luu y: Nhung truong khong muon sua doi vui long nhap dau space doi voi chuoi ky tu, doi voi so" +
                " vui long nhap -1");
        System.out.print("Nhap id nha san xuat muon cap nhat: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhap ten nha san xuat: ");
        String name = scanner.nextLine();
        System.out.print("Nhap dia chi nha san xuat: ");
        String location = scanner.nextLine();
        System.out.print("Nhap nhan vien dang hoat dong o nha san xuat: ");
        int employee = scanner.nextInt();
        boolean check = ManufactureDAO.getInstance().update(new Manufacture(id, name, location, employee));

        if (check) {
            System.out.println("Cap nhat thong tin nha san xuat thanh cong!");
        } else {
            System.out.println("Khong tim thay nha san xuat!");
        }
        System.out.println();
    }

    public static void updatePhone() {
        System.out.println("Luu y: Nhung truong khong muon sua doi vui long nhap dau space doi voi chuoi ky tu, doi voi so" +
                " vui long nhap -1");
        System.out.print("Nhap id dien thoai muon cap nhat thong tin: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhap ten dien thoai: ");
        String name = scanner.nextLine();
        System.out.print("Nhap gia tien: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nhap mau sac cua dien thoai: ");
        String color = scanner.nextLine();
        System.out.print("Nhap quoc gia san xuat cua dien thoai: ");
        String country = scanner.nextLine();
        System.out.print("Nhap so luong dien thoai hien co: ");
        int quantity = scanner.nextInt();

        PhoneDAO.getInstance().update(new Phone(id, name, price, color, country, quantity));
        System.out.println("Cap nhat thong tin thanh cong!");
        System.out.println();
    }

    public static void checkManuEmp() {
        System.out.print("Nhap ten nha san xuat: ");
        String name = scanner.nextLine();
        boolean check = ManufactureDAO.getInstance().checkEmployees(name);

        if (check) {
            System.out.println("Nha san xuat nay co tren 100 employees");
        }
        System.out.println();
    }

    public static void sumEmp() {
        long check = ManufactureDAO.getInstance().sumOfEmployees();

        if (check <= 0) {
            System.out.println("Khong co nha san xuat nao ton tai");
        } else {
            System.out.println("Tong cong co " + check + " nhan vien");
        }
    }

    public static void findLastManufactureUS() {
        Manufacture manufacture = ManufactureDAO.getInstance().findLastManufactureUS();
        if (manufacture != null) {
            System.out.println(manufacture);
        }
        System.out.println();
    }

    public static void findHighestPrice() {
        Phone check = PhoneDAO.getInstance().findHighestPrice();
        if (check != null) {
            System.out.println(check);
        } else {
            System.out.println("Khong co dien thoai nao trong cua hang");
        }
        System.out.println();
    }

    public static void getPhonesSortByCountry() {
        List<Phone> phones = PhoneDAO.getInstance().getPhonesSortByCountry();
        if (phones.size() == 0) {
            System.out.println("Khong co dien thoai nao trong cua hang");
        } else {
            phones.forEach(phone -> System.out.println(phone));
        }
        System.out.println();
    }

    public static void checkPriceOfPhone() {
        System.out.print("Nhap ten dien thoai can tim: ");
        String name = scanner.nextLine();
        System.out.print("Nhap mau sac cua dien thoai: ");
        String color = scanner.nextLine();
        Phone phone = PhoneDAO.getInstance().checkPriceOfPhone(name, color);
        if (phone.getPrice() > 50000000) {
            System.out.println("Dien thoai nay co gia lon hon 50000000VND");
        } else {
            System.out.println("Dien thoai nay khong co gia lon hon 50000000VND");
        }
        System.out.println(phone);
        System.out.println();
    }

    public static void getFirstPhonePink() {
        Phone phone = PhoneDAO.getInstance().getFirstPhonePink();
        if (phone == null) {
            System.out.println("Khong co dien thoai nao co mau pink");
        } else {
            System.out.println("Dien thoai dau tien trong danh sach co mau pink la: \n" + phone);
        }
        System.out.println();
    }

    public static void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----------------------Menu------------------------");
            System.out.println();
            System.out.println("-------------------Nha san xuat-------------------");
            System.out.println("1. Them nha san xuat");
            System.out.println("2. Tim nha san xuat theo id");
            System.out.println("3. Lay ra danh sach cac nha san xuat");
            System.out.println("4. Xoa nha san xuat theo id");
            System.out.println("5. Xoa nha san xuat");
            System.out.println("6. Cap nhat thong tin nha san xuat");
            System.out.println("7. Kiem tra xem nha san xuat co hon 100 nhan vien hay khong");
            System.out.println("8. Tinh tong nhan vien cua cac nha san xuat");
            System.out.println("9. Tim nha san xuat cuoi cung co vi tri US");
            System.out.println("-------------------Dien thoai---------------------");
            System.out.println("10. Them dien thoai");
            System.out.println("11. Tim dien thoai theo id");
            System.out.println("12. Lay ra danh sach cac dien thoai co trong cua hang");
            System.out.println("13. Xoa dien thoai theo id");
            System.out.println("14. Xoa dien thoai");
            System.out.println("15. Cap nhat thong tin dien thoai");
            System.out.println("16. Tim dien thoai co gia tri cao nhat");
            System.out.println("17. Lay ra danh sach dien thoai sap xep theo quoc gia, cung quoc gia thi sap xep theo gia tien");
            System.out.println("18. Kiem tra xem co dien thoai nao gia tri hon 50000000 hay khong?");
            System.out.println("19. Lay ra chiec dien thoai co mau hong dau tien trong danh sach");
            System.out.println("20. Thoat");
            System.out.println();
            System.out.print("Moi ban chon chuc nang: ");
            int option = scanner.nextInt();

            switch(option) {
                case 1 -> inputManufacture();
                case 2 -> getManufactureById();
                case 3 -> getAllManufacture();
                case 4 -> removeObjectByID("nha san xuat");
                case 5 -> removeObject("nha san xuat");
                case 6 -> updateManufacture();
                case 7 -> checkManuEmp();
                case 8 -> sumEmp();
                case 9 -> findLastManufactureUS();
                case 10 -> inputPhone();
                case 11 -> getPhoneById();
                case 12 -> getAllPhone();
                case 13 -> removeObjectByID("dien thoai");
                case 14 -> removeObject("dien thoai");
                case 15 -> updatePhone();
                case 16 -> findHighestPrice();
                case 17 -> getPhonesSortByCountry();
                case 18 -> checkPriceOfPhone();
                case 19 -> getFirstPhonePink();
                case 20 -> {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        show();
    }
}
