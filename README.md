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
- **`AdministratorController.java`**: Manages the actions and functionalities available to the administrator role, including staff management, schedule management, and system configurations.
- **`AppointmentController.java`**: Handles the logic for managing appointments, including creating, updating, deleting, and retrieving appointment details.
- **`AppointmentOutcomeController.java`**: Manages appointment outcomes after the appointment is completed, including recording prescribed medications and consultation notes.
- **`DoctorController.java`**: Manages functionalities specific to doctors, such as viewing patient details, managing medical records, and responding to appointments.
- **`InventoryController.java`**: Handles pharmacy inventory management, including viewing stock, adding new medicines, and updating existing inventory.
- **`MedicalRecordController.java`**: Manages patient medical records, allowing for creation, updating, and retrieval of medical records.
- **`PastDiagnosesAndTreatmentsController.java`**: Handles the retrieval and management of patients' past diagnoses and treatments to assist doctors in making informed decisions.
- **`PatientController.java`**: Manages patient-specific actions such as registration, viewing personal details, managing appointments, and accessing medical history.
- **`PharmacistController.java`**: Manages pharmacist-specific tasks, including dispensing medicines, handling prescriptions, and tracking inventory levels.
- **`ReplenishmentRestockController.java`**: Handles restocking of medicines and inventory replenishment requests in coordination with the inventory system.
- **`ScheduleController.java`**: Manages staff schedules, including creating, updating, and viewing schedules for doctors, pharmacists, and administrators.

### 4. Data
CSV files that serve as the database for the application:
- **`Appointment.csv`**: Stores appointment-related data such as appointment IDs, doctor IDs, patient IDs, status, dates, and times.
- **`AppointmentOutcome.csv`**: Contains data about completed appointment outcomes, including prescribed medications and consultation notes.
- **`MedicalRecord.csv`**: Holds patients' medical record data, including diagnoses, treatments, and medical history.
- **`Medicine_List.csv`**: Lists all medicines available in the inventory, including details such as medicine name, quantity, and category.
- **`PastDiagnosesAndTreatments.csv`**: Tracks historical data on patients' past diagnoses and treatments.
- **`Patient_List.csv`**: Contains data about registered patients, including their personal details and medical history references.
- **`Replenishment_Restock.csv`**: Records restocking requests for medicines in the pharmacy.
- **`Schedule.csv`**: Manages staff schedules, including working hours for doctors, pharmacists, and other personnel.
- **`Staff_List.csv`**: Lists all staff members, including their roles, IDs, and contact information.
- **`Staff_List.xlsx`**: An Excel version of the staff list, potentially for external use or reports.
- **`userlogin.csv`**: Stores user login credentials and authentication details for the system.


### 5. DataAccess
DAO classes for managing CRUD operations with CSV files:
- **`AppointmentDAO.java`**: Handles CRUD operations for `Appointment.csv`, managing appointment-related data.
- **`AppointmentOutcomeDAO.java`**: Manages appointment outcomes, including prescribed medications and consultation notes in `AppointmentOutcome.csv`.
- **`InventoryDAO.java`**: Handles inventory-related data stored in `Medicine_List.csv`, such as stock levels and medicine details.
- **`MedicalRecordDAO.java`**: Manages CRUD operations for `MedicalRecord.csv`, including patients' diagnoses and treatments.
- **`PastDiagnosesAndTreatmentsDAO.java`**: Handles the retrieval and storage of historical data about patients' past diagnoses and treatments in `PastDiagnosesAndTreatments.csv`.
- **`PatientDAO.java`**: Manages patient records in `Patient_List.csv`, including personal details and references to medical history.
- **`ReplenishmentDAO.java`**: Handles replenishment and restock requests stored in `Replenishment_Restock.csv`.
- **`ScheduleDAO.java`**: Manages staff schedules in `Schedule.csv`, allowing for creation, updates, and retrieval of working hours.
- **`StaffDAO.java`**: Handles data for staff members, including roles and personal details in `Staff_List.csv`.
- **`UserDAO.java`**: Manages user authentication and login credentials stored in `userlogin.csv`.


### 6. Model
Model classes that represent the data structure:

