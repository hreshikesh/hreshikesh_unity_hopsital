<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quintessential&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
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
<nav class="navbar bg-body-tertiary" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            <span class="fw-bold fs-5 quintessential-regular">UNITY HOSPITAL</span>
        </a>
        <div class="d-flex">
            <a href="index.jsp" class="btn btn-outline-success">Back</a>
        </div>
    </div>
</nav>

<div class="container d-flex justify-content-center align-items-center vh-100">
    <form class="bg-success text-light p-4 rounded shadow-lg border border-success w-100"
          style="max-width: 400px;" action="adminEmail" method="post" id="adminForm">

        <h2 class="text-center mb-4">Welcome Admin!</h2>


        <div class="mb-3">
            <label for="emailId" class="form-label">Email address</label>
            <input type="email" class="form-control" id="emailId"
                   value="${email}"
                   onchange="checkEmail()" name="email"
                   placeholder="Enter your email" required>
            <span class="text-danger" id="emailError"></span>
        </div>

        <c:if test="${empty check}">
            <div class="mb-3 text-center">
                <button type="submit" class="btn btn-light w-50" id="sendOtpButton" formaction="sendotp">Send OTP</button>
            </div>
        </c:if>

        <c:if test="${not empty check}">
            <c:choose>
                <c:when test="${check}">
                    <div class="mb-3">
                        <label for="otpId" class="form-label">OTP</label>
                        <input type="text" class="form-control" id="otpId" name="otp"
                               placeholder="Enter OTP" required maxlength="6">
                    </div>
                    <div class="mb-3 text-center">
                        <button type="submit" class="btn btn-light w-50" formaction="adminEmail">Login</button>
                    </div>
                </c:when>

                <c:otherwise>
                    <p class="text-danger text-center">Email not valid</p>
                </c:otherwise>

            </c:choose>
        </c:if>
        <div class="mb-3">
            <c:if test="${not empty otpstatus}">

                <c:if test="${otpstatus eq 'time'}">
                    <p class="text-center text-warning">OTP TimeOut</p>
                    <div class="text-center"> <a href="resendOtp"> Resend OTP</a></div>
                </c:if>

                <c:if test="${otpstatus eq 'mismatch'}">
                    <p class="text-center text-warning">OTP not matched</p>
                </c:if>


            </c:if>
        </div>
    </form>
</div>
        <script src="js/index.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
                crossorigin="anonymous"></script>
</body>
</html>
