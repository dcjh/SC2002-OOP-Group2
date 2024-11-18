# SC2002-OOP-Group2

This is a **Medical Management System** application built using Java. The application follows the **MVC (Model-View-Controller)** design pattern and uses **DAO (Data Access Object)** classes to interact with CSV files for data persistence. The project is designed to simulate a hospital management system, including modules for appointments, patient records, staff management, and more.

---

## Project Structure

The project is organized into multiple packages as follows:

### 1. App
- **`App.java`**: The main entry point for running the application.

### 2. Authentication
- **`AccountManager.java`**: Handles user authentication and account-related operations.

### 3. Controller
Controllers handle business logic and act as intermediaries between the model and view.  
Some key controllers:
- **`AppointmentController.java`**: Manages appointments.
- **`AppointmentOutcomeController.java`**: Handles appointment outcomes and their logic.
- **`PatientController.java`**: Manages patient records and interactions.
- **`PharmacistController.java`**: Manages pharmacist-specific tasks like inventory and prescriptions.
- **`DoctorController.java`**: Manages doctor-related tasks such as appointments and medical records.

### 4. Data
CSV files that serve as the database for the application:
- `Appointment.csv`
- `AppointmentOutcome.csv`
- `Patient_List.csv`
- `Schedule.csv`
- `Medicine_List.csv`
- `Staff_List.csv`
- `Replenishment_Restock.csv`
- Others...

### 5. DataAccess
DAO classes for managing CRUD operations with CSV files:
- **`AppointmentDAO.java`**: Handles data for `Appointment.csv`.
- **`AppointmentOutcomeDAO.java`**: Manages data for `AppointmentOutcome.csv`.
- **`PatientDAO.java`**: Manages patient records from `Patient_List.csv`.
- **`ReplenishmentDAO.java`**: Handles restock requests from `Replenishment_Restock.csv`.
- **`ScheduleDAO.java`**: Manages schedules from `Schedule.csv`.

### 6. Model
Model classes that represent the data structure:
- **`Appointment.java`**: Represents an appointment.
- **`AppointmentOutcome.java`**: Represents an appointment outcome with prescribed medications.
- **`Patient.java`**: Represents a patient.
- **`Pharmacist.java`**, **`Doctor.java`**, **`Administrator.java`**, etc.: Represents different roles.
- **`PrescribedMedication.java`**: Represents medications prescribed during appointments.

### 7. Shared
Shared utility and reusable classes:
- **`Medicine.java`**: Represents medicine details.
- **`Inventory.java`**: Manages inventory data.
- **`Schedule.java`**: Represents staff schedules.

### 8. View
View classes handle the user interface and display data:
- **`AdministratorView.java`**: UI for administrator actions.
- **`DoctorView.java`**: UI for doctor-specific actions.
- **`PatientView.java`**: UI for patient interactions.
- **`PharmacistView.java`**: UI for pharmacist-specific tasks.
- **`AppointmentView.java`**: Displays appointment-related data.
- **`MedicalRecordView.java`**: Handles the display of medical records.

---

## Setup and Running the Application

### Requirements
- Java Development Kit (JDK) 8 or later installed.
- A Java IDE (e.g., IntelliJ IDEA, Eclipse) or command-line tools.

### Steps to Run
1. Clone or download the project.
2. Navigate to the `src` folder in your IDE or terminal.
3. Ensure all CSV files are placed in the correct **`Data`** directory.
4. Compile the project:
   ```bash
   javac App.java
5. Run the application:
   ```bash
   java App

### Features
- User Roles: Separate functionalities for administrators, doctors, patients, and pharmacists.
- Appointment Management: Create, view, and manage appointments.
- Medical Records: Store and retrieve patient diagnoses and treatments.
- Pharmacy Inventory: Manage medicine inventory and restock requests.
- Schedule Management: Manage staff schedules.

### Design Patterns

#### MVC (Model-View-Controller):
- Model: Represents the application's data structure.
- View: Displays data to the user.
- Controller: Handles user input and updates the model or view.

#### DAO (Data Access Object):
- Encapsulates logic for reading/writing to CSV files.
- Ensures separation of concerns and clean data access.
