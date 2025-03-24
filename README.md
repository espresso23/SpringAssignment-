# AIPO User Management System

## Overview
This is a user management system built with Spring Boot, implementing the AIPO (Open Source Groupware) user management functionality. The system allows for user creation, searching, and management with a modern web interface.

## Prerequisites
- Java 17
- Maven 3+
- SQL Server
- IDE (IntelliJ IDEA or Eclipse recommended)

## Database Setup
1. Create a new database named `Long101`
2. Execute the following SQL scripts:

```sql
-- Create Company table
CREATE TABLE eip_m_company (
    company_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    company_name NVARCHAR(255) NOT NULL,
    company_name_kana NVARCHAR(255),
    zipcode VARCHAR(20),
    address NVARCHAR(255),
    telephone VARCHAR(50),
    fax_number VARCHAR(50),
    url VARCHAR(255),
    created_user_id BIGINT,
    modified_user_id BIGINT,
    created DATETIME,
    modified DATETIME
);

-- Create Position table
CREATE TABLE eip_m_position (
    position_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    position_name NVARCHAR(255) NOT NULL,
    created_user_id BIGINT,
    modified_user_id BIGINT,
    created DATETIME,
    modified DATETIME
);

-- Create Group table
CREATE TABLE turbine_group (
    group_name VARCHAR(255) PRIMARY KEY,
    owner_id BIGINT,
    group_alias_name NVARCHAR(255),
    created_user_id BIGINT,
    modified_user_id BIGINT,
    created DATETIME,
    modified DATETIME
);

-- Create Post table
CREATE TABLE eip_m_post (
    post_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    post_name NVARCHAR(255) NOT NULL,
    company_id BIGINT,
    group_name VARCHAR(255),
    created_user_id BIGINT,
    modified_user_id BIGINT,
    created DATETIME,
    modified DATETIME,
    FOREIGN KEY (company_id) REFERENCES eip_m_company(company_id),
    FOREIGN KEY (group_name) REFERENCES turbine_group(group_name)
);

-- Create User table
CREATE TABLE turbine_user (
    user_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    login_name VARCHAR(255) NOT NULL UNIQUE,
    password_value VARCHAR(255) NOT NULL,
    first_name NVARCHAR(255),
    last_name NVARCHAR(255),
    first_name_kana NVARCHAR(255),
    last_name_kana NVARCHAR(255),
    email VARCHAR(255),
    out_telephone VARCHAR(50),
    in_telephone VARCHAR(50),
    cellular_phone VARCHAR(50),
    cellular_mail VARCHAR(255),
    photo VARCHAR(255),
    created_user_id BIGINT,
    modified_user_id BIGINT,
    created DATETIME,
    modified DATETIME,
    last_login DATETIME,
    disabled CHAR(1),
    password_changed DATETIME,
    is_admin BIT,
    position_id BIGINT,
    company_id BIGINT,
    FOREIGN KEY (position_id) REFERENCES eip_m_position(position_id),
    FOREIGN KEY (company_id) REFERENCES eip_m_company(company_id)
);
```

## Configuration
1. Update database connection in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:sqlserver://[YOUR_SERVER]\\SQLEXPRESS;databaseName=Long101;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=123
```

## Building and Running
1. Clone the repository:
```bash
git clone [repository_url]
```

2. Navigate to project directory:
```bash
cd SpringAssignment
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

5. Access the application:
- URL: http://localhost:8080/aipo/users

## Features
- User Management
  - Create new users
  - Search users
  - Enable/Disable users
  - Delete users
- Modern UI with responsive design
- Form validation
- Japanese language support (Kana fields)
- File upload for user photos

## Technology Stack
- Spring Boot 2.7.0
- Spring MVC
- Spring Data JPA
- Spring Security
- JSP & JSTL
- Bootstrap 5
- SQL Server
- Maven

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── tandev/
│   │       └── springassignment/
│   │           ├── controller/
│   │           ├── dto/
│   │           ├── entity/
│   │           ├── repository/
│   │           └── service/
│   ├── resources/
│   │   ├── static/
│   │   │   └── css/
│   │   └── application.properties
│   └── webapp/
│       └── WEB-INF/
│           └── views/
└── test/
    └── java/
```

## Contributing
Please read CONTRIBUTING.md for details on our code of conduct, and the process for submitting pull requests.

## License
This project is licensed under the MIT License - see the LICENSE.md file for details

Để nhập dữ liệu vào form thêm người dùng mà không gặp lỗi, bạn cần đảm bảo rằng các trường bắt buộc được điền đúng cách và tuân thủ các quy tắc xác thực. Dưới đây là một ví dụ về dữ liệu hợp lệ mà bạn có thể nhập vào form:

Login Name: Tên đăng nhập phải là duy nhất và không được trống. Ví dụ: john_doe.

Password: Mật khẩu phải được nhập và xác nhận lại. Ví dụ: password123.

Confirm Password: Phải giống với mật khẩu đã nhập. Ví dụ: password123.

First Name: Tên không được trống. Ví dụ: John.

Last Name: Họ không được trống. Ví dụ: Doe.

First Name (Kana): Tên bằng kana (không bắt buộc). Ví dụ: ジョン.

Last Name (Kana): Họ bằng kana (không bắt buộc). Ví dụ: ドウ.

Email: Địa chỉ email hợp lệ (không bắt buộc). Ví dụ: john.doe@example.com.

Cellular Mail: Địa chỉ email hợp lệ (không bắt buộc). Ví dụ: john.doe@cellular.com.

Outside Telephone: Số điện thoại (không bắt buộc). Ví dụ: 0123456789.

Inside Telephone: Số điện thoại nội bộ (không bắt buộc). Ví dụ: 1234.

Cellular Phone: Số điện thoại di động (không bắt buộc). Ví dụ: 0987654321.

Photo: Tải lên một tệp hình ảnh (không bắt buộc).

Position: Chọn một vị trí từ danh sách (bắt buộc). Ví dụ: Manager.

Administrator: Chọn nếu người dùng là quản trị viên (không bắt buộc).

Ví dụ dữ liệu nhập vào form:
Login Name: john_doe

Password: password123

Confirm Password: password123

First Name: John

Last Name: Doe

First Name (Kana): ジョン

Last Name (Kana): ドウ

Email: john.doe@example.com

Cellular Mail: john.doe@cellular.com

Outside Telephone: 0123456789

Inside Telephone: 1234

Cellular Phone: 0987654321

Photo: (Tải lên một tệp hình ảnh nếu có)

Position: Manager

Administrator: (Chọn nếu cần)

Lưu ý:
Các trường có dấu * là bắt buộc và phải được điền đúng cách.

Login Name phải là duy nhất, nếu không sẽ hiển thị thông báo lỗi.

Password và Confirm Password phải khớp nhau.

Position phải được chọn từ danh sách.

Nếu bạn nhập đúng các thông tin trên, form sẽ không báo lỗi và dữ liệu sẽ được gửi thành công.