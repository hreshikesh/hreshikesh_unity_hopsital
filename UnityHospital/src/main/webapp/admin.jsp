<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quintessential&display=swap" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
</head>
<body class="bg-success-subtle">

<nav class="navbar bg-body-tertiary" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            <span class="fw-bold fs-5 quintessential-regular">UNITY HOSPITAL</span>
        </a>
        <div class="d-flex">
            <a href="index" class="btn btn-outline-success">Back</a>
        </div>
    </div>
</nav>


<div class="container d-flex justify-content-center align-items-center vh-100">
    <form class="bg-success text-dark p-5 rounded shadow-lg w-50"  action="home" method="post"  id="adminForm">

        <h2 class="text-center  text-dark mb-5">Welcome Admin!</h2>

        <div class="mb-3 text-center">
            <input type="email" class="form-control w-50 mx-auto" id="emailId"
                   onchange="checkEmail()" name="email"
                   placeholder="Enter your email" required>
            <span class="text-danger small" id="emailError"></span>
        </div>


        <div class="mb-3 text-center">
            <button type="button" class="btn btn-dark w-50 fw-bold" id="sendOtpButton" onclick="sendOtp()">
                Send OTP
            </button>
        </div>

        <div class="text-center mb-3">
            <span id="otpStatusId" class="text-warning fw-semibold"></span>
        </div>

        <div id="otpContainerId" class="d-none">

            <div class="mb-3 text-center">
                <input type="text" class="form-control w-50 mx-auto" id="otpId" name="otp"
                       placeholder="Enter OTP" onchange="verifyOtp()" required maxlength="6">
            </div>

            <div class="d-flex justify-content-center align-items-center mb-3 text-center">
                <span id="timeCountId" class="text-dark mb-0 fw-semibold"></span>
                <button type="button" class="btn btn-outline-dark btn-sm ms-1" id="resendId" onclick="resetTimeOtp()" disabled>
                    Resend OTP
                </button>
            </div>

            <p id="timeoutMessageId" class="text-danger text-center mb-3 fw-semibold"></p>

            <div class="mb-3 text-center w-100">
                <button type="submit" id="loginButtonId" class="btn btn-dark fw-bold w-50" disabled>
                    Login
                </button>
            </div>

        </div>
    </form>
</div>

<script src="js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
</body>
</html>
