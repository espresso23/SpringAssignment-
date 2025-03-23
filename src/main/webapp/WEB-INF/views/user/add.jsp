<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="modal fade" id="addUserModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add New User</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form:form modelAttribute="userDTO" action="/users/add" method="post" class="needs-validation" novalidate="true">
                    <c:if test="${error != null}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="loginName">Login Name *</label>
                                <form:input path="loginName" class="form-control" required="true"/>
                                <form:errors path="loginName" class="text-danger"/>
                                <div class="invalid-feedback">Login name is required</div>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="password">Password *</label>
                                <form:password path="password" class="form-control" required="true"/>
                                <form:errors path="password" class="text-danger"/>
                                <div class="invalid-feedback">Password is required</div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="passwordConfirm">Confirm Password *</label>
                                <form:password path="passwordConfirm" class="form-control" required="true"/>
                                <form:errors path="passwordConfirm" class="text-danger"/>
                                <div class="invalid-feedback">Password confirmation is required</div>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="firstName">First Name *</label>
                                <form:input path="firstName" class="form-control" required="true"/>
                                <form:errors path="firstName" class="text-danger"/>
                                <div class="invalid-feedback">First name is required</div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="lastName">Last Name *</label>
                                <form:input path="lastName" class="form-control" required="true"/>
                                <form:errors path="lastName" class="text-danger"/>
                                <div class="invalid-feedback">Last name is required</div>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="firstNameKana">First Name (Kana)</label>
                                <form:input path="firstNameKana" class="form-control"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="lastNameKana">Last Name (Kana)</label>
                                <form:input path="lastNameKana" class="form-control"/>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <form:input path="email" type="email" class="form-control"/>
                                <form:errors path="email" class="text-danger"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="cellularMail">Cellular Mail</label>
                                <form:input path="cellularMail" type="email" class="form-control"/>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="outTelephone">Outside Telephone</label>
                                <form:input path="outTelephone" class="form-control"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="inTelephone">Inside Telephone</label>
                                <form:input path="inTelephone" class="form-control"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="cellularPhone">Cellular Phone</label>
                                <form:input path="cellularPhone" class="form-control"/>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="photo">Photo</label>
                                <input type="file" name="photoFile" class="form-control" accept="image/*"/>
                                <small class="form-text text-muted">Upload a profile photo (optional)</small>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="positionId">Position *</label>
                                <form:select path="positionId" class="form-select" required="true">
                                    <form:option value="">Select Position</form:option>
                                    <form:options items="${positions}" itemValue="positionId" itemLabel="positionName"/>
                                </form:select>
                                <form:errors path="positionId" class="text-danger"/>
                                <div class="invalid-feedback">Position is required</div>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-12">
                            <div class="form-check">
                                <form:checkbox path="isAdmin" class="form-check-input"/>
                                <label class="form-check-label" for="isAdmin">Administrator</label>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script>
$(document).ready(function() {
    // Form validation
    const form = document.querySelector('.needs-validation');
    form.addEventListener('submit', function(event) {
        if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        form.classList.add('was-validated');
    });

    // Check login name availability
    $('#loginName').blur(function() {
        const loginName = $(this).val();
        if (loginName) {
            $.get('<c:url value="/users/check-login-name"/>', { loginName: loginName })
                .done(function(exists) {
                    if (exists) {
                        $('#loginName').addClass('is-invalid')
                            .siblings('.invalid-feedback')
                            .text('This login name is already taken');
                    } else {
                        $('#loginName').removeClass('is-invalid');
                    }
                });
        }
    });

    // Password confirmation validation
    $('#passwordConfirm').blur(function() {
        if ($(this).val() !== $('#password').val()) {
            $(this).addClass('is-invalid')
                .siblings('.invalid-feedback')
                .text('Passwords do not match');
        } else {
            $(this).removeClass('is-invalid');
        }
    });

    // Photo preview
    $('input[name="photoFile"]').change(function() {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                $('#photoPreview').attr('src', e.target.result);
            };
            reader.readAsDataURL(file);
        }
    });
});
</script> 