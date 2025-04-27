# Android Sheet Localization 🌍

[![](https://jitpack.io/v/prongbang/android-sheet-localization.svg)](https://jitpack.io/#prongbang/android-sheet-localization)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](https://developer.android.com)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/kotlin-1.5.x-orange.svg)](https://kotlinlang.org)

> Android localization generator that converts Google Sheets data into Android string resources. Inspired by [Flutter Google Sheet localizations generator](https://github.com/aloisdeniel/flutter_sheet_localization).

## ✨ Features

- 📊 **Google Sheets Integration** - Manage translations in a familiar spreadsheet interface
- 🚀 **Automatic Generation** - Generate string resources at compile time
- 🌐 **Multi-Language Support** - Support for unlimited languages
- 🔄 **Real-time Updates** - Changes in Google Sheets reflect in your app
- 🛠️ **Simple Configuration** - Just add an annotation to start
- ⚡ **Build Integration** - Works seamlessly with Android build system

## 📦 Installation

### 1. Add JitPack Repository

In your `project/build.gradle`:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### 2. Add Dependencies

In your `app/build.gradle`:

```groovy
dependencies {
    implementation 'com.github.prongbang:sheet-localization-annotation:1.2.0'
    kapt 'com.github.prongbang:sheet-localization-processor:1.2.0'
}
```

Don't forget to apply the kapt plugin:

```groovy
plugins {
    id 'kotlin-kapt'
}
```

## 🚀 Quick Start

### 1. Create a Google Sheet

Create a Google Sheet with your translations:

![Sheet Example](/screenshot/sheet-localize.png)

Or use this alternative structure:

![Alternative Sheet](/screenshot/sheet.png)

The sheet should have:
- First column: String keys (identifiers)
- Subsequent columns: Translations for each language

### 2. Extract Sheet IDs

From your Google Sheet URL, extract the `DocumentId` and `SheetId`:

```
https://docs.google.com/spreadsheets/d/<DocumentId>/edit#gid=<SheetId>
```

Example URLs:
```
https://docs.google.com/spreadsheets/d/1_97CuyDyVD28ICV_hBGDEdwKGuIokE1egeNlVJn08Tc/edit#gid=0
https://docs.google.com/spreadsheets/d/1r91ECV-As0XtuqGKXU7dXnoY4og9XPBoCqwRcdio6EU/edit#gid=0
```

### 3. Add Annotation

Add the `@AndroidSheetLocalization` annotation to your Application class:

```kotlin
@AndroidSheetLocalization(
    sheetId = "0",
    documentId = "1_97CuyDyVD28ICV_hBGDEdwKGuIokE1egeNlVJn08Tc",
    enabled = true
)
class MainApplication : Application()
```

### 4. Build Project

Build your project (`Make Project` or `Command + F9`):

```
> Create file values-es/strings.xml	-> SUCCESS
> Create file values-zh/strings.xml	-> SUCCESS
> Create file values-de/strings.xml	-> SUCCESS
> Create file values-hi/strings.xml	-> SUCCESS
> Create file values-pt/strings.xml	-> SUCCESS
> Create file values/strings.xml	-> SUCCESS
> Create file values-ru/strings.xml	-> SUCCESS
> Create file values-ja/strings.xml	-> SUCCESS
> Create file values-fr/strings.xml	-> SUCCESS
```

## 📁 Generated Structure

The plugin generates the following structure:

```
res/
├── values/
│   └── strings.xml
├── values-de/
│   └── strings.xml
├── values-es/
│   └── strings.xml
├── values-fr/
│   └── strings.xml
├── values-hi/
│   └── strings.xml
├── values-ja/
│   └── strings.xml
├── values-pt/
│   └── strings.xml
├── values-ru/
│   └── strings.xml
└── values-zh/
    └── strings.xml
```

## ⚙️ Configuration Options

### @AndroidSheetLocalization Annotation

| Parameter | Type | Description | Required |
|-----------|------|-------------|----------|
| `documentId` | String | Google Sheet document ID | ✅ |
| `sheetId` | String | Sheet tab ID (usually "0" for first tab) | ✅ |
| `enabled` | Boolean | Enable/disable generation | ✅ |

## 💡 Usage Examples

### Basic Usage

```kotlin
@AndroidSheetLocalization(
    sheetId = "0",
    documentId = "YOUR_DOCUMENT_ID",
    enabled = true
)
class MyApplication : Application()
```

### Disable Generation

```kotlin
@AndroidSheetLocalization(
    sheetId = "0",
    documentId = "YOUR_DOCUMENT_ID",
    enabled = false // Disable for production builds
)
class MyApplication : Application()
```

### Multiple Sheets

```kotlin
@AndroidSheetLocalization(
    sheetId = "0",
    documentId = "YOUR_DOCUMENT_ID"
)
@AndroidSheetLocalization(
    sheetId = "1",
    documentId = "YOUR_DOCUMENT_ID"
)
class MyApplication : Application()
```

## 🔍 Best Practices

1. **Version Control**: Don't commit generated files to version control
2. **Public Sheets**: Ensure your Google Sheet is publicly accessible
3. **Consistent Keys**: Use consistent naming conventions for string keys
4. **Default Values**: Always provide default values in the main language column
5. **Build Automation**: Integrate with CI/CD for automatic updates

## 🛠️ Troubleshooting

### Common Issues

1. **Compilation Error**: Ensure kapt is properly configured
2. **Empty Files**: Check if the Google Sheet is publicly accessible
3. **Missing Languages**: Verify column headers match language codes
4. **Build Failure**: Clean and rebuild the project

### Debug Tips

```groovy
// Enable kapt verbose logging
kapt {
    arguments {
        arg("verbose", "true")
    }
}
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Inspired by [Flutter Google Sheet localizations generator](https://github.com/aloisdeniel/flutter_sheet_localization)
- Thanks to all contributors who have helped improve this project

---
