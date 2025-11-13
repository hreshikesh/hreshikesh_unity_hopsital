<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital</title>
    <link rel="icon" href="images/hospitallogo.webp">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
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

<div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
    <div class="card shadow-sm w-100" style="max-width: 400px;">
        <div class="card-body">
            <h4 class="text-center text-success fw-bold" style="font-family: 'Quintessential', serif;">Sign In </h4>
            <p class="text-center text-muted small mb-4">Enter your registered email to receive OTP</p>

            <form action="sendOtp" method="post">
                <div class="mb-3">
                    <label for="emailId" class="form-label">Email address</label>
                    <input type="email" class="form-control" id="emailId" name="email" value="${email}"
                           placeholder="Enter your email" required>
                    <span id="userEmailCheckError" class="text-danger small mt-1"></span>
                </div>
                <c:if test="${empty check}">
                    <button type="submit" class="btn btn-success w-100" id="sendOtpBtn">
                        <i class="bi bi-envelope"></i> Send OTP
                    </button>
                </c:if>
                <span class="text-danger text-center small mt-1">${emailerror}</span>
            </form>

            <c:if test="${check}">
                <form action="login"  method="post" id="otpForm" class="mt-4">
                    <div class="mb-3">
                        <label for="otpId" class="form-label">Enter OTP</label>
                        <input type="text" class="form-control" id="otpId" name="otp" placeholder="Enter 6-digit OTP" onchange="verifyOtp()" maxlength="6" required>
                        <span id="otpStatusId" class="text-danger text-center small mt-1"></span>
                    </div>
                    <input type="hidden" value="${email}" name="email" required>

                    <p id="timeoutMessageId" class="text-danger text-center fw-semibold"></p>

                    <button type="submit" class="btn btn-success w-100" id="loginButtonId" disabled>Verify OTP</button>
                </form>

                <div class="d-flex justify-content-center align-items-center m-3">
                    <span id="timeCountId" class="text-dark fw-semibold me-2"></span>
                    <form action="resendOtp" method="post" class="d-inline">
                        <input type="hidden" name="email" value="${email}">
                        <button type="submit" class="btn btn-outline-dark btn-sm" id="resendId" disabled>
                            Resend OTP
                        </button>
                    </form>
                </div>
            </c:if>

        </div>

        <c:if test="${otpSent}">
            <div class="modal fade" id="otpSentModal" tabindex="-1" aria-labelledby="otpSentLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header bg-success text-white">
                            <h5 class="modal-title" id="otpSentLabel"><i class="bi bi-envelope-check"></i> OTP Sent</h5>
                            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body text-center">
                            <p>OTP has been sent to your registered email address.</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/user.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

</body>
</html>
