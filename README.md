# android-sheet-localization

Android Google Sheet Localizations generator, inspired by [Flutter Google Sheet localizations generator](https://github.com/aloisdeniel/flutter_sheet_localization).

## Installation

- Add the following repositories to your `project/build.gradle` file.

```groovy
repositories {
   maven { url 'https://jitpack.io' }
}
```

- Add the following dependency to your `project/app/build.gradle` file.

```groovy
dependencies {
    implementation 'com.github.prongbang:sheet-localization-annotation:1.0.0'
    kapt 'com.github.prongbang:sheet-localization-processor:1.0.0'
}
```

## Used

- Create a Google Sheet

![Sheet](/screenshot/sheet.png)

- Extract from the link the `DocumentId` and `SheetId` values

```
https://docs.google.com/spreadsheets/d/<DocumentId>/edit#gid=<SheetId>
```

- Example

https://docs.google.com/spreadsheets/d/1_97CuyDyVD28ICV_hBGDEdwKGuIokE1egeNlVJn08Tc/edit#gid=0

- Add annotation on `Application` class

```kotlin
@AndroidSheetLocalization(
		documentId = "1_97CuyDyVD28ICV_hBGDEdwKGuIokE1egeNlVJn08Tc",
		sheetId = "0"
)
class MainApplication : Application()
```

- Click `Make Project` or `Command + F9`

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

- Result

```
res
├── values
│   └── strings.xml
├── values-de
│   └── strings.xml
├── values-es
│   └── strings.xml
├── values-fr
│   └── strings.xml
├── values-hi
│   └── strings.xml
├── values-ja
│   └── strings.xml
├── values-pt
│   └── strings.xml
├── values-ru
│   └── strings.xml
└── values-zh
    └── strings.xml
```