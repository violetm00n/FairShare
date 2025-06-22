#Fair share 💰

A modern Android application designed to simplify the process of splitting and tracking shared expenses among groups. Whether you're traveling with friends, sharing household costs with roommates, or organizing group events, Money Splitter makes expense management transparent, fair, and effortless.

## 🌟 Features

### Core Functionality
- **Easy Expense Entry**: Quickly add expenses with amount, category, and participants
- **Flexible Splitting Options**: 
  - Equal split among all participants
  - Custom contributions with personalized amounts or percentages
- **Real-time Balance Tracking**: Automatically calculates who owes whom
- **Expense Categories**: Organize expenses by type (food, travel, accommodation, etc.)
- **Offline Support**: All data stored locally - works without internet connection

### User Experience
- **Intuitive Interface**: Built with Jetpack Compose for a modern, responsive UI
- **Real-time Notifications**: Stay informed when expenses are added or balances change
- **Comprehensive History**: View, edit, or delete past expenses
- **Balance Summary**: Clear overview of each participant's financial status
- **Group Management**: Add or remove participants as needed

## 🎯 Perfect For

- **Friend Groups**: Splitting dinner bills, trip expenses, and shared activities
- **Roommates**: Managing rent, utilities, groceries, and household costs
- **Travelers**: Tracking group expenses for transportation, lodging, and meals
- **Event Organizers**: Managing costs for parties, celebrations, and group gifts
- **Coworkers**: Splitting business trip expenses and team activities

## 🛠️ Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Database for local storage
- **Async Programming**: Kotlin Coroutines
- **Navigation**: Jetpack Navigation Component
- **Design**: Material Design Components



## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 21 or higher
- Kotlin 1.5.0 or later

### Installation

1. Clone the repository
```bash
git clone https://github.com/yourusername/money-splitter.git
```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the app on an emulator or physical device

### Building

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease
```

## 📖 How to Use

1. **Create a Group**: Start by adding participants who will share expenses
2. **Add Expenses**: Enter the amount, select participants, and choose splitting method
3. **Track Balances**: View real-time updates of who owes what
4. **Settle Up**: Use the balance summary to see final amounts owed
5. **Manage History**: Review, edit, or delete past expenses as needed

## 🏗️ Architecture

The app follows the MVVM (Model-View-ViewModel) architectural pattern:

- **Model**: Handles data management and business logic using Room database
- **View**: UI components built with Jetpack Compose
- **ViewModel**: Manages UI-related data and handles communication between Model and View

### Key Components

- **Expense Management**: Core logic for adding, updating, and deleting expenses
- **User Management**: Handles participant profiles and group membership
- **Balance Calculation**: Automated calculation engine for fair expense splitting
- **Notifications**: Real-time updates for expense changes
- **Data Persistence**: Local storage using Room database for offline capability

## 🎨 Design Principles

- **Simplicity**: Clean, intuitive interface accessible to all users
- **Transparency**: Clear visibility into all calculations and balances
- **Fairness**: Accurate splitting algorithms for equitable expense sharing
- **Reliability**: Offline capability ensures data is always accessible
- **Performance**: Optimized for smooth operation with large datasets

## 🔮 Future Enhancements

- **Multi-currency Support**: Handle international expenses with currency conversion
- **Cloud Synchronization**: Backup and sync data across devices
- **Payment Integration**: Direct settlement through payment platforms
- **Export Features**: Generate expense reports and summaries
- **Group Sharing**: Invite participants via links or QR codes

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📋 Requirements

- **Minimum SDK**: Android 5.0 (API level 21)
- **Target SDK**: Android 14 (API level 34)
- **Architecture**: ARM64, ARMv7


## 🙏 Acknowledgments

- Built with modern Android development best practices
- Inspired by the need for fair and transparent expense sharing
- Thanks to the Android development community for excellent tools and libraries

---
