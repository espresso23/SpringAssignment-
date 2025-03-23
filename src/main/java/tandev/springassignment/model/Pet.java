package tandev.springassignment.model;

import java.io.Serializable;

/**
 * Class Pet đại diện cho một thú cưng
 * Implements Serializable để có thể lưu object xuống file
 */
public class Pet implements Serializable {
    // Các thuộc tính của thú cưng
    private String name;    // Tên của thú cưng
    private String type;    // Loại thú cưng (cat, dog, etc.)
    private int age;       // Tuổi của thú cưng

    /**
     * Constructor với đầy đủ tham số
     * @param name Tên thú cưng
     * @param type Loại thú cưng
     * @param age Tuổi thú cưng
     */
    public Pet(String name, String type, int age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    /**
     * Phương thức để thú cưng phát ra âm thanh
     * In ra màn hình âm thanh tương ứng với loại thú cưng
     * Ví dụ: "Mowzer cat says meow"
     */
    public void makeNoise() {
        String sound = "";
        switch (type.toLowerCase()) {
            case "cat":
                sound = "meow";
                break;
            case "dog":
                sound = "woof";
                break;
            default:
                sound = "unknown sound";
        }
        System.out.println(name + " " + type + " says " + sound);
    }

    /**
     * Override phương thức toString để hiển thị thông tin thú cưng
     * @return Chuỗi thông tin theo định dạng: "Pet [type=type, name=name, age=age]"
     */
    @Override
    public String toString() {
        return "Pet [type=" + type + ", name=" + name + ", age=" + age + "]";
    }

    // Các phương thức getter và setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
} 