Phần 1 - Phân tích logic

Trong phương thức updateStock() có 2 lỗi logic chính.

Lỗi 1: Sai loại Exception khi tồn kho âm

Quy tắc nghiệp vụ:

Không cho phép số lượng tồn kho xuống dưới 0.

Hiện tại:

if (newStock < 0) {
throw new IllegalStateException("Resulting stock would be negative");
}

IllegalStateException thường dùng khi trạng thái hệ thống không hợp lệ.

Ở đây người dùng nhập dữ liệu không hợp lệ (trừ quá số lượng tồn kho), nên phù hợp hơn là:

throw new IllegalArgumentException("Resulting stock would be negative");

Ví dụ:

Tồn kho hiện tại: 5
quantityChange: -10

Kết quả:

newStock = -5

→ phải báo lỗi nghiệp vụ bằng IllegalArgumentException.

Lỗi 2: Không lưu dữ liệu vào database

Sau khi cập nhật:

product.setStockQuantity(newStock);

đáng lẽ phải:

productRepository.save(product);

nhưng đã bị comment:

// productRepository.save(product);

Hậu quả:

Đối tượng trong bộ nhớ thay đổi.
Database không thay đổi.
Tồn kho thực tế không được cập nhật.

Ví dụ:

Stock hiện tại = 100
Nhập thêm = 20

Sau khi chạy:

newStock = 120

nhưng database vẫn lưu:

100

=> dữ liệu bị sai lệch.