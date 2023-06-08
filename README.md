# SimultaneousSharedPreferences
Test of simultaneous access on multiple instances of EncryptedSharedPrefrences.

# Actions
The actions are all in MainViewModel.trigger()

```kotlin
    fun trigger() {
        preferences.forEachIndexed { index, sharedPreferences ->
            sharedPreferences.edit()
                .putString("key", "r$index")
                .apply()
        }
        output = ""
        preferences.forEach { sharedPreferences ->
            output += sharedPreferences.getString("key", null)
        }
    }
```

If there is no coordination between the separate sharedPreferences instances, they will show lots of different values.
If they are coordinated, they will all show the same value.
Compile and run the application and click on the button, and you will see a `Text` area showing the `output`.
