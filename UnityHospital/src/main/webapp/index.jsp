<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital</title>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css">


    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quintessential&display=swap" rel="stylesheet">
</head>
<body class="bg-success-subtle">


<nav class="navbar navbar-expand-lg bg-body-tertiary sticky-top" data-bs-theme="dark">
    <div class="container">

        <a class="navbar-brand d-flex align-items-center text-success" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            <span class="fw-bold fs-5" style="font-family: 'Quintessential', serif;">UNITY HOSPITAL</span>
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarMenu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarMenu">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0 align-items-lg-center">
                <li class="nav-item">
                    <a class="nav-link text-success d-flex align-items-center" href="admin">
                        <i class="bi bi-person-circle me-1 fs-4"></i> Admin
                    </a>
                </li>
                <li class="nav-item ms-lg-2">
                    <a class="btn btn-success rounded-pill px-4 py-2" href="userSignUp">User Sign Up</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<section class="vh-100 d-flex align-items-center position-relative">
    <img src="images/courosel4.png" alt="Hospital" class="position-absolute top-0 start-0 w-100 h-100 opacity-25 object-fit-cover">
    <div class="container text-center text-success position-relative">
        <h1 class="display-3 fw-bold mb-3" style="font-family: 'Quintessential', serif;">Unity Hospital</h1>
        <p class="lead fs-5 mb-4">Compassionate care. Advanced medicine. Healthy future.</p>
        <p class="mb-4 fs-6">Providing world-class healthcare services with a personal touch. Our team of experts ensures the highest standards of medical care and patient comfort.</p>
        <div class="d-flex justify-content-center gap-3">
            <a href="service" class="btn btn-success btn-lg rounded-pill px-4">Explore Services</a>
        </div>
    </div>
</section>

<footer class="bg-dark text-success pt-4 pb-2">
    <div class="container">
        <div class="row text-center text-lg-start justify-content-center">
            <div class="col-lg-4 mb-3">
                <h6 class="fw-bold text-center">Contact</h6>
                <p class="mb-1 text-center">67th cross, Attiguppe, Bengaluru</p>
                <p class="mb-0 text-center">Phone: +91 98765 43210</p>
            </div>
            <div class="col-lg-4 mb-3 text-lg-center">
                <h6 class="fw-bold">&copy; 2025 Unity Hospital</h6>
                <small>All rights reserved.</small>
            </div>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
