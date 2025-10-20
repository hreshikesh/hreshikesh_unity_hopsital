<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Profile - Unity Hospital</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body class="bg-success-subtle">

<nav class="navbar bg-body-tertiary shadow-sm" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success fw-bold" href="#">
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
                    <i class="bi bi-person-circle me-2"></i> User Profile
                </div>
                <div class="card-body">
                    <div class="mb-3">
                        <label class="form-label fw-semibold text-success">User Name</label>
                        <input type="text" oninput="validateUserName()" class="form-control" value="${dto.userName}" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold text-success">Email</label>
                        <input type="text" class="form-control" value="${dto.userEmail}" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold text-success">Phone</label>
                        <input type="text" class="form-control" value="${dto.phone}" readonly>
                    </div>

                    <div class="text-center">
                        <form action="updateProfile" method="get">
                            <button type="submit" class="btn btn-success">
                                <i class="bi bi-pencil-square me-1"></i> Update Profile
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
