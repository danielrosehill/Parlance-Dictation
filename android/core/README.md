# Parlance engine (`android/core`)

The on-device dictation engine (package `com.danielrosehill.parlance.core`): `Catalogue`,
`ModelStore`, `ModelDownload`, `ModelPrefs`, `Recorder`, `Transcriber`, `TakeDecoder` (decodes a
take's segments while it is recorded). `android/app` compiles it as a source folder:
`sourceSets["main"].java.srcDir("../core/src/main/java")` in `app/build.gradle.kts`.

It is a twin of Hebrish's engine (`Hebrish-ASR/Hebrish-App`, `android/core`, package
`app.hebrish.core`), ported file for file on 2026-10-20 (Parlance Android 0.4.0 = Hebrish
Android 0.4.0). Keep the two identical apart from the package name, so a change ports with a copy
and a package rename. Each engine has a second consumer, a HeliBoard fork that compiles it as a
git submodule: Hebrish Keyboard (`Hebrish-ASR/Hebrish-Keyboard`) for Hebrish's, and Parlance
Keyboard (`danielrosehill/Parlance-Keyboard`, `parlance/` submodule, since 2026-10-20) for this
one. After a change here, bump the keyboard's submodule (asr-dev `sync-keyboards`, section 2).
The rules that keep the files portable:

- No `R`, no resources, no references to the app's classes. The app's build supplies the
  sherpa-onnx AAR, `models.json` as an asset, and the `RECORD_AUDIO` and `INTERNET` permissions.
- Must compile on minSdk 21 as well as 28 (both keyboards' minSdk is 21).
- Public signatures change only additively (new optional parameters, new functions with
  defaults), or a keyboard breaks when its engine is bumped.
