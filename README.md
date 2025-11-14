## 1. Android Login & Signup App

A modern, fully-functional Android authentication application built with Kotlin, following Material Design 3 guidelines and modern Android architecture best practices.

### 🎯 Features: 

#### Authentication
- **User Registration** - Secure signup with email validation
- **User Login** - Email and password authentication
- **Input Validation** - Real-time form validation with error messages
- **Password Security** - Secure password storage with Room database

#### User Interface
- **Material Design 3** - Modern, responsive UI components
- **Dark/Light Theme Support** - Adaptive theming system
- **Custom Icons** - Vector icons for all UI elements
- **Smooth Animations** - Navigation transitions and loading states

#### Architecture
- **MVVM Pattern** - Model-View-ViewModel architecture
- **Repository Pattern** - Clean separation of data layer
- **Room Database** - Local data persistence with SQLite
- **Navigation Component** - Type-safe navigation between screens
- **ViewBinding** - Type-safe view references

## 2. Screens

||||
|---|---|---|
|<img width="311" height="642" alt="Screenshot 2025-11-13 at 5 46 16 in the afternoon" src="https://github.com/user-attachments/assets/19e93a56-17ee-4a2f-bb1d-1b9947b4dd7f" />|<img width="299" height="632" alt="Screenshot 2025-11-13 at 5 46 59 in the afternoon" src="https://github.com/user-attachments/assets/e0779546-2faf-4d06-9992-f41c25d1b4de" />|<img width="300" height="638" alt="Screenshot 2025-11-13 at 5 46 50 in the afternoon" src="https://github.com/user-attachments/assets/6cb92692-0b36-468b-b925-994eb5a288f6" />|
|<img width="308" height="639" alt="Screenshot 2025-11-13 at 5 47 50 in the afternoon" src="https://github.com/user-attachments/assets/e627183f-f445-4bce-9448-c2e19419e66e" />|<img width="319" height="640" alt="Screenshot 2025-11-13 at 5 48 32 in the afternoon" src="https://github.com/user-attachments/assets/062b8d47-f201-4672-bdc2-1ecf21a8511b" />|<img width="305" height="645" alt="Screenshot 2025-11-13 at 5 48 23 in the afternoon" src="https://github.com/user-attachments/assets/46b9c74d-9929-4ee3-945c-962e40c026b1" />|
<img width="307" height="641" alt="Screenshot 2025-11-13 at 5 47 10 in the afternoon" src="https://github.com/user-attachments/assets/0e5501d9-e88e-46fb-836e-5b5edb83573d" />|

## 3. Technical Stack

#### Core Technologies
- **Kotlin** - 100% Kotlin implementation
- **Android SDK** - Minimum API 24 (Android 7.0)
- **Material Design 3** - Modern UI components

#### Architecture Components
- **ViewModel** - Lifecycle-aware data management
- **LiveData** - Observable data holders
- **Room** - SQLite object mapping
- **Navigation** - Fragment navigation management

#### Dependencies
- **Material Components** - `com.google.android.material:material:1.12.0`
- **Room Database** - `androidx.room:room-*:2.8.3`
- **Navigation Component** - `androidx.navigation:*:2.9.6`
- **Lifecycle Components** - `androidx.lifecycle:*:2.8.3`


## 4. Project Structure

``` kotlin
Login_Signup_App/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/login_signup_app/
│   │   │   │   ├── LoginApp.kt                          # Application class
│   │   │   │   ├── MainActivity.kt                      # Main activity
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/
│   │   │   │   │   │   └── User.kt                      # User entity
│   │   │   │   │   ├── local/
│   │   │   │   │   │   ├── AppDatabase.kt               # Room database
│   │   │   │   │   │   └── UserDao.kt                   # Data access object
│   │   │   │   │   └── repository/
│   │   │   │   │       └── UserRepository.kt            # Repository pattern
│   │   │   │   ├── ui/
│   │   │   │   │   ├── login/
│   │   │   │   │   │   ├── LoginFragment.kt
│   │   │   │   │   │   └── LoginViewModel.kt
│   │   │   │   │   ├── signup/
│   │   │   │   │   │   ├── SignupFragment.kt
│   │   │   │   │   │   └── SignupViewModel.kt
│   │   │   │   │   └── home/
│   │   │   │   │       ├── HomeFragment.kt
│   │   │   │   │       └── HomeViewModel.kt
│   │   │   │   └── di/                                  # Dependency injection
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── fragment_login.xml
│   │   │   │   │   ├── fragment_signup.xml
│   │   │   │   │   └── fragment_home.xml
│   │   │   │   ├── navigation/
│   │   │   │   │   └── nav_graph.xml                    # Navigation graph
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   ├── drawable/                            # Icons & vectors
│   │   │   │   │   ├── ic_email.xml
│   │   │   │   │   ├── ic_password.xml
│   │   │   │   │   ├── ic_person.xml
│   │   │   │   │   ├── ic_profile.xml
│   │   │   │   │   ├── ic_settings.xml
│   │   │   │   │   ├── ic_statistics.xml
│   │   │   │   │   ├── ic_help.xml
│   │   │   │   │   └── ic_logout.xml
│   │   │   │   └── color/
│   │   │   │       └── primary_color.xml
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle.kts                                 # App level build config
│   └── proguard-rules.pro
├── gradle/
│   └── libs.versions.toml                              # Dependency versions
├── build.gradle.kts                                    # Project level build config
├── settings.gradle.kts
├── gradle.properties
└── README.md
```
