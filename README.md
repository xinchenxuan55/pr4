# Model Monitor

An offline Android demo that turns the linear-regression model from the
automated data pipeline into a phone-friendly estimate tool.

## Why mobile delivery is useful

- Gives students and stakeholders a quick way to test model scenarios without
  opening a notebook or starting a backend.
- Makes the released model tangible in a classroom demonstration.
- Works offline and exposes the model coefficients for auditability.
- Demonstrates how CI/CD moves a data product from source code to an
  installable artifact.

## Build

The debug APK is built by GitHub Actions:

```bash
./gradlew assembleDebug
```

The workflow uploads `app-debug.apk` as a build artifact and publishes it as a
GitHub Release asset after pushes to `main`.
