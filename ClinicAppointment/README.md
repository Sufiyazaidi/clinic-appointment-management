# 🏥 Clinic Appointment Management System

A lightweight RESTful Spring Boot application to manage appointments between doctors and patients using in-memory data storage (Maps/Lists). The system supports booking appointments, listing doctors/patients/appointments, and filtering available slots by date.

---

## 📦 Features

- Book appointments between doctors and patients.
- View all doctors, patients, and appointments.
- View appointments by doctor with optional date filtering.
- Thread-safe appointment booking.
- In-memory data storage (no database).
- Uses Java 8 features (Streams, Optionals, Lambdas).
- Input validation and global exception handling.
- Well-organized layered architecture.

---

## 🛠️ Setup Instructions

### 🔧 Prerequisites

- Java 8 or higher
- Maven
- Postman (for testing APIs)

### 🚀 Running the Application

```bash
# Clone the repository
git clone https://github.com/Sufiyazaidi/clinic-appointment-management.git
cd clinic-appointment-management

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
````

The application will start on:

```
http://localhost:8080
```

---

## 📬 API Testing with Postman

All endpoints can be tested using the provided Postman collection.

### 🗂️ Location of Postman Collection

The Postman collection is located at:

```
ClinicAppointment/Postman/Clinic_Appointment_Postman_Collection.json
```

> The `postman` folder is at the root of the project, alongside `src`, `README.md`, etc.

### 🧪 How to Import and Test

1. Open **Postman**.
2. Click on **Import**.
3. Choose the file:

   ```
   ClinicAppointment/Postman/Clinic_Appointment_Postman_Collection.json
   ```
4. After importing, you will see a collection named **Clinic Appointment Management System**.
5. Expand it to view and test all available API requests.

---

## 📂 API Overview

| Method | Endpoint                    | Description                                                                    |
| ------ | --------------------------- | ------------------------------------------------------------------------------ |
| POST   | `/doctors`                  | Add a doctor                                                                   |
| GET    | `/doctors`                  | Get all doctors                                                                |
| GET    | `/doctors/{id}`             | Get doctor by ID <br> ➤ Optional: `?date=YYYY-MM-DD` to filter available slots |
| POST   | `/patients`                 | Add a patient                                                                  |
| GET    | `/patients`                 | Get all patients                                                               |
| GET    | `/patients/{id}`            | Get patient by ID                                                              |
| POST   | `/appointments`             | Book an appointment                                                            |
| GET    | `/appointments`             | Get all appointments                                                           |
| GET    | `/appointments/doctor/{id}` | Get all appointments of a doctor by ID                                         |

---

## 🧠 Design Highlights

* **Thread-Safe Booking:** Uses `ConcurrentHashMap` and synchronized blocks to avoid conflicting bookings.
* **In-Memory Data Layer:** Uses Maps for fast lookups and easy debugging.
* **Validation & Error Handling:** Proper error messages for missing data.
* **Date Filtering:** Easily filter doctor slots or appointments using `?date=YYYY-MM-DD`.
* **Clean Architecture:** Follows layered architecture with `controller`, `service`, `repository`, `model`, and `exception` packages.


##  You're Ready to Test!

Once the app is running and Postman collection is imported, you're all set to test the full flow:

* Add a doctor
* Add a patient
* Book an appointment
* View appointments by doctor/date



