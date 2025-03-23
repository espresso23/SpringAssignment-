<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1>AIPO - User Management</h1>
        </div>
        <div class="col-md-6 text-end">
            <span class="current-time" id="currentTime"></span>
        </div>
    </div>
</div>

<script>
function updateTime() {
    const now = new Date();
    const options = { 
        weekday: 'long', 
        year: 'numeric', 
        month: 'long', 
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
    };
    document.getElementById('currentTime').textContent = now.toLocaleDateString('ja-JP', options);
}

updateTime();
setInterval(updateTime, 1000);
</script> 