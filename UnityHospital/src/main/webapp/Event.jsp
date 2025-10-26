
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
        <div class="d-flex gap-3">
            <a href="Home" class="btn btn-outline-success"><i class="bi bi-house"></i></a>
        </div>
    </div>
</nav>'

<div class="container mt-5">
    <div class="card bg-success text-white mx-auto" style="max-width: 500px;">
        <div class="card-header text-black text-center fs-4 fw-bold">
            Add  Event
        </div>
        <div class="card-body">
            <form action="addEvent" method="post">
                <div class="mb-3">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <ul class="mb-0 ps-3">
                                <c:forEach var="err" items="${error}">
                                    <li class="small">${err.defaultMessage}</li>
                                </c:forEach>
                            </ul>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <label for="eventId" class="form-label">Event Details</label>
                    <input type="text" id="eventId" class="form-control"
                           placeholder="Enter Event Details" oninput="validateInput()" name="event" value="${dto.event}" minlength="5" maxlength="50" required>
                    <span id="messageId" class="form-text text-warning"></span>
                </div>
                <div class="d-flex justify-content-center gap-2">
                    <button type="submit" class="btn btn-warning"><i class="bi bi-plus-circle mx-2"></i>Add</button>
                    <button type="reset" class="btn btn-danger"><i class="bi bi-x-circle mx-2"></i>Reset</button>
                </div>
            </form>
        </div>
    </div>
</div>

<c:if test="${not empty result}">
    <div class="position-absolute bottom-0 start-50 translate-middle-x p-3">
        <div class="toast align-items-center text-bg-success " role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body text-warning">
                    ${result}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
</c:if>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
<script>
    function validateInput() {
        const input = document.getElementById("eventId");
        const message = document.getElementById("messageId");

        if (input.value.length > 50 ||input.value.length < 5 ) {
            message.textContent = "Maximum 5 characters allowed and minimum 50 characters";
        } else {
            message.textContent = "";
        }
    }

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
</body>
</html>