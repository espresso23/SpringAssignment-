<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container mt-4">
    <div class="row mb-3">
        <div class="col-md-6">
            <form action="<c:url value='/users/search'/>" method="get" class="form-inline">
                <div class="input-group">
                    <select name="searchType" class="form-select">
                        <option value="loginName">Login Name</option>
                        <option value="name">Name</option>
                        <option value="department">Department</option>
                    </select>
                    <input type="text" name="keyword" class="form-control" value="${keyword}" placeholder="Search...">
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>
            </form>
        </div>
        <div class="col-md-6 text-end">
            <button type="button" class="btn btn-success" onclick="location.href='<c:url value='/users/add'/>'">Add User</button>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th><input type="checkbox" id="selectAll"></th>
                    <th>Login Name</th>
                    <th>Name</th>
                    <th>Department</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><input type="checkbox" class="userCheckbox" value="${user.userId}"></td>
                        <td>${user.loginName}</td>
                        <td>${user.fullName}</td>
                        <td>${user.departmentName}</td>
                        <td>
                            <button class="btn btn-sm btn-warning" onclick="toggleUserStatus(${user.userId})">
                                Enable/Disable
                            </button>
                            <button class="btn btn-sm btn-danger" onclick="deleteUser(${user.userId})">
                                Delete
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="row mt-3">
        <div class="col">
            <button id="enableSelected" class="btn btn-success">Enable Selected</button>
            <button id="disableSelected" class="btn btn-warning">Disable Selected</button>
            <button id="deleteSelected" class="btn btn-danger">Delete Selected</button>
        </div>
    </div>
</div>

<script>
$(document).ready(function() {
    // Select all checkbox
    $('#selectAll').change(function() {
        $('.userCheckbox').prop('checked', $(this).prop('checked'));
    });

    // Enable selected users
    $('#enableSelected').click(function() {
        const selectedIds = getSelectedUserIds();
        if (selectedIds.length === 0) {
            alert('Please select users to enable');
            return;
        }
        enableUsers(selectedIds);
    });

    // Disable selected users
    $('#disableSelected').click(function() {
        const selectedIds = getSelectedUserIds();
        if (selectedIds.length === 0) {
            alert('Please select users to disable');
            return;
        }
        disableUsers(selectedIds);
    });

    // Delete selected users
    $('#deleteSelected').click(function() {
        const selectedIds = getSelectedUserIds();
        if (selectedIds.length === 0) {
            alert('Please select users to delete');
            return;
        }
        if (confirm('Are you sure you want to delete the selected users?')) {
            deleteUsers(selectedIds);
        }
    });
});

function getSelectedUserIds() {
    return $('.userCheckbox:checked').map(function() {
        return $(this).val();
    }).get();
}

function enableUsers(userIds) {
    userIds.forEach(userId => {
        $.post('<c:url value="/users/"/>' + userId + '/enable')
            .done(function() {
                location.reload();
            })
            .fail(function(xhr) {
                alert('Error enabling user: ' + xhr.responseText);
            });
    });
}

function disableUsers(userIds) {
    userIds.forEach(userId => {
        $.post('<c:url value="/users/"/>' + userId + '/disable')
            .done(function() {
                location.reload();
            })
            .fail(function(xhr) {
                alert('Error disabling user: ' + xhr.responseText);
            });
    });
}

function deleteUsers(userIds) {
    userIds.forEach(userId => {
        $.ajax({
            url: '<c:url value="/users/"/>' + userId,
            type: 'DELETE',
            success: function() {
                location.reload();
            },
            error: function(xhr) {
                alert('Error deleting user: ' + xhr.responseText);
            }
        });
    });
}

function toggleUserStatus(userId) {
    // Implementation will depend on current user status
    // For now, we'll just try to enable the user
    $.post('<c:url value="/users/"/>' + userId + '/enable')
        .done(function() {
            location.reload();
        })
        .fail(function(xhr) {
            // If enabling fails, try disabling
            $.post('<c:url value="/users/"/>' + userId + '/disable')
                .done(function() {
                    location.reload();
                })
                .fail(function(xhr) {
                    alert('Error toggling user status: ' + xhr.responseText);
                });
        });
}

function deleteUser(userId) {
    if (confirm('Are you sure you want to delete this user?')) {
        $.ajax({
            url: '<c:url value="/users/"/>' + userId,
            type: 'DELETE',
            success: function() {
                location.reload();
            },
            error: function(xhr) {
                alert('Error deleting user: ' + xhr.responseText);
            }
        });
    }
}
</script> 