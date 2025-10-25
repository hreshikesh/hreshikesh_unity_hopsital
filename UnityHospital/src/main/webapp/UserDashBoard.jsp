<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital - User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>

    <style>
        body { font-family: 'Poppins', sans-serif; }
        .feature-icon { font-size: 2rem; }
        .card:hover { transform: translateY(-5px); transition: 0.3s ease; }
        .card a.btn:hover { transform: scale(1.05); transition: 0.3s ease; }
        .content:hover { transform: scale(1.05); transition: 0.3s ease; border:1px solid green }
        .quintessential-regular {
            font-family: "Quintessential", serif;
            font-weight: 400;
            font-style: normal;
        }
    </style>
</head>
<body class="bg-success-subtle">

<nav class="navbar bg-body-tertiary shadow-sm" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            <span class="fw-bold fs-5 quintessential-regular">UNITY HOSPITAL</span>
        </a>
        <div class="d-flex">
            <a href="logout" class="btn btn-outline-success"><i class="bi bi-box-arrow-right"></i> Logout</a>
        </div>
    </div>
</nav>

<div class="container py-5">
    <div class="card mb-5 shadow-lg border-0 rounded-4">
        <div class="card-body text-center bg-white p-5">
            <h2 class="text-success fw-bold">Welcome, ${user}!</h2>
            <p class="text-muted fs-5">You have successfully logged in. Explore our hospital services and stay informed about your health.</p>
            <div class="d-flex justify-content-center gap-3 mt-4">
                <a href="myAppointment" class="btn btn-success btn-lg rounded-pill">
                    <i class="bi bi-person-circle me-2"></i> My Appointment
                </a>
                <a href="profile" class="btn btn-outline-success btn-lg rounded-pill">
                    <i class="bi bi-person-circle me-2"></i> My Profile
                </a>
            </div>
            <div class="d-flex justify-content-center gap-3 mt-4">
                <a href="patientRegistration" class="btn btn-success btn-lg rounded-pill">
                    <i class="bi bi-person-circle me-2"></i>Book an Appointment
                </a>
            </div>
        </div>
    </div>

    <div class="row g-4 mb-5">
        <div class="col-md-6">
            <div class="card shadow-sm border-0 rounded-4 h-100 bg-white p-4 d-flex justify-content-center align-items-center">
                <div class="card-body">
                    <h4 class="text-success fw-bold mb-3">About Unity Hospital</h4>
                    <p class="text-muted fs-6" id="aboutPreview">
                        Unity Hospital is a premier healthcare institution dedicated to high-quality medical care. Our experienced team ensures compassionate treatment with advanced technology.
                    </p>
                    <p class="text-muted fs-6 collapse" id="aboutFull">
                        Established in 2005, Unity Hospital has consistently focused on patient-centric care. We offer modern diagnostics, comfortable patient rooms, and specialized wellness programs. Our vision is to promote healthy living through preventive care and advanced medical services. With a commitment to safety, comfort, and innovation, Unity Hospital strives to be a trusted partner in your health journey.
                    </p>
                    <div class="mt-3 d-flex justify-content-center">
                        <a class="btn btn-success rounded-pill" data-bs-toggle="collapse" href="#aboutFull" role="button" aria-expanded="false" aria-controls="aboutFull">
                            Learn More
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card shadow-sm border-0 rounded-4 h-100 bg-white p-4">
                <div class="card-body">
                    <h4 class="text-success fw-bold mb-3">Our Services</h4>
                    <div class="row g-3">
                        <div class="col-6 d-flex align-items-start content p-1">
                            <i class="bi bi-person-check-fill text-success feature-icon me-3"></i>
                            <div>
                                <h6 class="fw-bold mb-0">Patient Care</h6>
                                <small class="text-muted">Focused on your health and comfort with personalized attention.</small>
                            </div>
                        </div>
                        <div class="col-6 d-flex align-items-start content p-1">
                            <i class="bi bi-journal-medical text-success feature-icon me-3"></i>
                            <div>
                                <h6 class="fw-bold mb-0">Consultations</h6>
                                <small class="text-muted">Professional advice tailored to your health needs.</small>
                            </div>
                        </div>
                        <div class="col-6 d-flex align-items-start content p-1">
                            <i class="bi bi-clipboard-check text-success feature-icon me-3"></i>
                            <div>
                                <h6 class="fw-bold mb-0">Diagnostics</h6>
                                <small class="text-muted">Accurate lab and imaging services for informed decisions.</small>
                            </div>
                        </div>
                        <div class="col-6 d-flex align-items-start content p-1">
                            <i class="bi bi-people text-success feature-icon me-3"></i>
                            <div>
                                <h6 class="fw-bold mb-0">Wellness Programs</h6>
                                <small class="text-muted">Designed to promote healthy living and prevent illness.</small>
                            </div>
                        </div>
                        <div class="col-12 d-flex align-items-start content p-1">
                            <i class="bi bi-hospital text-success feature-icon me-3"></i>
                            <div>
                                <h6 class="fw-bold mb-0">Patient Support</h6>
                                <small class="text-muted">Friendly assistance for appointments, follow-ups, and hospital guidance.</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<footer class="bg-dark text-success pt-4 pb-2">
    <div class="container">
        <div class="row text-center text-lg-start justify-content-center align-items-center">

            <div class="col-lg-4 mb-3">
                <p class="mb-1 text-center">67th Cross, Attiguppe, Bengaluru</p>
                <p class="mb-0 text-center">Phone: +91 98765 43210</p>
            </div>

            <div class="col-lg-4 mb-3 text-center">
                <button class="btn btn-success px-4 py-2 fw-semibold"
                        data-bs-toggle="modal"
                        data-bs-target="#contactModal">
                    Contact Us
                </button>
            </div>

            <div class="col-lg-4 mb-3 text-center text-lg-center">
                <h6 class="fw-bold mb-1">&copy; 2025 Unity Hospital</h6>
                <small>All rights reserved.</small>
            </div>

        </div>
    </div>
</footer>

<div class="modal fade" id="contactModal" tabindex="-1" aria-labelledby="contactModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title" id="contactModalLabel">Contact Unity Hospital</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <form class="needs-validation" novalidate  method="post" action="contact">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="name" class="form-label">Full Name</label>
                        <input type="text" class="form-control" id="name" placeholder="Enter your name" name="fullName" required>
                        <div class="invalid-feedback">Please enter your name.</div>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" placeholder="Enter your email" name="email" required>
                        <div class="invalid-feedback">Please enter a valid email.</div>
                    </div>

                    <div class="mb-3">
                        <label for="subject" class="form-label">Message</label>
                        <textarea class="form-control" id="subject" rows="3" placeholder="Your subject here..." name="subject" required></textarea>
                        <div class="invalid-feedback">Please enter your subject.</div>
                    </div>

                    <div class="mb-3">
                        <label for="message" class="form-label">Message</label>
                        <textarea class="form-control" id="message" rows="3" placeholder="Your message here..." name="message" required></textarea>
                        <div class="invalid-feedback">Please enter your message.</div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-success">Send Message</button>
                </div>
            </form>
        </div>
    </div>
</div>

<c:choose>
    <c:when test="${check}">
        <div class="modal fade" id="resultmodal" tabindex="-1" aria-labelledby="contactModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <p class="text-success text-center">Your Email has been Sent We will contact You soon...</p>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="modal fade" id="resultmodal1" tabindex="-1" aria-labelledby="contactModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <p class="text-success text-center">Issue in sending mail</p>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" ></script>

<script>
    (() => {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})()
</script>

</body>
</html>
