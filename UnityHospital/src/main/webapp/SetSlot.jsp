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
            <a href="slotback" class="btn btn-outline-success">Back</a>
        </div>
    </div>
</nav>

<div class="container w-50 mt-5">
    <form class="bg-success p-4 mt-5 rounded shadow w-50 mx-auto" action="settimeslot" method="post">
        <h2 class="text-center text-light mb-4">Time Slot Setting</h2>

        <c:if test="${not empty errors}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <ul class="mb-0">
                    <c:forEach var="err" items="${errors}">
                        <li>${err.defaultMessage}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <div class="mb-3">
            <label for="fromTimeId" class="form-label fw-semibold">From</label>
            <input type="time" class="form-control" id="fromTimeId" name="fromTime" required>
        </div>

        <div class="mb-3">
            <label for="toTimeId" class="form-label fw-semibold">To</label>
            <input type="time" class="form-control" id="toTimeId" name="toTime" required>
        </div>


        <div class="d-grid">
            <button type="submit" class="btn btn-dark fw-bold">Set Time Slot</button>
        </div>

        <c:if test="${not empty result}">
            <p class="text-center mt-3 text-warning">${result}</p>
        </c:if>

    </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/index.js"></script>
</body>
</html>
