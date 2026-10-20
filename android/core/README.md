# Parlance engine (`android/core`)

The on-device dictation engine (package `com.danielrosehill.parlance.core`): `Catalogue`,
`ModelStore`, `ModelDownload`, `ModelPrefs`, `Recorder`, `Transcriber`, `TakeDecoder` (decodes a
take's segments while it is recorded). `android/app` compiles it as a source folder:
`sourceSets["main"].java.srcDir("../core/src/main/java")` in `app/build.gradle.kts`.

It is a twin of Hebrish's engine (`Hebrish-ASR/Hebrish-App`, `android/core`, package
`app.hebrish.core`), ported file for file on 2026-10-20 (Parlance Android 0.4.0 = Hebrish
Android 0.4.0). Keep the two identical apart from the package name, so a change ports with a copy
and a package rename. Hebrish also compiles its copy into Hebrish Keyboard (its HeliBoard fork);
Parlance has no such second consumer, but the same rules keep the files portable:

- No `R`, no resources, no references to the app's classes. The app's build supplies the
  sherpa-onnx AAR, `models.json` as an asset, and the `RECORD_AUDIO` and `INTERNET` permissions.
- Must compile on minSdk 21 as well as 28 (Hebrish Keyboard's minSdk is 21).
- Public signatures change only additively (new optional parameters, new functions with
  defaults), or Hebrish Keyboard breaks when a change is ported back.
