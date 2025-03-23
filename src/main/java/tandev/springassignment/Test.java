package tandev.springassignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tandev.springassignment.model.Pet;
import tandev.springassignment.service.IOService;

import java.util.Arrays;
import java.util.List;

/**
 * Class Test để demo chức năng của ứng dụng
 * Implements CommandLineRunner để chạy code khi ứng dụng Spring Boot khởi động
 * Được đánh dấu @Component để Spring quản lý như một bean
 */
@Component
public class Test implements CommandLineRunner {

    // Inject IOService bean bằng annotation @Autowired
    @Autowired
    private IOService ioService;

    /**
     * Phương thức run sẽ được Spring Boot gọi khi ứng dụng khởi động
     * @param args Tham số dòng lệnh
     */
    @Override
    public void run(String... args) {
        // Tạo danh sách thú cưng mẫu
        List<Pet> pets = Arrays.asList(
            new Pet("Mowzer", "cat", 3),    // Con mèo Mowzer, 3 tuổi
            new Pet("Rex", "dog", 5),        // Con chó Rex, 5 tuổi
            new Pet("Whiskers", "cat", 2)    // Con mèo Whiskers, 2 tuổi
        );

        // Lưu danh sách thú cưng xuống file
        System.out.println("Saving pets to file...");
        ioService.savePets(pets);

        // Đọc danh sách thú cưng từ file
        System.out.println("\nReading pets from file...");
        List<Pet> loadedPets = ioService.getPets();

        // Cho mỗi thú cưng phát ra âm thanh
        System.out.println("\nMaking pets noise...");
        loadedPets.forEach(Pet::makeNoise);
    }
} 