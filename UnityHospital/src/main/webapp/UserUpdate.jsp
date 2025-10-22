
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Profile - Unity Hospital</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
    <style>
        .quintessential-regular {
         font-family: "Quintessential", serif;
         font-weight: 400;
         font-style: normal;
     }
    </style>
</head>
<body class="bg-success-subtle">

<nav class="navbar bg-body-tertiary shadow-sm" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success fw-bold quintessential-regular" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            UNITY HOSPITAL
        </a>
        <div class="d-flex">
            <a href="userDashboard" class="btn btn-outline-success">
                <i class="bi bi-arrow-return-left"></i>
            </a>
        </div>
    </div>
</nav>

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-success text-white text-center fs-5 fw-semibold">
                    <i class="bi bi-person-circle me-2"></i> Update Profile
                </div>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <ul class="mb-0">
                            <c:forEach var="err" items="${error}">
                                <li class="text-danger small">${err.defaultMessage}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <div class="card-body">
                    <form action="saveUpdatedProfile" method="post">
                        <input type="hidden" name="id" value="${dto.id}">
                        <div class="mb-3">
                            <label class="form-label fw-semibold text-success">User Name</label>
                            <input type="text" class="form-control" name="userName" id="usernameId"
                                   value="${dto.userName}" minlength="3" maxlength="25"
                                   oninput="validateUserName()"
                                   title="Only alphabets allowed"
                                   required>
                            <span id="usernameError" class="text-danger small mt-1"></span>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold text-success">Email</label>
                            <input type="email" class="form-control" name="userEmail"
                                   value="${dto.userEmail}" readonly>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-semibold text-success">Phone</label>
                            <input type="text" class="form-control" name="phone"
                                   value="${dto.phone}"
                                   oninput="validatePhone()"  maxlength="10" id="phoneId"
                                   title="Must be 10 digits starting with 6, 7, 8, or 9"
                                   required>
                            <span class="text-danger small mt-1" id="phoneErrorId"></span>
                        </div>
                        <div class="d-flex justify-content-between">
                            <a href="userDashboard" class="btn btn-outline-success">
                                <i class="bi bi-arrow-left-circle"></i> Back
                            </a>
                            <button type="submit" class="btn btn-success">
                                <i class="bi bi-check-circle me-1"></i> Save Changes
                            </button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<c:if test="${not empty status}">
    <c:if test="${status eq 'success'}">
        <div class="position-fixed bottom-0 end-0  p-3 " >
            <div class="toast align-items-center text-bg-dark " role="alert" aria-live="assertive" aria-atomic="true">
                <div class="d-flex">
                    <div class="toast-body text-success">
                       Successfully Updated User Profile
                    </div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${status eq 'failure'}">
        <div class="position-fixed bottom-0 end-0  p-3 " >
            <div class="toast align-items-center text-bg-dark " role="alert" aria-live="assertive" aria-atomic="true">
                <div class="d-flex">
                    <div class="toast-body text-danger">
                        User Profile not Updated
                    </div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            </div>
        </div>
    </c:if>
</c:if>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/user.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
   const toastElList = document.querySelectorAll('.toast');
   toastElList.forEach(toastEl => {
       const toast = new bootstrap.Toast(toastEl, { delay: 3000 });
       toast.show();
   });
});
</script>
</body>
</html>
