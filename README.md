### 1. Mục tiêu của chương trình:
- **Mục tiêu chính**: Thực hành các khái niệm cơ bản của lập trình Java và Spring Framework:
  1. OOP (Object-Oriented Programming)
  2. File I/O (Input/Output)
  3. Spring Core (IoC - Inversion of Control và DI - Dependency Injection)

### 2. Cấu trúc chương trình:
1. **Package `model`**:
   ```java
   public class Pet implements Serializable {
       private String name;    // Tên thú cưng
       private String type;    // Loại (cat, dog,...)
       private int age;        // Tuổi
   }
   ```
   - Đây là class mô tả thú cưng với các thuộc tính cơ bản
   - Có phương thức `makeNoise()` để mô phỏng tiếng kêu của thú cưng

2. **Package `service`**:
   ```java
   @Service
   public class IOService {
       public void savePets(List<Pet> pets) { ... }
       public List<Pet> getPets() { ... }
   }
   ```
   - Class này xử lý việc đọc/ghi file
   - Được đánh dấu `@Service` để Spring quản lý

3. **Class `Test`**:
   ```java
   @Component
   public class Test implements CommandLineRunner {
       @Autowired
       private IOService ioService;
       
       @Override
       public void run(String... args) { ... }
   }
   ```
   - Class demo chức năng của chương trình
   - Sử dụng Spring DI để inject `IOService`

### 3. Cách hoạt động:
1. **Khởi động**:
   - Spring Boot khởi động ứng dụng
   - Quét và tạo các beans (`@Service`, `@Component`)
   - Inject `IOService` vào `Test` class

2. **Luồng xử lý**:
   ```
   [Tạo danh sách Pet] -> [Lưu xuống file] -> [Đọc từ file] -> [Hiển thị tiếng kêu]
   ```

3. **Chi tiết từng bước**:
   - **Bước 1**: Tạo danh sách thú cưng mẫu
     ```java
     List<Pet> pets = Arrays.asList(
         new Pet("Mowzer", "cat", 3),
         new Pet("Rex", "dog", 5),
         new Pet("Whiskers", "cat", 2)
     );
     ```

   - **Bước 2**: Lưu xuống file
     ```java
     // Format: name,type,age
     Mowzer,cat,3
     Rex,dog,5
     Whiskers,cat,2
     ```

   - **Bước 3**: Đọc từ file
     - Đọc từng dòng
     - Tách thông tin bằng dấu phẩy
     - Tạo lại đối tượng Pet

   - **Bước 4**: Hiển thị tiếng kêu
     ```
     Mowzer cat says meow
     Rex dog says woof
     Whiskers cat says meow
     ```

### 4. Kiến thức áp dụng:
1. **OOP**:
   - Encapsulation (đóng gói): private fields, getters/setters
   - Class và Objects
   - Inheritance (kế thừa): implements interfaces

2. **File I/O**:
   - BufferedWriter để ghi file
   - BufferedReader để đọc file
   - Try-with-resources để quản lý tài nguyên

3. **Spring Framework**:
   - Dependency Injection (`@Autowired`)
   - Component scanning (`@Service`, `@Component`)
   - Application lifecycle (`CommandLineRunner`)

### 5. Kết quả đạt được:
1. Hiểu và áp dụng được OOP
2. Thực hành được File I/O
3. Nắm được cách Spring quản lý dependencies
4. Tạo được ứng dụng hoàn chỉnh với đầy đủ chức năng

**Các annotation tạo Bean phổ biến:**

1. `@Component`: Bean chung chung
2. `@Service`: Bean xử lý business logic
3. `@Repository`: Bean tương tác với database
4. `@Controller`/`@RestController`: Bean xử lý web requests

### 2. @Autowired là gì?

- `@Autowired` là cách yêu cầu Spring inject (tiêm) một Bean vào nơi cần dùng
- Spring sẽ tìm Bean phù hợp và tự động inject vào