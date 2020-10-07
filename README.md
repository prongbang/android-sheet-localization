# android-sheet-localization

- Extract from the link the DocumentId and SheetId values :

```
https://docs.google.com/spreadsheets/d/<DocumentId>/edit#gid=<SheetId>
```

## Used

- Add annotation on Application class

```kotlin
@AndroidSheetLocalization(
		documentId = "1oS7iJ6ocrZBA53SxRfKF0CG9HAaXeKtzvsTBhgG4Zzk",
		sheetId = "0"
)
class MainApplication : Application()
```