##### Role Model
- **`Administrator.java`**: Represents the administrator role, including functionalities for managing staff, schedules, and system configurations.
- **`Doctor.java`**: Represents the doctor role, including details about assigned patients, medical records, and appointments.
- **`Gender.java`**: Represents gender options for users or patients in the system.
- **`Patient.java`**: Represents patient details, including personal information and references to medical history and appointments.
- **`Pharmacist.java`**: Represents the pharmacist role, including inventory and prescription management.
- **`UserType.java`**: Represents the different types of users in the system (e.g., Administrator, Doctor, Patient, Pharmacist).

##### Shared Model

- **`Appointment.java`**: Represents an appointment, including details like doctor ID, patient ID, date, time, and status.
- **`AppointmentOutcome.java`**: Represents an outcome for a completed appointment, including prescribed medications and consultation notes.
- **`Inventory.java`**: Represents the inventory system, including details about medicines and their stock levels.
- **`MedicalRecord.java`**: Represents a patient's medical record, including diagnoses, treatments, and medical history.
- **`Medicine.java`**: Represents a medicine, including its name, quantity, and other details.
- **`PastDiagnosesAndTreatments.java`**: Tracks a patient's past diagnoses and treatments for medical history purposes.
- **`PrescribedMedication.java`**: Represents medications prescribed during an appointment, including their name, quantity, and status (e.g., pending, dispensed).
- **`ReplenishmentRequest.java`**: Represents a request for restocking medicines in the inventory.
- **`Schedule.java`**: Represents staff schedules, including working hours for doctors, pharmacists, and other staff members.
- **`User.java`**: Represents a user in the system, including authentication and personal details.

##### Menu Model

- **`MenuFactory.java`**: Handles the creation and management of menus for different user roles.
- **`UserFactory.java`**: Manages the creation of user objects based on their roles (e.g., Administrator, Doctor, Patient, Pharmacist).


### 7. View
View classes handle the user interface and display data:

##### Administrator
- **`AdministratorView.java`**: Displays functionalities and data specific to the administrator, such as staff and schedule management.

##### Appointments
- **`AppointmentOutcomeView.java`**: Displays details of completed appointment outcomes, including prescribed medications and consultation notes.
- **`AppointmentRequestsView.java`**: Displays appointment requests made by patients.
- **`AppointmentView.java`**: Manages the display of general appointment details, such as date, time, and status.

##### Doctor
- **`DoctorAvailabilityView.java`**: Displays the doctor's availability for appointments.
- **`DoctorMedicalRecordView.java`**: Displays the patient's medical records as seen by the doctor.
- **`DoctorScheduleView.java`**: Displays the doctor's daily or weekly schedule.
- **`DoctorUpdateMedicalRecordView.java`**: Displays a form for updating or editing a patient's medical record.
- **`DoctorView.java`**: Displays general options and data specific to the doctor's role.

##### Medical Record
- **`MedicalRecordView.java`**: Displays detailed medical records for patients.
- **`PastDiagnosesAndTreatmentsView.java`**: Displays past diagnoses and treatments of a patient.

##### Patient
- **`PatientBookScheduleView.java`**: Displays options for patients to book an appointment.
- **`PatientCancelView.java`**: Displays options for patients to cancel an existing appointment.
- **`PatientReScheduleView.java`**: Displays options for patients to reschedule their appointments.
- **`PatientScheduleView.java`**: Displays the patient's schedule of upcoming appointments.
- **`PatientView.java`**: Displays general options and data specific to the patient's role.

##### Pharmacist
- **`InventoryView.java`**: Displays inventory details, such as available medicines and stock levels.
- **`PharmacistView.java`**: Displays options and data specific to the pharmacist's role.
- **`ReplenishmentRestockView.java`**: Displays restock requests and updates for the pharmacy inventory.

##### User
- **`UserMainView.java`**: Displays the main user interface for general user interactions.


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

##### MVC (Model-View-Controller):
- Model: Represents the application's data structure.
- View: Displays data to the user.
- Controller: Handles user input and updates the model or view.

##### DAO (Data Access Object):
- Encapsulates logic for reading/writing to CSV files.
- Ensures separation of concerns and clean data access.
