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
    <style>
        .quintessential-regular {
  font-family: "Quintessential", serif;
  font-weight: 400;
  font-style: normal;
}
    </style>
</head>
<body class="bg-success-subtle">
<nav class="navbar bg-body-tertiary sticky-top" data-bs-theme="dark">
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


<section class="d-flex flex-column justify-content-center align-items-center text-center text-success py-5">
    <h1 class="display-4 fw-bold quintessential-regular">Unity Hospital</h1>
    <p class="lead mt-3">Compassionate care. Advanced medicine. Healthy future.</p>
</section>

<div class="container my-5">
    <div class="row text-center">
        <div class="col-md-4 mb-3">
            <div class="card h-100 shadow-sm">
                <div class="card-body bg-dark text-success">
                    <img src="images/outpatient.png" width="150" height="150" class="rounded-circle object-fit-cover mb-3 border border-success border-4">
                    <h5 class="card-title text-success">Outpatient Care</h5>
                    <p class="card-text">Comprehensive consultation and treatment by expert doctors.</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-3">
            <div class="card h-100 shadow-sm">
                <div class="card-body bg-dark text-success">
                    <img src="images/emergency.png" alt="Outpatient" width="150" height="150" class="rounded-circle object-fit-cover mb-3 border border-success border-4">
                    <h5 class="card-title text-success">Emergency Services</h5>
                    <p class="card-text">24/7 emergency and trauma care with advanced facilities.</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-3">
            <div class="card h-100 shadow-sm">
                <div class="card-body bg-dark text-success">
                    <img src="images/surgicalcare.png" alt="Outpatient" width="150" height="150" class="rounded-circle object-fit-cover mb-3 border border-success border-4">
                    <h5 class="card-title text-success">Surgical Care</h5>
                    <p class="card-text">State-of-the-art operation theatres for advanced surgeries.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
</body>
</html>
