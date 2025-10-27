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
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            <a href="event" class="btn btn-outline-success"><i class="bi bi-arrow-return-left"></i></a>
        </div>
    </div>
</nav>

<c:if test="${not empty result}">
    <p class="mx-auto alert alert-primary  text-center m-3">${result}</p>
</c:if>

<c:if test="${check}">
    <div class="row d-flex justify-contents-center align-items-center">
    <c:forEach var="dto" items="${dtos}" varStatus="loop">
        <div class="col-3">
        <div class=" card text-center m-3 border border-success border-3 rounded-5" style="width: 18rem;">
            <div class="card-header bg-success text-dark fs-5 border-success rounded-5">
                Event
            </div>
            <div class="card-body text-success">
                <h5 class="card-title ">${dto.event}</h5>
                <p class="card-text">${dto.date}</p>
            </div>
            <div class="card-footer text-body-secondary">
                <button type="button" class="btn btn-success rounded-5"
                        data-bs-toggle="modal"
                        data-bs-target="#confirmDeleteModal${loop.index}">
                    <i class="bi bi-trash"></i> Delete Event
                </button>
            </div>
        </div>
        </div>
        <div class="modal fade" id="confirmDeleteModal${loop.index}" tabindex="-1"
             aria-labelledby="confirmDeleteLabel${loop.index}" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header bg-dark text-success">
                        <h1 class="modal-title fs-5" id="confirmDeleteLabel${loop.index}">Confirm Deletion</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body bg-success-subtle">
                        Are you sure you want to delete this Event?
                        <form action="deleteEvent" method="post" class="d-inline">
                            <input type="hidden" value="${dto.id}" name="id">
                            <button type="submit" class="btn btn-danger">Yes, Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</c:if>


<c:if test="${not check}">
    <p class="text-center fs-3 text-danger">No Events Found</p>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
</body>
</html>