// Lấy các phần tử DOM
const openModalBtn = document.getElementById('openAddUserModal');
const modal = document.getElementById('addUserModal');
const closeBtn = document.querySelector('.close');

// Kiểm tra xem các phần tử DOM có tồn tại không
if (openModalBtn && modal && closeBtn) {
    // Mở popup khi nhấn nút "Add User"
    openModalBtn.addEventListener('click', () => {
        modal.style.display = 'block';
    });

    // Đóng popup khi nhấn nút đóng (x)
    closeBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    // Đóng popup khi nhấn bên ngoài popup
    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
} else {
    console.error('One or more DOM elements not found');
}

// Tải danh sách vị trí khi DOM đã tải xong
document.addEventListener('DOMContentLoaded', function () {
    const positionSelect = document.getElementById('positionId');
    if (positionSelect) {  // Kiểm tra xem phần tử có tồn tại không
        fetch('/aipo/api/users/positions')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (Array.isArray(data)) {  // Kiểm tra xem data có phải là mảng không
                    data.forEach(position => {
                        const option = document.createElement('option');
                        option.value = position.positionId;
                        option.textContent = position.positionName;
                        positionSelect.appendChild(option);
                    });
                } else {
                    console.error('Expected an array but got:', data);
                }
            })
            .catch(error => console.error('Error fetching positions:', error));
    } else {
        console.error('Element with id "positionId" not found');
    }
});