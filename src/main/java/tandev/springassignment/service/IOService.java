package tandev.springassignment.service;

import org.springframework.stereotype.Service;
import tandev.springassignment.model.Pet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class để xử lý các thao tác Input/Output với file
 * Được đánh dấu @Service để Spring quản lý như một bean
 */
@Service
public class IOService {
    // Đường dẫn tới file lưu trữ danh sách Pet
    private static final String FILE_PATH = "pets.txt";

    /**
     * Lưu danh sách Pet xuống file ở dạng text
     * Mỗi Pet được lưu trên một dòng với format: name,type,age
     * @param pets Danh sách Pet cần lưu
     */
    public void savePets(List<Pet> pets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Pet pet : pets) {
                writer.write(String.format("%s,%s,%d%n", 
                    pet.getName(), pet.getType(), pet.getAge()));
            }
            System.out.println("Pets saved successfully to " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving pets: " + e.getMessage());
        }
    }

    /**
     * Đọc danh sách Pet từ file text
     * Mỗi dòng chứa thông tin một Pet với format: name,type,age
     * @return Danh sách Pet đã đọc được
     */
    public List<Pet> getPets() {
        List<Pet> pets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    pets.add(new Pet(
                        parts[0],                    // name
                        parts[1],                    // type
                        Integer.parseInt(parts[2])    // age
                    ));
                }
            }
            System.out.println("Pets loaded successfully from " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error loading pets: " + e.getMessage());
        }
        return pets;
    }
} 