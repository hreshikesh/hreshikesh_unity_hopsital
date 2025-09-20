<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Patient Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-success-subtle">

<nav class="navbar bg-body-tertiary " data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            <span class="fw-bold fs-5 quintessential-regular">UNITY HOSPITAL</span>
        </a>
        <div class="d-flex">
            <a href="Home" class="btn btn-outline-success">Home</a>
        </div>
    </div>
</nav>

<div class="container py-3 d-flex justify-content-center">
    <form class="bg-success p-5 rounded shadow w-75" action="registerPatient" method="post">
        <h2 class="text-center text-light mb-3">Patient Registration</h2>


        <div class="row mb-3 g-3">
            <div class="col-md-5">
                <label for="patientName" class="form-label fw-semibold text-dark">Full Name</label>
                <input type="text" class="form-control" id="patientName" name="name" placeholder="John Doe" required>
            </div>
            <div class="col-md-3">
                <label for="patientAge" class="form-label fw-semibold text-dark">Age</label>
                <input type="number" class="form-control" id="patientAge" name="age" min="0" max="120" required>
            </div>
            <div class="col-md-4">
                <label for="bloodGroup" class="form-label fw-semibold text-dark">Blood Group</label>
                <select class="form-select" id="bloodGroup" name="bloodGroup" required>
                    <option selected disabled>Select Blood Group</option>
                    <option>A+</option>
                    <option>A-</option>
                    <option>B+</option>
                    <option>B-</option>
                    <option>O+</option>
                    <option>O-</option>
                    <option>AB+</option>
                    <option>AB-</option>
                </select>
            </div>
        </div>

        <div class="row mb-3 g-3">
            <div class="col-md-6">
                <label for="patientEmail" class="form-label fw-semibold text-dark">Email</label>
                <input type="email" class="form-control" id="patientEmail" name="email" placeholder="example@gmail.com" required>
            </div>
            <div class="col-md-6">
                <label for="patientPhone" class="form-label fw-semibold text-dark">Phone</label>
                <input type="text" class="form-control" id="patientPhone" name="phone" maxlength="10" placeholder="9876543210" required>
            </div>
        </div>


        <div class="row mb-2 g-3">
            <div class="col-md-6">
                <label for="patientAddress" class="form-label fw-semibold text-dark">Address</label>
                <textarea class="form-control" id="patientAddress" name="address" rows="2" placeholder="Enter full address..." required></textarea>
            </div>
            <div class="col-md-6">
                <label for="patientDisease" class="form-label fw-semibold text-dark">Disease / Symptoms</label>
                <textarea class="form-control" id="patientDisease" name="disease" rows="2" placeholder="Describe symptoms..." required></textarea>
            </div>
        </div>


        <div class="row mb-3 g-3">
            <div class="col-md-4">
                <label for="specialization" class="form-label fw-semibold text-dark">Doctor Specialization</label>
                <select class="form-select" id="specialization" name="specialization" required>
                    <option selected disabled>Select Specialization</option>
                    <option>Cardiology</option>
                    <option>Neurology</option>
                    <option>Orthopedics</option>
                    <option>General Medicine</option>
                    <option>Dermatology</option>
                </select>
            </div>
            <div class="col-md-4">
                <label for="doctorName" class="form-label fw-semibold text-dark">Doctor Name</label>
                <select class="form-select" id="doctorName" name="doctorName" required>
                    <option selected disabled>Select Doctor</option>
                    <option>Dr. John Smith</option>
                    <option>Dr. Jane Doe</option>
                    <option>Dr. Emily Johnson</option>
                    <option>Dr. Michael Brown</option>
                </select>
            </div>
            <div class="col-md-4">
                <label for="slot" class="form-label fw-semibold text-dark">Appointment Slot</label>
                <input type="datetime-local" class="form-control" id="slot" name="slot" required>
            </div>
        </div>

        <div class="mb-3 col-md-3  mx-auto">
            <label for="fees" class="form-label fw-semibold text-dark">Fees</label>
            <input type="number" class="form-control" id="fees" name="fees" min="0" required>
        </div>

        <div class="d-grid mt-2">
            <button type="submit" class="btn btn-dark  btn-lg">Register Patient</button>
        </div>

    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
