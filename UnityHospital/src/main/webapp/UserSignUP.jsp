<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quintessential&display=swap" rel="stylesheet">

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

<nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            <span class="fw-bold fs-5 quintessential-regular">UNITY HOSPITAL</span>
        </a>
        <div class="d-flex">
            <a href="index" class="btn btn-outline-success"><i class="bi bi-house"></i></a>
        </div>
    </div>
</nav>

<div class="container d-flex justify-content-center align-items-center" style="min-height: 85vh;">
    <div class="card shadow-lg border-success w-100 mt-3" style="max-width: 400px;">
        <div class="card-body">
            <h3 class="text-center text-success mb-4 fw-bold">User Sign Up</h3>
            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <ul class="mb-0">
                        <c:forEach var="err" items="${error}">
                            <li class="text-danger small">${err.defaultMessage}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form action="registerUser" method="post">
                <div class="mb-3">
                    <label for="usernameId" class="form-label text-success fw-semibold">Username</label>
                    <input type="text" class="form-control border-success" id="usernameId" name="userName" value="${dto.userName}"
                      oninput="validateUserName()"     placeholder="Enter username" maxlength="25" required>
                    <span id="usernameError" class="text-danger small mt-1"></span>
                </div>

                <div class="mb-3">
                    <label for="emailId" class="form-label text-success fw-semibold">Email</label>
                    <input type="email" class="form-control border-success" id="emailId" name="userEmail"
                      oninput="validateUserEmail()" onchange="checkUserEmail()" value="${dto.userEmail}"    placeholder="Enter email" required>
                    <span id="useremailError" class="text-danger small mt-1"></span>
                    <span id="userEmailCheckError" class="text-danger small mt-1"></span>
                </div>

                <div class="mb-3">
                        <label for="phoneId" class="form-label text-success fw-semibold">Phone No</label>
                        <input type="text"  class="form-control  border-success" oninput="validatePhone()"  maxlength="10" id="phoneId" name="phone" value="${dto.phone}" required >
                        <span class="text-danger small mt-1" id="phoneErrorId"></span>
                    <span id="userPhoneCheckError" class="text-danger small mt-1"></span>
                </div>


                <div class="d-grid">
                    <button type="submit" class="btn btn-success fw-semibold">Sign Up</button>
                </div>
            </form>
            <div class="mt-2">
                <p class="text-center">Already a user <span class="link-primary"><a href="signin"> sign in</a></span> </p>
            </div>
        </div>
    </div>
</div>

<c:if test="${not empty result}">
        <div class="position-fixed bottom-0 end-0  p-3 " >
            <div class="toast align-items-center text-bg-dark " role="alert" aria-live="assertive" aria-atomic="true">
                <div class="d-flex">
                    <div class="toast-body text-danger">
                        ${result}
                    </div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            </div>
        </div>
</c:if>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/user.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
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
